package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HelloController {

//    @FXML
//    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
//        welcomeText.setText("Welcome to JavaFX Application!");
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/teste.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}