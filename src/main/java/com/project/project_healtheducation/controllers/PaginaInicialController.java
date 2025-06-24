package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PaginaInicialController {

    @FXML protected void loginScreen(ActionEvent event) {
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/login.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML protected void cadastroScreen(ActionEvent event){
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/cadastro.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void RegistrarSentimentoScreen(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/RegistrarEmocaoAluno.fxml");
    }

    @FXML
    protected void btnSairApp(){
        System.exit(1);
    }
}