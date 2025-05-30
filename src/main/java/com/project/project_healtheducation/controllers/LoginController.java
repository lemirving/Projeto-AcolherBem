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
    protected void irParaHome(ActionEvent event){
        try{
            if(validarLogin())
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/Home.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}