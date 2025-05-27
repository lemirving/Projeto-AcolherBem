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


            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS user (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL UNIQUE," +
                    "senha TEXT NOT NULL," +
                    "type TEXT NOT NULL," +
                    "age INTEGER NOT NULL" +
                    ");";


            String sqlPsicologoTables = "CREATE TABLE IF NOT EXISTS psicologo (" +
                    "id_user INTEGER PRIMARY KEY," +
                    "identificacao TEXT NOT NULL," +
                    "FOREIGN KEY (id_user) REFERENCES user(id)" +
                    ");";


            String sqlProfessorTables = "CREATE TABLE IF NOT EXISTS professor (" +
                    "id_user INTEGER PRIMARY KEY," +
                    "especialidade TEXT NOT NULL," +
                    "FOREIGN KEY (id_user) REFERENCES user(id)" +
                    ");";


            String sqlTurmaTable = "CREATE TABLE IF NOT EXISTS turma (" +
                    "nome TEXT PRIMARY KEY," +
                    "quantidade INTEGER NOT NULL" +
                    ");";


            String sqlTurmaProfessorTable = "CREATE TABLE IF NOT EXISTS turma_professor (" +
                    "id_professor INTEGER NOT NULL," +
                    "nome_turma TEXT NOT NULL," +
                    "PRIMARY KEY (id_professor, nome_turma)," +
                    "FOREIGN KEY (id_professor) REFERENCES professor(id_user) ON DELETE CASCADE," +
                    "FOREIGN KEY (nome_turma) REFERENCES turma(nome) ON DELETE CASCADE" +
                    ");";


            String sqlAlunoTables = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "id_user INTEGER PRIMARY KEY," +
                    "ano_escolar TEXT NOT NULL," +
                    "FOREIGN KEY (id_user) REFERENCES user(id)" +
                    ");";


            String sqlAlunoTurmaTable = "CREATE TABLE IF NOT EXISTS aluno_turma (" +
                    "id_user INTEGER NOT NULL," +
                    "nome_turma TEXT NOT NULL," +
                    "PRIMARY KEY (id_user, nome_turma)," +
                    "FOREIGN KEY (id_user) REFERENCES aluno(id_user) ON DELETE CASCADE," +
                    "FOREIGN KEY (nome_turma) REFERENCES turma(nome) ON DELETE CASCADE" +
                    ");";


            String sqlEmocoesTables = "CREATE TABLE IF NOT EXISTS emocao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_user INTEGER NOT NULL," +
                    "data TEXT NOT NULL," +
                    "descricao TEXT NOT NULL," +
                    "nivel INTEGER NOT NULL," +
                    "FOREIGN KEY (id_user) REFERENCES aluno(id_user) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";


            String sqlDesempenhoTable = "CREATE TABLE IF NOT EXISTS desempenho (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_user INTEGER NOT NULL," +
                    "materia TEXT NOT NULL," +
                    "periodo_atual TEXT NOT NULL," +
                    "nota REAL NOT NULL," +
                    "observacao TEXT NOT NULL," +
                    "data TEXT NOT NULL," +
                    "FOREIGN KEY (id_user) REFERENCES aluno(id_user) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ");";


            statement.execute(sqlCreateTable);
            statement.execute(sqlPsicologoTables);
            statement.execute(sqlProfessorTables);
            statement.execute(sqlTurmaTable);
            statement.execute(sqlTurmaProfessorTable);
            statement.execute(sqlAlunoTables);
            statement.execute(sqlAlunoTurmaTable);
            statement.execute(sqlEmocoesTables);
            statement.execute(sqlDesempenhoTable);

            if (bancoNovo) {
                System.out.println("Banco criado");
            } else {
                System.out.println("Banco já existente acessado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
