package com.project.project_healtheducation.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class homeProfessorController {

    @FXML
    private JFXButton btnPerfil;
    @FXML
    private JFXButton btnAlunos;
    @FXML
    private JFXButton btnTurmas;
    @FXML
    private JFXButton btnConfig;
    @FXML
    private JFXButton btnSair;

    private AnchorPane mainPane;

    private void loadPage(String fxml){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxml));
            mainPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        btnAlunos.setOnAction(e -> System.out.println("Abrir Alunos"));
        btnPerfil.setOnAction(e -> System.out.println("Abrir Perfil"));
        btnTurmas.setOnAction(e -> System.out.println("Abrir Turmas"));
        btnConfig.setOnAction(e -> System.out.println("Abrir Configurações"));
        btnSair.setOnAction(e -> System.out.println("Sair da tela professor"));

    }

}
