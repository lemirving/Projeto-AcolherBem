package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeaderText(null);
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/login.fxml");
//            alert.setContentText("Mudou de tela");
//            alert.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}