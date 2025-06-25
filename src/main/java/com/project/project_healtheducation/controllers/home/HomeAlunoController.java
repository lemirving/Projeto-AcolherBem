package com.project.project_healtheducation.controllers.home;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeAlunoController {

    @FXML
    private AnchorPane main_anchorPane;

    @FXML
    private void handleTelaPerfil(ActionEvent event) {
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/telaPerfil.fxml");
    }

    @FXML
    private void handleTelaConfigs(ActionEvent event){
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/telaConfigs.fxml");

    }




    @FXML
    protected void handleTelaInicial(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }
    @FXML
    protected void registrarEmocoesScreen(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/RegistrarEmocaoAluno.fxml");
    }

    @FXML
    protected void btnVoltarPaginaInicio(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }

}
