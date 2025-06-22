package com.project.project_healtheducation.controllers.home;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeAlunoController {

    @FXML
    protected void btnTelaInicial(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
    }
    @FXML
    protected void registrarEmocoesScreen(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/RegistrarEmocaoAluno.fxml");
    }

    @FXML
    protected void btnVoltarPaginaInicio(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
    }

}
