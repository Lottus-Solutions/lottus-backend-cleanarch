package br.com.lottus.library.usecase;

import br.com.lottus.library.application.ports.out.CategoriaRepositoryPort;
import br.com.lottus.library.application.usecases.ListarCategoriaImpl;
import br.com.lottus.library.domain.entities.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListarCategoriaImplTest {

    private CategoriaRepositoryPort repository;
    private ListarCategoriaImpl useCase;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CategoriaRepositoryPort.class);
        useCase = new ListarCategoriaImpl(repository);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não existir nenhuma categoria")
    void deveRetornarListaVaziaQuandoCategoriasNaoExistem() {

        when(repository.findAll()).thenReturn(Collections.emptyList());
        List<Categoria> resultado = useCase.executar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(repository, times(1)).findAll();

    }

    @Test
    @DisplayName("Deve retornar todas as categorias cadastradas")
    void deveRetornarCategoriasQuandoExistem() {

        Categoria categoria1 = Categoria.criarComId(1L, "Ficção", "#FFFFFF");
        Categoria categoria2 = Categoria.criarComId(2L, "Aventura", "#FFFFFF");
        when(repository.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<Categoria> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(categoria1));
        assertTrue(resultado.contains(categoria2));
        verify(repository, times(1)).findAll();

    }

    @Test
    @DisplayName("As categorias retornadas devem preservar seus dados")
    void devePreservarDadosDasCategorias() {
        Categoria categoria = Categoria.criarComId(1L, "Ficção", "#FFFFFF");
        when(repository.findAll()).thenReturn(List.of(categoria));

        List<Categoria> resultado = useCase.executar();

        Categoria resultadoCategoria = resultado.get(0);
        assertEquals(1L, resultadoCategoria.getId());
        assertEquals("Ficção", resultadoCategoria.getNome());
        assertEquals("#FFFFFF", resultadoCategoria.getCor());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve propagar exceção do repositório")
    void devePropagarExcecao() {
        when(repository.findAll()).thenThrow(new RuntimeException("Erro no banco"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> useCase.executar());
        assertEquals("Erro no banco", ex.getMessage());
        verify(repository, times(1)).findAll();
    }
}
