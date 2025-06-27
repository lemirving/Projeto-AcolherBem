package com.project.project_healtheducation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbSetup {
    private static final String URL = "jdbc:sqlite:bancoDeDados.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTables() {
        boolean bancoNovo = !new java.io.File("bancoDeDados.db").exists();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON;");

            String sqlAlunoTable = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "idade TEXT NOT NULL," +
                    "matricula TEXT," +
                    "turma TEXT," +
                    "caminhoImagem TEXT" +
                    ");";

            // Tabela professor (corrigida)
            String sqlProfessorTable = "CREATE TABLE IF NOT EXISTS professor (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "caminhoImagem TEXT" +
                    ");";

            String sqlPsicologoTable = "CREATE TABLE IF NOT EXISTS psicologo (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "caminhoImagem TEXT" +
                    ");";

            String sqlEmocaoTable = "CREATE TABLE IF NOT EXISTS emocao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomeHumor TEXT NOT NULL," +
                    "id_aluno INTEGER NOT NULL," +
                    "data TEXT NOT NULL," +
                    "descricao TEXT," +
                    "FOREIGN KEY (id_aluno) REFERENCES aluno(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

            statement.execute(sqlAlunoTable);
            statement.execute(sqlProfessorTable);
            statement.execute(sqlPsicologoTable);
            statement.execute(sqlEmocaoTable);

            if (bancoNovo) {
                System.out.println("Banco criado com nova estrutura.");
            } else {
                System.out.println("Banco já existente acessado com nova estrutura.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
