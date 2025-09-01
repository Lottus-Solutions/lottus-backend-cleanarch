package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.application.ports.command.CadastrarCategoriaCommand;
import br.com.lottus.library.domain.entities.Categoria;

public interface CadastrarCategoriaUseCase {

    Categoria cadastrar(CadastrarCategoriaCommand command);

}
