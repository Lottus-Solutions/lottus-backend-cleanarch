package br.com.lottus.library.application.usecases;

import br.com.lottus.library.application.exceptions.UsuarioJaCadastradoComEmailException;
import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;
import br.com.lottus.library.application.ports.out.PasswordEncoderPort;
import br.com.lottus.library.application.ports.out.UsuarioRepositoryPort;
import br.com.lottus.library.domain.entities.Usuario;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastrarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @InjectMocks
    private CadastrarUsuarioUseCaseImpl cadastrarUsuarioUseCase;

    private CadastrarUsuarioCommand command;

    @BeforeEach
    void setUp() {
        command = new CadastrarUsuarioCommand(
                "Nome Válido",
                "email@valido.com",
                "Senha@123",
                1,
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("Deve cadastrar um novo usuário com sucesso")
    void deveCadastrarUsuarioComSucesso() {
        // Arrange
        when(usuarioRepositoryPort.findByEmail(command.email())).thenReturn(Optional.empty());
        when(passwordEncoderPort.encode(command.senha())).thenReturn("senhaCriptografada");

        Usuario usuarioSalvo = Usuario.criar(command);
        usuarioSalvo.setId(1L);
        usuarioSalvo.setSenha("senhaCriptografada");
        when(usuarioRepositoryPort.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        // Act
        Usuario result = cadastrarUsuarioUseCase.executar(command);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(command.nome(), result.getNome());
        assertEquals(command.email(), result.getEmail());
        assertEquals("senhaCriptografada", result.getSenha());

        verify(usuarioRepositoryPort).findByEmail(command.email());
        verify(passwordEncoderPort).encode(command.senha());
        verify(usuarioRepositoryPort).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve lançar UsuarioJaCadastradoComEmailException ao tentar cadastrar email existente")
    void deveLancarExcecaoParaEmailJaCadastrado() {
        // Arrange
        when(usuarioRepositoryPort.findByEmail(command.email())).thenReturn(Optional.of(Usuario.criar(command)));

        // Act & Assert
        var exception = assertThrows(UsuarioJaCadastradoComEmailException.class, () -> {
            cadastrarUsuarioUseCase.executar(command);
        });

        assertEquals("E-mail já cadastrado.", exception.getMessage());

        verify(usuarioRepositoryPort).findByEmail(command.email());
        verify(passwordEncoderPort, never()).encode(anyString());
        verify(usuarioRepositoryPort, never()).save(any(Usuario.class));
    }

    @Test
    @DisplayName("Deve propagar exceção de validação ao receber dados inválidos")
    void devePropagarExcecaoDeValidacao() {
        // Arrange
        var commandInvalido = new CadastrarUsuarioCommand("Nome Válido", "email@valido.com", "senhafraca",1, LocalDate.now());

        // Act & Assert
        assertThrows(SenhaInvalidaException.class, () -> {
            cadastrarUsuarioUseCase.executar(commandInvalido);
        });

        verify(usuarioRepositoryPort, never()).findByEmail(anyString());
        verify(passwordEncoderPort, never()).encode(anyString());
        verify(usuarioRepositoryPort, never()).save(any(Usuario.class));
    }
}
