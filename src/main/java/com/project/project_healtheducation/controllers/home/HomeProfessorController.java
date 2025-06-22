package com.project.project_healtheducation.controllers.home;

import com.jfoenix.controls.JFXButton;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeProfessorController {

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

    @FXML
    protected void btnTelaInicial(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
    }

}
