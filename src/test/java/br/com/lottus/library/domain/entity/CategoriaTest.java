//package br.com.lottus.library.domain.entity;
//
//import br.com.lottus.library.domain.entities.Categoria;
//import br.com.lottus.library.domain.exceptions.NomeCategoriaVazioOuNuloException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class CategoriaTest {
//
//    private String nome;
//    private String cor;
//
//    @BeforeEach
//    void setUp() {
//        nome = "Ficção";
//        cor = "#FF5733";
//    }
//
//    @Test
//    @DisplayName("Deve criar uma categoria com nome e cor válidos")
//    void deveCriarCategoriaComNomeECorValidos() {
//        Categoria categoria = new Categoria(nome, cor);
//
//        assertNotNull(categoria);
//        assertEquals(nome, categoria.getNome());
//        assertEquals(cor, categoria.getCor());
//    }
//
//    @Test
//    @DisplayName("Deve lançar exceção ao criar uma categoria com nome nulo ou vazio")
//    void deveLancarExcecaoAoCriarCategoriaComNomeNuloOuVazio() {
//        try {
//            new Categoria(null, cor);
//        } catch (NomeCategoriaVazioOuNuloException e) {
//            assertEquals("Nome não pode ser nulo ou vazio", e.getMessage());
//        }
//    }
//
//    @Test
//    @DisplayName("Deve criar uma categoria com nome válido e cor padrão quando cor for nula ou vazia")
//    void deveCriarCategoriaComNomeValidoECorPadrao() {
//        Categoria categoria = new Categoria(nome, null);
//
//        assertNotNull(categoria);
//        assertEquals(nome, categoria.getNome());
//        assertEquals("#CBCBCB", categoria.getCor()); // Cor padrão
//
//    }
//}
