package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Humor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HumorDAO {

    public boolean inserirEmocao(Humor emocao) {
        String sql = "INSERT INTO emocao (nomeHumor, id_aluno, data, descricao) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, emocao.getNomeHumor());
            stmt.setInt(2, emocao.getIdAluno());
            stmt.setDate(3, Date.valueOf(emocao.getData()));
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
                            rs.getString("nomeHumor"),
                            rs.getInt("id_aluno"),
                            rs.getDate("data").toLocalDate(),
                            rs.getString("descricao")
                    );
                    emocao.setId(rs.getInt("id"));
                    emocoes.add(emocao);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar emoções do aluno: " + e.getMessage());
        }

        return emocoes;
    }

    public ArrayList<Humor> listarEmocoesDoAluno(int idAluno) {
        String sql = "SELECT * FROM emocao WHERE id_aluno = ? ORDER BY data DESC";
        ArrayList<Humor> emocoes = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Humor emocao = new Humor(
                            rs.getString("nomeHumor"),
                            rs.getInt("id_aluno"),
                            rs.getDate("data").toLocalDate(),
                            rs.getString("descricao")
                    );
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
    public Map<String, Map<LocalDate, Integer>> getContagemEmocoesPorData(LocalDate dataInicio, LocalDate dataFim) {
        // Usamos TreeMap para garantir que as datas dentro de cada emoção estejam ordenadas
        // (importante para a plotagem do gráfico de linhas)
        Map<String, Map<LocalDate, Integer>> emocoesPorData = new HashMap<>();

        // Consulta SQL para agrupar e contar emoções por dia e por tipo de humor
        String sql = "SELECT nomeHumor, DATE(data) as data_registro, COUNT(*) as contagem " +
                "FROM emocao " + // Sua tabela é 'emocao'
                "WHERE data BETWEEN ? AND ? " +
                "GROUP BY nomeHumor, DATE(data) " +
                "ORDER BY nomeHumor, data_registro"; // Ordena para melhor processamento

        try (Connection conn = dbSetup.getConnection(); // Usando seu dbSetup
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(dataInicio)); // Converte LocalDate para java.sql.Date
            stmt.setDate(2, Date.valueOf(dataFim));   // Converte LocalDate para java.sql.Date

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nomeHumor = rs.getString("nomeHumor");
                    LocalDate data = rs.getDate("data_registro").toLocalDate(); // Converte java.sql.Date para LocalDate
                    int contagem = rs.getInt("contagem");

                    // Adiciona o humor ao mapa principal se ainda não existir, inicializando com um TreeMap
                    emocoesPorData.computeIfAbsent(nomeHumor, k -> new TreeMap<>());
                    // Adiciona a contagem para a data específica do humor
                    emocoesPorData.get(nomeHumor).put(data, contagem);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contagem de emoções por data para o gráfico: " + e.getMessage());
            e.printStackTrace();
            // Em um ambiente de produção, você pode querer lançar uma exceção customizada aqui
            // para que o Controller possa lidar com ela (ex: exibir uma mensagem de erro ao usuário).
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
            System.out.println("Erro ao atualizar emoção: " + e.getMessage());
        }

        return false;
    }
}
