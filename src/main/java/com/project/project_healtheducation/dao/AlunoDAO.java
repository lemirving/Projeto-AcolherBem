package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Aluno;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public boolean inserirAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, senha, tipo, idade, matricula, turma, caminhoImagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            stmt.setString(8, aluno.getCaminhoImagem());

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
            e.printStackTrace(); // Always good for debugging
        }
        return false;
    }

    public boolean atualizarPerfil(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, idade = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getIdade());
            stmt.setInt(3, aluno.getId());
            int rowsAffected = stmt.executeUpdate();
            System.out.println("AlunoDAO - atualizarPerfil: Linhas afetadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar perfil do aluno: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Aluno buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email, senha, idade, matricula, turma, tipo, caminhoImagem FROM aluno WHERE email = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setIdade(rs.getString("idade"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNomeTurma(rs.getString("turma"));
                aluno.setTipo(rs.getString("tipo"));
                aluno.setCaminhoImagem(rs.getString("caminhoImagem"));
                return aluno;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Aluno buscarAlunoPorId(int id) {
        String sql = "SELECT id, nome, email, senha, idade, matricula, turma, tipo, caminhoImagem FROM aluno WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setIdade(rs.getString("idade"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNomeTurma(rs.getString("turma"));
                aluno.setTipo(rs.getString("tipo"));
                aluno.setCaminhoImagem(rs.getString("caminhoImagem"));
                return aluno;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por ID: " + e.getMessage());
            e.printStackTrace();
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
        String sql = "SELECT id, nome, email, senha, idade, matricula, turma, tipo, caminhoImagem FROM aluno";

        try (Connection conn = dbSetup.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha"));
                aluno.setIdade(rs.getString("idade"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setNomeTurma(rs.getString("turma"));
                aluno.setTipo(rs.getString("tipo"));
                aluno.setCaminhoImagem(rs.getString("caminhoImagem"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> listarTodosComUltimoHumor() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT a.id, a.nome, a.email, a.idade, a.tipo, a.matricula, a.turma, a.caminhoImagem, e.nomeHumor, e.descricao " + // <-- ADICIONADA 'e.descricao' AQUI!
                "FROM aluno a " +
                "LEFT JOIN (" +
                "  SELECT id_aluno, nomeHumor, descricao " +
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
                aluno.setCaminhoImagem(rs.getString("caminhoImagem"));

                String humorDoBanco = rs.getString("nomeHumor");
                String descricaoDoBanco = rs.getString("descricao");

                aluno.setHumorAtual(humorDoBanco);
                aluno.setDescricaoHumorAtual(descricaoDoBanco);



                lista.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos com humor: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean atualizarCaminhoImagem(int id, String caminhoImagem) {
        String sql = "UPDATE aluno SET caminhoImagem = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhoImagem);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("AlunoDAO - atualizarCaminhoImagem: Linhas afetadas: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar imagem do aluno: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean removerAlunoPorId(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}