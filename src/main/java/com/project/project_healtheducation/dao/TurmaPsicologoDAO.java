package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;

import java.sql.*;
import java.util.ArrayList;

public class TurmaPsicologoDAO {

    public boolean adicionarPsicologoNaTurma(int idPsicologo, String nomeTurma) {
        String sql = "INSERT INTO turma_psicologo (nome_turma, id_psicologo) VALUES (?, ?)";
        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idPsicologo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar psicólogo à turma: " + e.getMessage());
            return false;
        }
    }


    public void removerPsicologoDaTurma(int idPsicologo, String nomeTurma) {
        String sql = "DELETE FROM turma_psicologo WHERE nome_turma = ? AND id_psicologo = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idPsicologo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover psicólogo da turma: " + e.getMessage());
        }
    }

    public ArrayList<String> listarTurmasPorPsicologo(int idPsicologo) {
        String sql = "SELECT nome_turma FROM turma_psicologo WHERE id_psicologo = ?";
        ArrayList<String> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPsicologo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                turmas.add(rs.getString("nome_turma"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar turmas do psicólogo: " + e.getMessage());
        }

        return turmas;
    }

    public ArrayList<Integer> listarPsicologosPorTurma(String nomeTurma) {
        String sql = "SELECT id_psicologo FROM turma_psicologo WHERE nome_turma = ?";
        ArrayList<Integer> psicologos = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeTurma);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                psicologos.add(rs.getInt("id_psicologo"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar psicólogos da turma: " + e.getMessage());
        }

        return psicologos;
    }
}
