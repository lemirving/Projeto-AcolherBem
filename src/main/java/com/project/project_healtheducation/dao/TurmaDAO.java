package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Turma;

import java.sql.*;
import java.util.ArrayList;

public class TurmaDAO {

    public void inserirTurma(Turma turma) {
        String sql = "INSERT INTO turma (nome, quantidade) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNomeTurma());
            stmt.setInt(2, turma.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar turma: " + e.getMessage());
        }
    }

    public Turma buscarTurmaPorNome(String nome) {
        String sql = "SELECT * FROM turma WHERE nome = ?";
        Turma turma = null;

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                turma = new Turma(rs.getString("nome"), rs.getInt("quantidade"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar turma: " + e.getMessage());
        }

        return turma;
    }

    public ArrayList<Turma> listarTurmas() {
        String sql = "SELECT * FROM turma";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                turmas.add(new Turma(rs.getString("nome"), rs.getInt("quantidade")));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar turmas: " + e.getMessage());
        }

        return turmas;
    }

    public void atualizarTurma(Turma turma) {
        String sql = "UPDATE turma SET quantidade = ? WHERE nome = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, turma.getQuantidade());
            stmt.setString(2, turma.getNomeTurma());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    public void deletarTurma(String nome) {
        String sql = "DELETE FROM turma WHERE nome = ?";

        try (Connection conn = dbSetup.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar turma: " + e.getMessage());
        }
    }
}
