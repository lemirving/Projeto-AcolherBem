package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Turma;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class ProfessorDAO {

    public boolean inserirProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, email, senha, tipo) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String senhaCriptografada = BCrypt.hashpw(professor.getSenha(), BCrypt.gensalt());

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, professor.getTipo());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    professor.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }

        return false;
    }

    public Professor buscarProfessorPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getInt("idade"),
                        rs.getString("especialidade"),
                        buscarTurmasDoProfessor(id)
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }

        return null;
    }

    public Professor buscarProfessorPorEmail(String email) {
        String sql = "SELECT * FROM professor WHERE email = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"), // senha criptografada para autenticação
                        rs.getInt("idade"),
                        rs.getString("especialidade"),
                        buscarTurmasDoProfessor(rs.getInt("id"))
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor por email: " + e.getMessage());
        }

        return null;
    }

    public boolean autenticar(String email, String senhaDigitada) {
        Professor professor = buscarProfessorPorEmail(email);
        if (professor != null) {
            return BCrypt.checkpw(senhaDigitada, professor.getSenha());
        }
        return false;
    }

    public boolean atualizarProfessor(Professor professor) {
        String sql = "UPDATE professor SET nome = ?, email = ?, senha = ?, idade = ?, especialidade = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String senhaCriptografada = BCrypt.hashpw(professor.getSenha(), BCrypt.gensalt());

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setInt(4, professor.getIdade());
            stmt.setString(5, professor.getEspecialidade());
            stmt.setInt(6, professor.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }

        return false;
    }

    public boolean removerProfessor(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover professor: " + e.getMessage());
        }

        return false;
    }

    public boolean associarTurmaAoProfessor(int idProfessor, String nomeTurma) {
        String sql = "INSERT INTO turma_professor (nome_turma, id_professor) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idProfessor);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao associar turma ao professor: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Turma> buscarTurmasDoProfessor(int idProfessor) {
        String sql = "SELECT t.nome, t.quantidade FROM turma t JOIN turma_professor tp ON t.nome = tp.nome_turma WHERE tp.id_professor = ?";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfessor);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma(rs.getString("nome"), rs.getInt("quantidade"));
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar turmas do professor: " + e.getMessage());
        }

        return turmas;
    }
}
