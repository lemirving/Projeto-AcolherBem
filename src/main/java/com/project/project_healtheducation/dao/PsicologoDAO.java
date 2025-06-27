package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Psicologo; // Mantido
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class PsicologoDAO {

    public boolean inserirPsicologo(Psicologo psicologo) {
        String sql = "INSERT INTO psicologo (nome, email, senha, tipo, caminhoImagem) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, psicologo.getTipo());
            stmt.setString(5, psicologo.getCaminhoImagem()); // Caminho da imagem é o 5º parâmetro

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    psicologo.setId(rs.getInt(1)); // Define o ID gerado no objeto Psicologo
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar psicólogo: " + e.getMessage()); // Use System.err para erros
            e.printStackTrace();
        }

        return false;
    }

    public Psicologo buscarPsicologoPorId(int id) {
        String sql = "SELECT id, nome, email, senha, tipo, caminhoImagem FROM psicologo WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Psicologo psicologo = new Psicologo();
                psicologo.setId(rs.getInt("id"));
                psicologo.setNome(rs.getString("nome"));
                psicologo.setEmail(rs.getString("email"));
                psicologo.setSenha(rs.getString("senha"));
                psicologo.setTipo(rs.getString("tipo"));
                psicologo.setCaminhoImagem(rs.getString("caminhoImagem"));

                return psicologo;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar psicólogo por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Psicologo buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email, senha, tipo, caminhoImagem FROM psicologo WHERE email = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Psicologo psicologo = new Psicologo();
                psicologo.setId(rs.getInt("id"));
                psicologo.setNome(rs.getString("nome"));
                psicologo.setEmail(rs.getString("email"));
                psicologo.setSenha(rs.getString("senha"));
                psicologo.setTipo(rs.getString("tipo"));
                psicologo.setCaminhoImagem(rs.getString("caminhoImagem"));

                return psicologo;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar psicólogo por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizarCaminhoImagem(int id, String caminhoImagem) {
        String sql = "UPDATE psicologo SET caminhoImagem = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhoImagem);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar caminho da imagem do psicólogo: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizarPerfil(Psicologo psicologo) {
        String sql = "UPDATE psicologo SET nome = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, psicologo.getNome());
            stmt.setInt(2, psicologo.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perfil do psicólogo (nome/caminhoImagem): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean autenticar(String email, String senhaDigitada) {
        Psicologo psicologo = buscarPorEmail(email);
        if (psicologo != null) {
            return BCrypt.checkpw(senhaDigitada, psicologo.getSenha());
        }
        return false;
    }

    public boolean atualizarPsicologo(Psicologo psicologo) {

        String sql = "UPDATE psicologo SET nome = ?, email = ?, senha = ?, tipo = ?, caminhoImagem = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, psicologo.getTipo());
            stmt.setString(5, psicologo.getCaminhoImagem());
            stmt.setInt(6, psicologo.getId());
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar todos os dados do psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean removerPsicologo(int id) {
        String sql = "DELETE FROM psicologo WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean associarTurmaAoPsicologo(int idPsicologo, String nomeTurma) {
        String sql = "INSERT INTO turma_psicologo (nome_turma, id_psicologo) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idPsicologo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao associar turma ao psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


}