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
    TextField textNome;
    @FXML
    PasswordField textSenha;


    private boolean validarLogin(){

        String email = textNome.getText();
        String senha = textSenha.getText();

        if(email.isEmpty() || senha.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setContentText("Os campos devem ser preenchidos");
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
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void irParaHome(ActionEvent event){
        try{
            if(validarLogin())
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}