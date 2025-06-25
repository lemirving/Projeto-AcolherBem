package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Humor;

import java.sql.*;
import java.util.ArrayList;

public class StatusEmocionalDAO {

    public boolean inserirEmocao(Humor emocao) {
        String sql = "INSERT INTO emocao (id_aluno, data, descricao, nivel) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, emocao.getIdAluno());
            stmt.setDate(2, Date.valueOf(emocao.getData())); // Usando java.sql.Date
            stmt.setString(3, emocao.getNomeHumor());
            stmt.setInt(4, emocao.getNivel());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        emocao.setId(rs.getInt(1));  // Supondo que StatusEmocional tem setId()
                    }
                }
                return true;
            } else {
                System.out.println("Nenhuma linha afetada ao inserir emoção.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir emoção: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Humor> listarEmocoesPorAluno(int idAluno) {
        String sql = "SELECT * FROM emocao WHERE id_aluno = ? ORDER BY data DESC";
        ArrayList<Humor> emocoes = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Humor emocao = new Humor(
                            rs.getInt("id_aluno"),
                            rs.getDate("data").toLocalDate(),  // Usando java.sql.Date
                            rs.getString("descricao"),
                            rs.getInt("nivel")
                    );
                    // Se StatusEmocional tiver id, atribua também:
                    emocao.setId(rs.getInt("id"));
                    emocoes.add(emocao);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar emoções do aluno: " + e.getMessage());
        }

        return emocoes;
    }

    public boolean removerEmocaoPorId(int idEmocao) {
        String sql = "DELETE FROM emocao WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmocao);
            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Emoção com id " + idEmocao + " removida com sucesso.");
                return true;
            } else {
                System.out.println("Nenhuma emoção encontrada para o id: " + idEmocao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao remover emoção: " + e.getMessage());
        }

        return false;
    }

    public boolean atualizarEmocao(int idEmocao, String novaDescricao, int novoNivel) {
        String sql = "UPDATE emocao SET descricao = ?, nivel = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaDescricao);
            stmt.setInt(2, novoNivel);
            stmt.setInt(3, idEmocao);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Emoção com id " + idEmocao + " atualizada com sucesso.");
                return true;
            } else {
                System.out.println("Nenhuma emoção encontrada para atualizar com o id: " + idEmocao);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar emoção: " + e.getMessage());
        }

        return false;
    }
}
