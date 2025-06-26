package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.dao.PsicologoDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.model.Usuario; // IMPORTE a interface Usuario
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField textEmail;
    @FXML
    PasswordField textSenha;

    @FXML
    protected void tentarLogin(ActionEvent event) throws IOException {
        String email = textEmail.getText();
        String senhaDigitada = textSenha.getText(); // Renomeado para clareza

        // 1. Validações Iniciais
        if (email.isEmpty() || senhaDigitada.isEmpty()) {
            mostrarAlerta("Preencha todos os campos.");
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Digite um e-mail válido.");
            return;
        }
        // A validação de tamanho de senha é geralmente feita antes da criptografia no cadastro.
        // No login, o BCrypt.checkpw cuidará da comparação, mas 6 caracteres é uma boa prática para alerta inicial.
        if (senhaDigitada.length() < 6) {
            mostrarAlerta("A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        // 2. Tentar Autenticação e Armazenar como tipo Usuario
        Usuario usuarioAutenticado = null;

        // Tenta como Aluno
        AlunoDAO alunoDAO = new AlunoDAO();
        if (alunoDAO.autenticar(email, senhaDigitada)) { // Usa o método autenticar do AlunoDAO
            usuarioAutenticado = alunoDAO.buscarPorEmail(email); // Busque o objeto completo após autenticar
        }

        if (usuarioAutenticado == null) {
            ProfessorDAO professorDAO = new ProfessorDAO();
            if (professorDAO.autenticar(email, senhaDigitada)) { // Usa o método autenticar do ProfessorDAO
                usuarioAutenticado = professorDAO.buscarProfessorPorEmail(email); // Busque o objeto completo
            }
        }

        // Se ainda não autenticado, tenta como Psicologo
        if (usuarioAutenticado == null) {
            PsicologoDAO psicologoDAO = new PsicologoDAO();
            if (psicologoDAO.autenticar(email, senhaDigitada)) { // Usa o método autenticar do PsicologoDAO
                usuarioAutenticado = psicologoDAO.buscarPorEmail(email); // Busque o objeto completo
            }
        }

        if (usuarioAutenticado != null) {
            SessaoUsuario.setUsuarioLogado(usuarioAutenticado);

            if (usuarioAutenticado instanceof Aluno) {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
            } else if (usuarioAutenticado instanceof Professor) {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/professor/telaProfessores.fxml");
            } else if (usuarioAutenticado instanceof Psicologo) {
                // TODO: Adicionar a tela inicial do Psicólogo
                mostrarAlerta("Login de Psicólogo realizado! Redirecionar para tela de Psicólogo (ainda não implementada).");
              }

            textEmail.clear();
            textSenha.clear();
        } else {
            // Se nenhum usuário foi autenticado
            mostrarAlerta("E-mail ou senha incorretos. Tente novamente.");
            textSenha.clear();
        }
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    protected void voltarInicio(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }
}