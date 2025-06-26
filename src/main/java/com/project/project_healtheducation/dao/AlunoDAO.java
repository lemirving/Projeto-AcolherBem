package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Aluno;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

//    public boolean inserirAluno(Aluno aluno) {
//        String sql = "INSERT INTO aluno (nome, email, senha) VALUES (?, ?, ?)";
//
//        try (Connection conn = dbSetup.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//
//            String senhaCriptografada = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt());
//
//            stmt.setString(1, aluno.getNome());
//            stmt.setString(2, aluno.getEmail());
//            stmt.setString(3, senhaCriptografada);
////            stmt.setInt(4, aluno.getIdade());
////            stmt.setString(5, aluno.getAnoEscolar());
////            stmt.setString(6, aluno.getNomeTurma());
//
//            int linhasAfetadas = stmt.executeUpdate();
//
//            if (linhasAfetadas > 0) {
//                ResultSet rs = stmt.getGeneratedKeys();
//                if (rs.next()) {
//                    aluno.setId(rs.getInt(1));  // atualiza o ID do objeto com o gerado no banco
//                }
//                return true;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Erro ao inserir aluno: " + e.getMessage());
//        }
//        return false;
//    }

public boolean inserirAluno(Aluno aluno) {
    String sql = "INSERT INTO aluno (nome, email, senha, tipo, idade, matricula, turma) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = dbSetup.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        String senhaCriptografada = BCrypt.hashpw(aluno.getSenha(), BCrypt.gensalt());

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getEmail());
        stmt.setString(3, senhaCriptografada);
        stmt.setString(4, aluno.getTipo());
        stmt.setString(5, aluno.getIdade());
        stmt.setString(6, aluno.getMatricula());
        stmt.setString(7, aluno.getNomeTurma());

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                aluno.setId(rs.getInt(1));
            }
            return true;
        }

    } catch (SQLException e) {
        System.out.println("Erro ao inserir aluno: " + e.getMessage());
    }
    return false;
}

    public Aluno buscarPorEmail(String email) {
        String sql = "SELECT * FROM aluno WHERE email = ?";
        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha")); // Criptografada
//                aluno.setIdade(rs.getInt("idade"));
//                aluno.setNomeTurma(rs.getString("nome_turma"));
                return aluno;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }
    public Aluno buscarAlunoPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
//                aluno.setIdade(rs.getInt("idade"));
//                aluno.setNomeTurma(rs.getString("nome_turma"));
                return aluno;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }

    public boolean autenticar(String email, String senhaDigitada) {
        Aluno aluno = buscarPorEmail(email);
        if (aluno != null) {
            return BCrypt.checkpw(senhaDigitada, aluno.getSenha());
        }
        return false;
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = dbSetup.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNomeTurma(rs.getString("turma"));
                aluno.setId(rs.getInt("id"));

                // Criptografada
//                aluno.setIdade(rs.getInt("idade"));
//                aluno.setNomeTurma(rs.getString("nome_turma"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }

        return alunos;
    }
    public List<Aluno> listarTodosComUltimoHumor() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT a.*, e.nomeHumor " +
                "FROM aluno a " +
                "LEFT JOIN (" +
                "  SELECT id_aluno, nomeHumor " +
                "  FROM emocao " +
                "  WHERE id IN (" +
                "    SELECT MAX(id) FROM emocao GROUP BY id_aluno" +
                "  )" +
                ") e ON e.id_aluno = a.id";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setIdade(rs.getString("idade"));
                aluno.setTipo(rs.getString("tipo"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNomeTurma(rs.getString("turma"));
                aluno.setHumorAtual(rs.getString("nomeHumor")); // <- aqui o nome correto
                lista.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos com humor: " + e.getMessage());
        }

        return lista;
    }

    public boolean removerAlunoPorId(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // retorna true se o aluno foi removido
        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno: " + e.getMessage());
            return false;
        }
    }

}
