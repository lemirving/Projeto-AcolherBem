package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
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


    private boolean validarLogin() {
        String email = textEmail.getText();
        String senha = textSenha.getText();

        // Verifica se algum campo está vazio
        if (email.isEmpty() || senha.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setContentText("Todos os campos devem ser preenchidos.");
            alerta.showAndWait();
            return false;
        }

        // Verifica formato básico do e-mail
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setContentText("Digite um e-mail válido.");
            alerta.showAndWait();
            return false;
        }

        // Verifica se a senha tem pelo menos 4 caracteres
        if (senha.length() < 4) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setContentText("A senha deve ter pelo menos 4 caracteres.");
            alerta.showAndWait();
            return false;
        }
        // Consulta ao banco de dados

//        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
//
//        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:schoolApp.db");
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, email);
//            stmt.setString(2, senha);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return true; // Login válido
//            } else {
//                Alert alerta = new Alert(Alert.AlertType.ERROR);
//                alerta.setHeaderText(null);
//                alerta.setContentText("Email ou senha incorretos.");
//                alerta.showAndWait();
//                return false;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Alert alerta = new Alert(Alert.AlertType.ERROR);
//            alerta.setHeaderText(null);
//            alerta.setContentText("Erro ao acessar o banco de dados.");
//            alerta.showAndWait();
//            return false;
//        }

        return true;
    }




    @FXML
    protected void voltarInicio(ActionEvent event){
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void irParaHome(ActionEvent event) throws IOException{
        if(validarLogin())
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/HomeAluno.fxml");
    }
}