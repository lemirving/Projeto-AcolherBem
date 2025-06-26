package com.project.project_healtheducation.controllers.home;

import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeAlunoController {

    @FXML
    private AnchorPane main_anchorPane;
    @FXML private Label labelSaudacao;

    @FXML
    private void initialize(){
        Usuario usuario = SessaoUsuario.getUsuarioLogado();
        if (usuario != null) {
            labelSaudacao.setText("Olá, " + usuario.getNome() + "! Como você se sente hoje?");
        } else {
            labelSaudacao.setText("Olá! Como você se sente hoje?");
        }
    }

    @FXML
    private void handleTelaPerfil() {
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/telaPerfil.fxml");
    }

    @FXML
    private void handleTelaConfigs(){
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/telaConfigs.fxml");

    }




    @FXML
    protected void handleHomeInicio(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
    }
    @FXML
    protected void registrarEmocoesScreen(){
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/RegistrarEmocaoAluno.fxml");
    }

    @FXML
    protected void btnVoltarPaginaInicio(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }

}
