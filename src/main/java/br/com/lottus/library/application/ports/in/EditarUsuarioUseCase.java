package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.EditarUsuarioCommand;
import br.com.lottus.library.domain.entities.Usuario;

public interface EditarUsuarioUseCase {
    Usuario execute(EditarUsuarioCommand command);
}
