package com.project.project_healtheducation.controllers.home;

import com.jfoenix.controls.JFXButton;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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


    @FXML
    protected void handleTelaListaAlunos() throws IOException {
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/professor/telaListaAlunos.fxml");
    }

    // /com/project/project_healtheducation/view/professor/telaPerfil.fxml
    @FXML
    protected void handleTelaPerfil(ActionEvent event) {
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/professor/telaPerfil.fxml");
    }


    @FXML
    protected void handleTelaGrafico(ActionEvent event){
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/professor/telaGrafico.fxml");
    }

    /*
    *
    * handleTelaAluno(){
    * ChangeScreen.sethalfScreen(main_anchorPane, caminho da tela de add emoção);
    * */
    @FXML
    private void handleTelaConfigs(ActionEvent event){
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/professor/telaConfigs.fxml");

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
