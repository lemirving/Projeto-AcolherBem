package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.model.Turma;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class PsicologoDAO {

    public boolean inserirPsicologo(Psicologo psicologo) {
        String sql = "INSERT INTO psicologo (nome, email, senha, idade, identificacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setInt(4, psicologo.getIdade());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    psicologo.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar psicólogo: " + e.getMessage());
        }

        return false;
    }

    public Psicologo buscarPsicologoPorId(int id) {
        String sql = "SELECT * FROM psicologo WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Psicologo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getInt("idade"),
                        buscarTurmasDoPsicologo(id)
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar psicólogo: " + e.getMessage());
        }

        return null;
    }

    public Psicologo buscarPorEmail(String email) {
        String sql = "SELECT * FROM psicologo WHERE email = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Psicologo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"), // senha criptografada para autenticação
                        rs.getInt("idade"),
                        buscarTurmasDoPsicologo(rs.getInt("id"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar psicólogo por email: " + e.getMessage());
        }
        return null;
    }

    public boolean autenticar(String email, String senhaDigitada) {
        Psicologo psicologo = buscarPorEmail(email);
        if (psicologo != null) {
            return BCrypt.checkpw(senhaDigitada, psicologo.getSenha());
        }
        return false;
    }

    public boolean atualizarPsicologo(Psicologo psicologo) {
        String sql = "UPDATE psicologo SET nome = ?, email = ?, senha = ?, idade = ?, identificacao_profissional = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setInt(4, psicologo.getIdade());
            stmt.setInt(6, psicologo.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar psicólogo: " + e.getMessage());
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
            System.out.println("Erro ao remover psicólogo: " + e.getMessage());
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
            System.out.println("Erro ao associar turma ao psicólogo: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Turma> buscarTurmasDoPsicologo(int idPsicologo) {
        String sql = "SELECT t.nome, t.quantidade FROM turma t JOIN turma_psicologo tp ON t.nome = tp.nome_turma WHERE tp.id_psicologo = ?";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPsicologo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma(rs.getString("nome"), rs.getInt("quantidade"), new ArrayList<>());
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar turmas do psicólogo: " + e.getMessage());
        }

        return turmas;
    }
}
