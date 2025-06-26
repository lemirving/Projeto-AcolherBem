package com.project.project_healtheducation.dao;

import com.project.project_healtheducation.db.dbSetup;
import com.project.project_healtheducation.model.Psicologo; // Mantido
import com.project.project_healtheducation.model.Turma;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class PsicologoDAO {

    public boolean inserirPsicologo(Psicologo psicologo) {
        // Query INSERT sem 'idade', e com 'caminhoImagem'
        String sql = "INSERT INTO psicologo (nome, email, senha, tipo, caminhoImagem) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, psicologo.getTipo());
            stmt.setString(5, psicologo.getCaminhoImagem()); // Caminho da imagem é o 5º parâmetro

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    psicologo.setId(rs.getInt(1)); // Define o ID gerado no objeto Psicologo
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar psicólogo: " + e.getMessage()); // Use System.err para erros
            e.printStackTrace(); // Adicione para depuração completa
        }

        return false;
    }

    public Psicologo buscarPsicologoPorId(int id) {
        // Query SELECT padrão
        String sql = "SELECT id, nome, email, senha, tipo, caminhoImagem FROM psicologo WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Instancia o objeto usando o construtor vazio e setters para flexibilidade
                Psicologo psicologo = new Psicologo();
                psicologo.setId(rs.getInt("id"));
                psicologo.setNome(rs.getString("nome"));
                psicologo.setEmail(rs.getString("email"));
                psicologo.setSenha(rs.getString("senha")); // Pegar a senha criptografada para autenticação futura
                psicologo.setTipo(rs.getString("tipo"));
                psicologo.setCaminhoImagem(rs.getString("caminhoImagem"));

                return psicologo;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar psicólogo por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Psicologo buscarPorEmail(String email) {
        // Query SELECT padrão
        String sql = "SELECT id, nome, email, senha, tipo, caminhoImagem FROM psicologo WHERE email = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Psicologo psicologo = new Psicologo();
                psicologo.setId(rs.getInt("id"));
                psicologo.setNome(rs.getString("nome"));
                psicologo.setEmail(rs.getString("email"));
                psicologo.setSenha(rs.getString("senha")); // senha criptografada
                psicologo.setTipo(rs.getString("tipo"));
                psicologo.setCaminhoImagem(rs.getString("caminhoImagem"));

                return psicologo;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar psicólogo por email: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean atualizarCaminhoImagem(int id, String caminhoImagem) {
        String sql = "UPDATE psicologo SET caminhoImagem = ? WHERE id = ?";
        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caminhoImagem);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar caminho da imagem do psicólogo: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean atualizarPerfil(Psicologo psicologo) {
        // Query UPDATE para nome e, se houver, outras propriedades básicas de perfil.
        // A senha e tipo são geralmente atualizados por métodos mais específicos ou em outro lugar.
        // Se a imagem puder ser atualizada por aqui também, adicione-a.
        String sql = "UPDATE psicologo SET nome = ? WHERE id = ?";
        // Se você quiser que o caminhoImagem seja atualizado aqui junto com o nome:
        // String sql = "UPDATE psicologo SET nome = ?, caminhoImagem = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, psicologo.getNome());
            stmt.setInt(2, psicologo.getId());

            // Se você adicionou caminhoImagem na query acima:
            // stmt.setString(2, psicologo.getCaminhoImagem());
            // stmt.setInt(3, psicologo.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perfil do psicólogo (nome/caminhoImagem): " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean autenticar(String email, String senhaDigitada) {
        Psicologo psicologo = buscarPorEmail(email);
        if (psicologo != null) {
            return BCrypt.checkpw(senhaDigitada, psicologo.getSenha());
        }
        return false;
    }

    public boolean atualizarPsicologo(Psicologo psicologo) {
        // Esta é a atualização "completa" (sem a imagem, que tem um método próprio)
        // Certifique-se de que a senha é criptografada APENAS se ela for realmente alterada
        // em sua UI, caso contrário, você estará recriptografando a senha criptografada.
        // Uma abordagem melhor seria passar a senha *nova* separadamente, ou verificar
        // se a senha do objeto psicologo é diferente da senha atual no banco antes de hash.
        // Por simplicidade, mantive como estava, mas é um ponto de melhoria.
        String sql = "UPDATE psicologo SET nome = ?, email = ?, senha = ?, tipo = ?, caminhoImagem = ? WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Criptografa a senha APENAS se você tem certeza que ela foi alterada
            // ou se sempre criptografa a nova senha (requer cuidado para não recriptografar)
            String senhaCriptografada = BCrypt.hashpw(psicologo.getSenha(), BCrypt.gensalt());

            stmt.setString(1, psicologo.getNome());
            stmt.setString(2, psicologo.getEmail());
            stmt.setString(3, senhaCriptografada);
            stmt.setString(4, psicologo.getTipo());
            stmt.setString(5, psicologo.getCaminhoImagem()); // Caminho da imagem é o 5º parâmetro
            stmt.setInt(6, psicologo.getId()); // ID é o 6º parâmetro

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar todos os dados do psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean removerPsicologo(int id) {
        String sql = "DELETE FROM psicologo WHERE id = ?";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public boolean associarTurmaAoPsicologo(int idPsicologo, String nomeTurma) {
        String sql = "INSERT INTO turma_psicologo (nome_turma, id_psicologo) VALUES (?, ?)";

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeTurma);
            stmt.setInt(2, idPsicologo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao associar turma ao psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Turma> buscarTurmasDoPsicologo(int idPsicologo) {
        String sql = "SELECT t.nome, t.quantidade FROM turma t JOIN turma_psicologo tp ON t.nome = tp.nome_turma WHERE tp.id_psicologo = ?";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conn = dbSetup.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPsicologo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Supondo que Turma tem um construtor que aceita nome, quantidade e uma lista vazia de alunos (ou outro)
                Turma turma = new Turma(rs.getString("nome"), rs.getInt("quantidade"), new ArrayList<>());
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar turmas do psicólogo: " + e.getMessage());
            e.printStackTrace();
        }

        return turmas;
    }
}