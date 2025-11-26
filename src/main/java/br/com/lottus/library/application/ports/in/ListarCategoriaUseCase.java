package br.com.lottus.library.application.ports.in;

import br.com.lottus.library.domain.entities.Categoria;

import java.util.List;

public interface ListarCategoriaUseCase {

    List<Categoria> executar();

}
