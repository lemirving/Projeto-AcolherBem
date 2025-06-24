package com.project.project_healtheducation.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    private AnchorPane main_anchorPane;
//    private void loadPage(String fxml){
//        try {
//            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaPerfil.fxml");
//            if( resource == null){
//                throw new IOException("FXML não encontrado:"+ fxml);
//            }
//            AnchorPane pane = FXMLLoader.load(resource);
//            main_anchorPane.getChildren().setAll(pane);
//        } catch (IOException e) {
//            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada." + e.getMessage());
//        }
//    }
    private void showError(String titulo, String mensagem){
        javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void handleTelaListaAlunos(ActionEvent event){
        try {
            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaListaAlunos.fxml");
            if (resource == null) {
                throw new IOException("FXML não encontrado: /com/project/project_healtheducation/view/professor/telaListaAlunos.fxml");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane tela = loader.load();

            main_anchorPane.getChildren().clear();
            main_anchorPane.getChildren().add(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
        }
    }


    @FXML
    private void handleListaTurmas(ActionEvent event){
        try {
            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/janelaTurma.fxml");
            if (resource == null) {
                throw new IOException("FXML não encontrado: /com/project/project_healtheducation/view/professor/janelaTurma.fxml");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane tela = loader.load();

            main_anchorPane.getChildren().clear();
            main_anchorPane.getChildren().add(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
        }
    }

    @FXML
    private void handleTelaPerfil(ActionEvent event) {
        try {
            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaPerfil.fxml");
            if (resource == null) {
                throw new IOException("FXML não encontrado: /com/project/project_healtheducation/view/professor/telaPerfil.fxml");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane tela = loader.load();

            main_anchorPane.getChildren().clear();
            main_anchorPane.getChildren().add(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
        }
    }


    @FXML
    private void handleTelaGrafico(ActionEvent event){
        try {
            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaGrafico.fxml");
            if (resource == null) {
                throw new IOException("FXML não encontrado: /com/project/project_healtheducation/view/professor/telaGrafico.fxml");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane tela = loader.load();

            main_anchorPane.getChildren().clear();
            main_anchorPane.getChildren().add(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
        }
    }

    @FXML
    private void handleTelaConfigs(ActionEvent event){
        try {
            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaConfigs.fxml");
            if (resource == null) {
                throw new IOException("FXML não encontrado: /com/project/project_healtheducation/view/professor/telaConfigs.fxml");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            AnchorPane tela = loader.load();

            main_anchorPane.getChildren().clear();
            main_anchorPane.getChildren().add(tela);

            AnchorPane.setTopAnchor(tela, 0.0);
            AnchorPane.setBottomAnchor(tela, 0.0);
            AnchorPane.setLeftAnchor(tela, 0.0);
            AnchorPane.setRightAnchor(tela, 0.0);

        } catch (IOException e) {
            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
        }
    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
//        btnAlunos.setOnAction(e -> System.out.println("Abrir Alunos"));
//        btnPerfil.setOnAction(e -> System.out.println("Abrir Perfil"));
//        btnTurmas.setOnAction(e -> System.out.println("Abrir Turmas"));
//        btnConfig.setOnAction(e -> System.out.println("Abrir Configurações"));
//        btnSair.setOnAction(e -> System.out.println("Sair da tela professor"));

    }

}
