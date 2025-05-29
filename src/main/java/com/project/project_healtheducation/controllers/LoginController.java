package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
    @FXML
    protected void voltarInicio(ActionEvent event){
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
