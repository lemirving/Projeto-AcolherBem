package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField textEmail;
    @FXML
    PasswordField textSenha;


    private boolean validarLogin() {
        String email = textEmail.getText();
        String senha = textSenha.getText();

        // Verifica se algum campo está vazio
        if (email.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Todos os campos devem ser preenchidos.");
            return false;
        }

        // Verifica formato básico do e-mail
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Digite um e-mail válido.");
            return false;
        }

        // Verifica se a senha tem pelo menos 4 caracteres
        if (senha.length() < 4) {
            mostrarAlerta("A senha deve ter pelo menos 4 caracteres.");
            return false;
        }

        // Autenticação
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean sucesso = professorDAO.autenticar(email, senha);

        if (!sucesso) {
            mostrarAlerta("E-mail ou senha incorretos.");
            return false;
        }

        System.out.println(sucesso);

        return true;
    }

    // Método auxiliar para reduzir repetição
    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }


    @FXML
    protected void voltarInicio(ActionEvent event) throws IOException{
        if(validarLogin()   )
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");

    }

    @FXML
    protected void irParaHome(ActionEvent event) throws IOException{
        if(validarLogin())
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
    }
}