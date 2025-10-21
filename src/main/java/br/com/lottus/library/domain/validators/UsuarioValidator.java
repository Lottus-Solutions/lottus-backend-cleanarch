package br.com.lottus.library.domain.validators;

import br.com.lottus.library.application.ports.command.CadastrarUsuarioCommand;
import br.com.lottus.library.domain.exceptions.IdAvatarInvalidoException;
import br.com.lottus.library.domain.exceptions.EmailInvalidoException;
import br.com.lottus.library.domain.exceptions.NomeInvalidoException;
import br.com.lottus.library.domain.exceptions.SenhaInvalidaException;

import java.util.regex.Pattern;

public class UsuarioValidator {

    private static final int TAMANHO_MINIMO_NOME = 3;
    private static final String PADRAO_SENHA = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~]).{8,}$";
    private static final Pattern pattern = Pattern.compile(PADRAO_SENHA);

    public static void validarCampos(CadastrarUsuarioCommand command) {
        validarNome(command.nome());
        validarEmail(command.email());
        validarSenha(command.senha());
        validarIdAvatar(command.idAvatar());
    }

    private static void validarNome(String nome) {
        if (nome == null || nome.trim().length() < TAMANHO_MINIMO_NOME) {
            throw new NomeInvalidoException("O nome deve ter pelo menos " + TAMANHO_MINIMO_NOME + " caracteres.");
        }
    }

    private static void validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException("Formato de e-mail inválido.");
        }
    }

    private static void validarSenha(String senha) {
        if (senha == null || !pattern.matcher(senha).matches()) {
            throw new SenhaInvalidaException("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.");
        }
    }

    private static void validarIdAvatar(Integer idAvatar) {
        if (idAvatar == null) {
            throw new IdAvatarInvalidoException("O id do avatar é obrigatório.");
        }
    }
}
