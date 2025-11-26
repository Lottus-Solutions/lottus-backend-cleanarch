package br.com.lottus.library.infrastructure.web.controller;

import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.application.ports.in.CadastrarUsuarioUseCase;
import br.com.lottus.library.application.ports.in.LoginUseCase;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import br.com.lottus.library.infrastructure.web.command.RegisterUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

    @MockBean
    private LoginUseCase loginUseCase;

    @Test
    @DisplayName("Deve registrar um usuário com sucesso e retornar status 200 OK")
    void deveRegistrarUsuarioComSucesso() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "teste@email.com", "Senha@123", 1);
        var usuario = Usuario.criarComId(1L, request.nome(), request.email(), "senhaCriptografada", LocalDate.now(), request.idAvatar());

        when(cadastrarUsuarioUseCase.executar(any())).thenReturn(usuario);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value(request.nome()))
                .andExpect(jsonPath("$.email").value(request.email()));
    }

    @Test
    @DisplayName("Deve retornar status 409 Conflict ao tentar registrar email já existente")
    void deveRetornarConflictParaEmailExistente() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "existente@email.com", "Senha@123", 1);

        when(cadastrarUsuarioUseCase.executar(any()))
                .thenThrow(new UsuarioJaCadastradoComEmailException("E-mail já cadastrado."));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.['Erro: ']").value("Erro de duplicidade: E-mail já cadastrado."));
    }

    @Test
    @DisplayName("Deve retornar status 400 Bad Request para erro de validação de senha")
    void deveRetornarBadRequestParaValidacaoDeSenha() throws Exception {
        var request = new RegisterUserRequest("Nome Teste", "teste@email.com", "senhafraca", 1);
        String mensagemErro = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.";

        when(cadastrarUsuarioUseCase.executar(any()))
                .thenThrow(new SenhaInvalidaException(mensagemErro));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.['Erro de validação: ']").value(mensagemErro));
    }

    @Test
    @DisplayName("Deve retornar status 400 Bad Request para corpo da requisição inválido (nome nulo)")
    void deveRetornarBadRequestParaRequestInvalido() throws Exception {
        var request = new RegisterUserRequest(null, "teste@email.com", "Senha@123", 1);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
