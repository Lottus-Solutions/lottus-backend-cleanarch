package br.com.lottus.library.infrastructure.persistence.repository;

import br.com.lottus.library.infrastructure.web.dto.AlunoBatchDTO;
import br.com.lottus.library.infrastructure.web.dto.LivroBatchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BatchRepositoryImpl implements BatchRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void saveAllLivros(List<LivroBatchDTO> livros) {
        String sql = "INSERT IGNORE INTO livro (nome, autor, quantidade, quantidade_disponivel, status, descricao, fk_categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, livros, 1000, (PreparedStatement ps, LivroBatchDTO livro) -> {
            ps.setString(1, livro.getNome());
            ps.setString(2, livro.getAutor());
            ps.setInt(3, livro.getQuantidade());
            ps.setInt(4, livro.getQuantidade()); // quantidadeDisponivel = quantidade
            ps.setString(5, "DISPONIVEL"); // StatusLivro.DISPONIVEL
            ps.setString(6, livro.getDescricao());
            ps.setLong(7, livro.getCategoriaId());
        });
    }

    @Override
    @Transactional
    public void saveAllAlunos(List<AlunoBatchDTO> alunos) {
        String sql = "INSERT IGNORE INTO aluno (nome, qtd_bonus, qtd_livros_lidos, fk_turma) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql, alunos, 1000, (PreparedStatement ps, AlunoBatchDTO aluno) -> {
            ps.setString(1, aluno.getNome());
            ps.setDouble(2, aluno.getQtdBonus());
            ps.setInt(3, aluno.getQtdLivrosLidos());
            ps.setLong(4, aluno.getTurmaId());
        });
    }
}
