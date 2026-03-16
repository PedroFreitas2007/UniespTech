package repository;

import infra.DatabaseConnection;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepositoryPostgres implements AlunoRepository {

    public AlunoRepositoryPostgres() {
        inicializarTabela();
    }

    private void inicializarTabela() {
        String sql = """
                CREATE TABLE IF NOT EXISTS alunos (
                    id    SERIAL       PRIMARY KEY,
                    nome  VARCHAR(150) NOT NULL,
                    cpf   CHAR(11)     NOT NULL UNIQUE
                );
                """;
        try (Connection conn = DatabaseConnection.obterConexao();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar tabela: " + e.getMessage(), e);
        }
    }

    @Override
    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, cpf) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new RuntimeException("CPF já cadastrado: " + aluno.getCpf());
            }
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Aluno> listarTodos() {
        String sql = "SELECT id, nome, cpf FROM alunos ORDER BY id";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage(), e);
        }
        return alunos;
    }

    @Override
    public void deletarTodos() {
        String sql = "DELETE FROM alunos";
        try (Connection conn = DatabaseConnection.obterConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar alunos: " + e.getMessage(), e);
        }
    }
}