package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Humor;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter; // <-- NOVO IMPORT: Para formatar/parsear datas
import java.util.*;

public class HumorDAO {

    // Formato para gravação e leitura de datas no banco de dados SQLite
    private static final DateTimeFormatter DB_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE; // YYYY-MM-DD

    public boolean inserirEmocao(Humor emocao) {
        String sql = "INSERT INTO emocao (nomeHumor, id_aluno, data, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, emocao.getNomeHumor());
            stmt.setInt(2, emocao.getIdAluno());
            stmt.setString(3, emocao.getData().format(DB_DATE_FORMATTER)); // <-- CORREÇÃO: Grava LocalDate como String
            stmt.setString(4, emocao.getDescricao());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        emocao.setId(rs.getInt(1));
                    }
                }
                return true;
            } else {
                System.out.println("Nenhuma linha afetada ao inserir emoção.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir emoção: " + e.getMessage()); // Use System.err para erros
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Humor> listarEmocoesPorAluno(int idAluno) {
        String sql = "SELECT id, id_aluno, nomeHumor, descricao, data FROM emocao WHERE id_aluno = ? ORDER BY data DESC, id DESC"; // ORDENAÇÃO POR data e id para pegar o último de verdade
        ArrayList<Humor> emocoes = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Humor emocao = new Humor(); // Use o construtor vazio e setters para clareza
                    emocao.setId(rs.getInt("id"));
                    emocao.setIdAluno(rs.getInt("id_aluno"));
                    emocao.setNomeHumor(rs.getString("nomeHumor"));
                    emocao.setDescricao(rs.getString("descricao"));
                    emocao.setData(LocalDate.parse(rs.getString("data"), DB_DATE_FORMATTER)); // <-- CORREÇÃO: Lê String e converte para LocalDate

                    emocoes.add(emocao);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar emoções do aluno no DAO: " + e.getMessage());
            e.printStackTrace();
        }

        return emocoes;
    }


    public Map<String, Map<LocalDate, Integer>> getContagemEmocoesPorData(LocalDate dataInicio, LocalDate dataFim) {
        Map<String, Map<LocalDate, Integer>> emocoesPorData = new HashMap<>();

        String sql = "SELECT nomeHumor, data as data_registro, COUNT(*) as contagem " + // Removed DATE() wrapper for SQLite TEXT data
                "FROM emocao " +
                "WHERE data BETWEEN ? AND ? " + // Comparison works correctly for YYYY-MM-DD strings
                "GROUP BY nomeHumor, data " + // Group by data directly (as string)
                "ORDER BY nomeHumor, data_registro";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dataInicio.format(DB_DATE_FORMATTER)); // <-- CORREÇÃO: Passa como String
            stmt.setString(2, dataFim.format(DB_DATE_FORMATTER));   // <-- CORREÇÃO: Passa como String

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nomeHumor = rs.getString("nomeHumor");
                    LocalDate data = LocalDate.parse(rs.getString("data_registro"), DB_DATE_FORMATTER); // <-- CORREÇÃO: Lê String e converte
                    int contagem = rs.getInt("contagem");

                    emocoesPorData.computeIfAbsent(nomeHumor, k -> new TreeMap<>());
                    emocoesPorData.get(nomeHumor).put(data, contagem);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contagem de emoções por data para o gráfico: " + e.getMessage());
            e.printStackTrace();
        }
        return emocoesPorData;
    }

    public boolean atualizarEmocao(int idEmocao, String novaDescricao, String novoNomeHumor) {
        String sql = "UPDATE emocao SET descricao = ?, nomeHumor = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novaDescricao);
            stmt.setString(2, novoNomeHumor);
            stmt.setInt(3, idEmocao);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Emoção com id " + idEmocao + " atualizada com sucesso.");
                return true;
            } else {
                System.out.println("Nenhuma emoção encontrada para atualizar com o id: " + idEmocao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar emoção: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }


    public ArrayList<Humor> listarEmocoesDoAluno(int idAluno) {
        String sql = "SELECT id, id_aluno, nomeHumor, descricao, data FROM emocao WHERE id_aluno = ? ORDER BY data DESC";
        ArrayList<Humor> emocoes = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Humor emocao = new Humor();
                    emocao.setId(rs.getInt("id"));
                    emocao.setIdAluno(rs.getInt("id_aluno"));
                    emocao.setNomeHumor(rs.getString("nomeHumor"));
                    emocao.setDescricao(rs.getString("descricao"));
                    emocao.setData(LocalDate.parse(rs.getString("data"), DB_DATE_FORMATTER)); // <-- CORREÇÃO AQUI
                    emocoes.add(emocao);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar emoções do aluno: " + e.getMessage());
            e.printStackTrace();
        }

        return emocoes;
    }
}