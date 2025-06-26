package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditarPerfilController {
    @FXML private TextField nomeField;
    @FXML private TextField emailField;
    @FXML private TextField idadeField;

    private Usuario usuario;

    @FXML
    public void initialize() {
        usuario = SessaoUsuario.getUsuarioLogado();

        if (usuario != null) {
            nomeField.setText(usuario.getNome());
            emailField.setText(usuario.getEmail());
            idadeField.setText(String.valueOf(usuario.getIdade()));
        }
    }


    public void salvarAlteracoes(ActionEvent event) {

    }
}
