package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Turma;

import java.sql.*;
import java.util.ArrayList;

public class TurmaProfessorDAO {

    public boolean adicionarProfessorNaTurma(int idProfessor, String nomeTurma) {
        String sql = "INSERT INTO turma_professor (nome_turma, id_professor) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idProfessor);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar professor à turma: " + e.getMessage());
            return false;
        }
    }

    public boolean removerProfessorDaTurma(int idProfessor, String nomeTurma) {
        String sql = "DELETE FROM turma_professor WHERE nome_turma = ? AND id_professor = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idProfessor);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover professor da turma: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<String> listarTurmasPorProfessor(int idProfessor) {
        String sql = "SELECT nome_turma FROM turma_professor WHERE id_professor = ?";
        ArrayList<String> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    turmas.add(rs.getString("nome_turma"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar turmas do professor: " + e.getMessage());
        }

        return turmas;
    }

    public ArrayList<Integer> listarProfessoresPorTurma(String nomeTurma) {
        String sql = "SELECT id_professor FROM turma_professor WHERE nome_turma = ?";
        ArrayList<Integer> professores = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    professores.add(rs.getInt("id_professor"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar professores da turma: " + e.getMessage());
        }

        return professores;
    }
}
