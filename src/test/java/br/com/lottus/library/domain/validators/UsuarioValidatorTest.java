package br.com.lottus.library.domain.validators;

import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;
import br.com.lottus.library.domain.exceptions.EmailInvalidoException;
import br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException;
import br.com.lottus.library.domain.exceptions.NomeInvalidoException;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioValidatorTest {

    @Test
    @DisplayName("Deve validar com sucesso um comando de cadastro de usuário com dados válidos")
    void deveValidarComandoComDadosValidos() {
        var command = new CadastrarUsuarioCommand(
                "Nome Válido",
                "email@valido.com",
                "Senha@123",
                1,
                LocalDate.now()
        );

        assertDoesNotThrow(() -> UsuarioValidator.validarCampos(command));
    }

    // Testes para validarNome
    @Test
    @DisplayName("Deve lançar NomeInvalidoException para nome nulo")
    void deveLancarExcecaoParaNomeNulo() {
        var command = new CadastrarUsuarioCommand(null, "email@valido.com", "Senha@123", 1, LocalDate.now());
        var exception = assertThrows(NomeInvalidoException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("O nome deve ter pelo menos 3 caracteres.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar NomeInvalidoException para nome com menos de 3 caracteres")
    void deveLancarExcecaoParaNomeCurto() {
        var command = new CadastrarUsuarioCommand("ab", "email@valido.com", "Senha@123", 1, LocalDate.now());
        var exception = assertThrows(NomeInvalidoException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("O nome deve ter pelo menos 3 caracteres.", exception.getMessage());
    }

    // Testes para validarEmail
    @Test
    @DisplayName("Deve lançar EmailInvalidoException para email nulo")
    void deveLancarExcecaoParaEmailNulo() {
        var command = new CadastrarUsuarioCommand("Nome Válido", null, "Senha@123", 1, LocalDate.now());
        var exception = assertThrows(EmailInvalidoException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("Formato de e-mail inválido.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar EmailInvalidoException para email sem @")
    void deveLancarExcecaoParaEmailInvalido() {
        var command = new CadastrarUsuarioCommand("Nome Válido", "emailinvalido.com", "Senha@123", 1, LocalDate.now());
        var exception = assertThrows(EmailInvalidoException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("Formato de e-mail inválido.", exception.getMessage());
    }

    // Testes para validarSenha
    @Test
    @DisplayName("Deve lançar SenhaInvalidaException para senha nula")
    void deveLancarExcecaoParaSenhaNula() {
        var command = new CadastrarUsuarioCommand("Nome Válido", "email@valido.com", null, 1, LocalDate.now());
        var exception = assertThrows(SenhaInvalidaException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar SenhaInvalidaException para senha fraca")
    void deveLancarExcecaoParaSenhaFraca() {
        var command = new CadastrarUsuarioCommand("Nome Válido", "email@valido.com", "senha123", 1, LocalDate.now());
        var exception = assertThrows(SenhaInvalidaException.class, () -> UsuarioValidator.validarCampos(command));
        assertEquals("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.", exception.getMessage());
    }
}
