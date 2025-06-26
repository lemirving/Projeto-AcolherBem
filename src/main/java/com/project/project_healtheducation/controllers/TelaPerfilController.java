package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TelaPerfilController {
    public TelaPerfilController(){
        System.out.println("TelaPerfilController carregado!");

    }

    @FXML
    private AnchorPane main_anchorPane;

    @FXML private Label nomeLabel;
    @FXML private Label emailLabel;
    @FXML private Label idadeLabel;

    @FXML
    public void initialize() {
        Usuario usuario = SessaoUsuario.getUsuarioLogado();
        if (usuario != null) {
            nomeLabel.setText("Nome: " + usuario.getNome());
            emailLabel.setText("Email: " + usuario.getEmail());
            idadeLabel.setText("Idade: " + usuario.getIdade());

            // Se quiser mostrar a profissão dinamicamente:
            if (usuario instanceof Professor) {
                System.out.println("Tipo: Professor");
            } else if (usuario instanceof Psicologo) {
                System.out.println("Tipo: Psicólogo");
            } else {
                System.out.println("Tipo: Aluno");
            }
        }
    }


    public void handleEditarPerfil(ActionEvent event) throws IOException {
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/editarPefil.fxml");
    }
}
