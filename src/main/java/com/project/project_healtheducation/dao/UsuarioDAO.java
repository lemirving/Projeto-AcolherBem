package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.model.*;
import com.project.project_healtheducation.db.dbSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {

    public static String encodePassword(Usuario usuario){
        return BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
    }

    public static Aluno carregarAluno(int id, String name, String email, String password){
        String sql = "SELECT * FROM aluno WHERE id_user = ?";
        try(Connection connection = dbSetup.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                String anoEscolar = result.getString("ano_escolar");

                String turma = null;
                return new Aluno(email, name, id, password, anoEscolar, turma);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Professor carregarProfessor(int id, String name, String email, String password) {
        String sqlProf = "SELECT * FROM professor WHERE id_user = ?";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection connection = dbSetup.getConnection();
             PreparedStatement stmtProf = connection.prepareStatement(sqlProf)) {

            stmtProf.setInt(1, id);
            ResultSet result = stmtProf.executeQuery();

            if (result.next()) {
                String especialidade = result.getString("especialidade");

                // Buscar turmas do professor via tabela turma_professor + turma
                String sqlTurmas = "SELECT t.nome, t.quantidade FROM turma_professor tp " +
                        "JOIN turma t ON tp.nome_turma = t.nome WHERE tp.id_professor = ?";
                try (PreparedStatement stmtTurmas = connection.prepareStatement(sqlTurmas)) {
                    stmtTurmas.setInt(1, id);
                    ResultSet resultTurmas = stmtTurmas.executeQuery();

                    while (resultTurmas.next()) {
                        String nomeTurma = resultTurmas.getString("nome");
                        int quantidade = resultTurmas.getInt("quantidade");
                        turmas.add(new Turma(nomeTurma, quantidade, new ArrayList<>()));
                    }
                }

                return new Professor(email, name, id, password, especialidade, turmas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Psicologo carregarPsicologo(int id, String name, String email, String password) {

        String sql = "SELECT * FROM psicologo WHERE id_user = ?";
        try (Connection connection = dbSetup.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                String identificacao = result.getString("identificacao");

                return new Psicologo(email, name, id, password, identificacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void inserirUsuario(Usuario usuario){
        String sql = "INSERT INTO user (name, email, senha, type, age) VALUES (?, ?, ?, ?, ?)";

        String encodedPassword = encodePassword(usuario);

        try(Connection connection = dbSetup.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, usuario.getName());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, encodedPassword);
            statement.setString(4, usuario.getType().toString());
            statement.setInt(5, usuario.getAge());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                usuario.setId(keys.getInt(1));

            if (usuario instanceof Aluno aluno) {
                String sqlAluno = "INSERT INTO aluno (id_user, ano_escolar) VALUES (?, ?)";
                try (PreparedStatement stmtAluno = connection.prepareStatement(sqlAluno)) {
                    stmtAluno.setInt(1, usuario.getId());
                    stmtAluno.setString(2, aluno.getAnoEscolar());
                    stmtAluno.executeUpdate();

                }
            } else if (usuario instanceof Professor professor) {
                String sqlProf = "INSERT INTO professor (id_user, especialidade) VALUES (?, ?)";
                try (PreparedStatement stmtProf = connection.prepareStatement(sqlProf)) {
                    stmtProf.setInt(1, usuario.getId());
                    stmtProf.setString(2, professor.getEspecialidade());
                    stmtProf.executeUpdate();

                    String sqlTurmaProfessor = "INSERT INTO turma_professor (id_professor, nome_turma) VALUES (?, ?)";
                    try (PreparedStatement stmtTurmaProf = connection.prepareStatement(sqlTurmaProfessor)) {
                        for (Turma turma : professor.getTurmasLecionadas()) {
                            stmtTurmaProf.setInt(1, usuario.getId());
                            stmtTurmaProf.setString(2, turma.getNomeTurma());
                            stmtTurmaProf.executeUpdate();
                        }
                    }
                }
            } else if (usuario instanceof Psicologo psicologo) {
                String sqlPsi = "INSERT INTO psicologo (id_user, identificacao) VALUES (?, ?)";
                try (PreparedStatement stmtPsi = connection.prepareStatement(sqlPsi)) {
                    stmtPsi.setInt(1, usuario.getId());
                    stmtPsi.setString(2, psicologo.getIdenficacaoProfissional());
                    stmtPsi.executeUpdate();
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = dbSetup.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("senha");
                String type = result.getString("type");

                switch (type.toUpperCase()) {
                    case "ALUNO":
                        return carregarAluno(id, name, email, password);
                    case "PROFESSOR":
                        return carregarProfessor(id, name, email, password);
                    case "PSICOLOGO":
                        return carregarPsicologo(id, name, email, password);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void removerUsuario(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (Connection connection = dbSetup.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            // Se você definiu ON DELETE CASCADE nas FK, as tabelas filhas serão removidas automaticamente
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        try (Connection connection = dbSetup.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("senha");
                String type = result.getString("type");

                switch (type.toUpperCase()) {
                    case "ALUNO":
                        return carregarAluno(id, name, email, password);
                    case "PROFESSOR":
                        return carregarProfessor(id, name, email, password);
                    case "PSICOLOGO":
                        return carregarPsicologo(id, name, email, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarUsuario(Usuario usuario) {
        String sqlUser = "UPDATE user SET name = ?, email = ?, senha = ?, age = ? WHERE id = ?";
        try (Connection connection = dbSetup.getConnection();
             PreparedStatement stmtUser = connection.prepareStatement(sqlUser)) {
            stmtUser.setString(1, usuario.getName());
            stmtUser.setString(2, usuario.getEmail());
            stmtUser.setString(3, encodePassword(usuario));  // Ou só atualize se a senha mudou
            stmtUser.setInt(4, usuario.getAge());
            stmtUser.setInt(5, usuario.getId());
            stmtUser.executeUpdate();

            if (usuario instanceof Aluno aluno) {
                String sqlAluno = "UPDATE aluno SET ano_escolar = ? WHERE id_user = ?";
                try (PreparedStatement stmtAluno = connection.prepareStatement(sqlAluno)) {
                    stmtAluno.setString(1, aluno.getAnoEscolar());
                    stmtAluno.setInt(2, usuario.getId());
                    stmtAluno.executeUpdate();
                }
            } else if (usuario instanceof Professor professor) {
                String sqlProf = "UPDATE professor SET especialidade = ? WHERE id_user = ?";
                try (PreparedStatement stmtProf = connection.prepareStatement(sqlProf)) {
                    stmtProf.setString(1, professor.getEspecialidade());
                    stmtProf.setInt(2, usuario.getId());
                    stmtProf.executeUpdate();
                }

                String sqlDeleteTurmas = "DELETE FROM turma_professor WHERE id_professor = ?";
                try (PreparedStatement stmtDel = connection.prepareStatement(sqlDeleteTurmas)) {
                    stmtDel.setInt(1, usuario.getId());
                    stmtDel.executeUpdate();
                }

                String sqlInsertTurma = "INSERT INTO turma_professor (id_professor, nome_turma) VALUES (?, ?)";
                try (PreparedStatement stmtInsert = connection.prepareStatement(sqlInsertTurma)) {
                    for (Turma turma : professor.getTurmasLecionadas()) {
                        stmtInsert.setInt(1, usuario.getId());
                        stmtInsert.setString(2, turma.getNomeTurma());
                        stmtInsert.executeUpdate();
                    }
                }
            } else if (usuario instanceof Psicologo psicologo) {
                String sqlPsi = "UPDATE psicologo SET identificacao = ? WHERE id_user = ?";
                try (PreparedStatement stmtPsi = connection.prepareStatement(sqlPsi)) {
                    stmtPsi.setString(1, psicologo.getIdenficacaoProfissional());
                    stmtPsi.setInt(2, usuario.getId());
                    stmtPsi.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
