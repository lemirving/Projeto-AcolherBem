package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class TelaConfigsController {
    public TelaConfigsController(){
        System.out.println("Tela de configuração");
    }

    @FXML
    private AnchorPane main_anchorPane;



    public void handleEditarPerfil(ActionEvent event) {
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/telaPerfil.fxml");
    }
}
