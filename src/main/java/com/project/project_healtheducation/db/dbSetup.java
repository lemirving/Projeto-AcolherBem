package com.project.project_healtheducation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbSetup {
    private static final String URL = "jdbc:sqlite:schoolApp.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTables() {
        boolean bancoNovo = !new java.io.File("schoolApp.db").exists();

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON;");

            // Tabela turma
            String sqlTurmaTable = "CREATE TABLE IF NOT EXISTS turma (" +
                    "nome TEXT PRIMARY KEY," +
                    "quantidade INTEGER NOT NULL" +
                    ");";

            // Tabela aluno (com campo de imagem)
            String sqlAlunoTable = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "idade TEXT NOT NULL," +
                    "matricula TEXT," +
                    "turma TEXT," +
                    "imagem_perfil TEXT" +
                    ");";

            // Tabela professor (corrigida)
            String sqlProfessorTable = "CREATE TABLE IF NOT EXISTS professor (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +
                    "idade TEXT NOT NULL," +
                    "caminhoImagem TEXT" +
                    ");";

            // Tabela psicologo (corrigida)
            String sqlPsicologoTable = "CREATE TABLE IF NOT EXISTS psicologo (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "tipo TEXT NOT NULL," +      // <- adicionada vírgula aqui
                    "idade TEXT NOT NULL," +
                    "caminhoImagem TEXT" +
                    ");";

            // Tabela emoção
            String sqlEmocaoTable = "CREATE TABLE IF NOT EXISTS emocao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomeHumor TEXT NOT NULL," +
                    "id_aluno INTEGER NOT NULL," +
                    "data TEXT NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "FOREIGN KEY (id_aluno) REFERENCES aluno(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";

            // Associação professor <-> turma
            String sqlTurmaProfessorTable = "CREATE TABLE IF NOT EXISTS turma_professor (" +
                    "nome_turma TEXT NOT NULL," +
                    "id_professor INTEGER NOT NULL," +
                    "PRIMARY KEY (nome_turma, id_professor)," +
                    "FOREIGN KEY (nome_turma) REFERENCES turma(nome) ON DELETE CASCADE," +
                    "FOREIGN KEY (id_professor) REFERENCES professor(id) ON DELETE CASCADE" +
                    ");";

            // Associação psicólogo <-> turma
            String sqlTurmaPsicologoTable = "CREATE TABLE IF NOT EXISTS turma_psicologo (" +
                    "nome_turma TEXT NOT NULL," +
                    "id_psicologo INTEGER NOT NULL," +
                    "PRIMARY KEY (nome_turma, id_psicologo)," +
                    "FOREIGN KEY (nome_turma) REFERENCES turma(nome) ON DELETE CASCADE," +
                    "FOREIGN KEY (id_psicologo) REFERENCES psicologo(id) ON DELETE CASCADE" +
                    ");";

            // Executar todas as criações
            statement.execute(sqlTurmaTable);
            statement.execute(sqlAlunoTable);
            statement.execute(sqlProfessorTable);
            statement.execute(sqlPsicologoTable);
            statement.execute(sqlEmocaoTable);
            statement.execute(sqlTurmaProfessorTable);
            statement.execute(sqlTurmaPsicologoTable);

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
