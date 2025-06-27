package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Turma;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class ProfessorDAO {

    public boolean inserirProfessor(Professor professor) {
        // CORRIGIDO: Removida 'idade' da query SQL. Agora são 5 placeholders.
        String sql = "INSERT INTO professor (nome, email, senha, tipo, caminhoImagem) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String senhaCriptografada = BCrypt.hashpw(professor.getSenha(), BCrypt.gensalt());

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, professor.getTipo());
            // REMOVIDO: stmt.setString(5, professor.getIdade());
            // CORRIGIDO: O caminhoImagem agora é o 5º parâmetro
            stmt.setString(5, professor.getCaminhoImagem()); // novo


            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    professor.setId(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }

        return false;
    }

    public Professor buscarProfessorPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("tipo"),
                        rs.getString("caminhoImagem")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }

        return null;
    }

    public Professor buscarProfessorPorEmail(String email) {
        String sql = "SELECT * FROM professor WHERE email = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"), rs.getString("tipo"), rs.getString("caminhoImagem"));
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getString("senha")); // senha criptografada
                professor.setTipo(rs.getString("tipo"));
                professor.setTipo(rs.getString("caminhoImagem"));

                // REMOVIDO: professor.setIdade(rs.getString("idade"));
                professor.setCaminhoImagem(rs.getString("caminhoImagem")); // se houver esse campo

                return professor;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor por email: " + e.getMessage());
        }

        return null;
    }


    public boolean autenticar(String email, String senhaDigitada) {
        Professor professor = buscarProfessorPorEmail(email);
        if (professor != null) {
            return BCrypt.checkpw(senhaDigitada, professor.getSenha());
        }
        return false;
    }

    public boolean atualizarProfessor(Professor professor) {
        // Esta query não incluía idade, então está OK.
        String sql = "UPDATE professor SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String senhaCriptografada = BCrypt.hashpw(professor.getSenha(), BCrypt.gensalt());

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, professor.getTipo());
            stmt.setInt(5, professor.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }

        return false;
    }

    public boolean removerProfessor(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover professor: " + e.getMessage());
        }

        return false;
    }

    public boolean atualizarPerfil(Professor professor) {
        // CORRIGIDO: Removida 'idade' da query UPDATE
        String sql = "UPDATE professor SET nome = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            // REMOVIDO: stmt.setString(2, professor.getIdade());
            stmt.setInt(2, professor.getId()); // Agora é o 2º parâmetro
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar perfil do professor: " + e.getMessage());
        }
        return false;
    }

    public boolean atualizarCaminhoImagem(int id, String caminhoImagem) {
        String sql = "UPDATE professor SET caminhoImagem = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhoImagem);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar imagem do professor: " + e.getMessage());
        }
        return false;
    }

    public boolean associarTurmaAoProfessor(int idProfessor, String nomeTurma) {
        String sql = "INSERT INTO turma_professor (nome_turma, id_professor) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idProfessor);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao associar turma ao professor: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<Turma> buscarTurmasDoProfessor(int idProfessor) {
        String sql = "SELECT t.nome, t.quantidade FROM turma t JOIN turma_professor tp ON t.nome = tp.nome_turma WHERE tp.id_professor = ?";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfessor);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Turma turma = new Turma(rs.getString("nome"), rs.getInt("quantidade"));
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar turmas do professor: " + e.getMessage());
        }

        return turmas;
    }

}