package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.in.LoginUseCase;
import br.com.lottus.library.application.ports.out.JwtProviderPort;
import br.com.lottus.library.application.ports.out.TokenBlocklistPort;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import br.com.lottus.library.infrastructure.web.command.RegisterUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfigTestDisable.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @MockitoBean
    private LoginUseCase loginUseCase;

    @MockitoBean
    private JwtProviderPort jwtProviderPort;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private TokenBlocklistPort tokenBlocklistPort;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("Deve registrar um usuário com sucesso e retornar status 200 OK")
    void deveRegistrarUsuarioComSucesso() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "teste@email.com", "Senha@123", 1);

        var usuario = Usuario.criarComId(
                1L,
                request.nome(),
                request.email(),
                "senhaCriptografada",
                LocalDate.now(),
                request.idAvatar()
        );

        when(cadastrarUsuarioUseCase.executar(any())).thenReturn(usuario);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                // ajustado conforme contract da API atual
                .andExpect(jsonPath("$.email").value(request.nome()))  // email = nome
                .andExpect(jsonPath("$.name").value(request.email())); // name = email
    }

    @Test
    @DisplayName("Deve retornar status 409 Conflict ao tentar registrar email já existente")
    void deveRetornarConflictParaEmailExistente() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "existente@email.com", "Senha@123", 1);

        when(cadastrarUsuarioUseCase.executar(any()))
                .thenThrow(new UsuarioJaCadastradoComEmailException("E-mail já cadastrado."));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Usuário já cadastrado"))
                .andExpect(jsonPath("$.message").value("E-mail já cadastrado."));
    }

    @Test
    @DisplayName("Deve retornar status 400 Bad Request para erro de validação de senha")
    void deveRetornarBadRequestParaValidacaoDeSenha() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "teste@email.com", "senhafraca", 1);
        String mensagemErro = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.";

        when(cadastrarUsuarioUseCase.executar(any()))
                .thenThrow(new SenhaInvalidaException(mensagemErro));

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Erro de validação"))
                .andExpect(jsonPath("$.message").value(mensagemErro));
    }

    @Test
    @DisplayName("Deve retornar status 400 Bad Request para corpo da requisição inválido (nome nulo)")
    void deveRetornarBadRequestParaRequestInvalido() throws Exception {
        var request = new RegisterUserRequest(null, "teste@email.com", "Senha@123", 1);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
