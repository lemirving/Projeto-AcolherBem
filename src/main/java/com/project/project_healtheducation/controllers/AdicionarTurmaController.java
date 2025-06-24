package com.project.project_healtheducation.controllers;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.project.project_healtheducation.dao.TurmaDAO;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class AdicionarTurmaController {
    @FXML private StackPane rootPane;
    @FXML private JFXDialog dialogCadastro;
    @FXML private JFXTextField campoNomeTurma;
    @FXML private JFXTextArea campoAlunos;

    @FXML
    private void abrirDialogCadastro() {
        dialogCadastro.show(rootPane);
    }

    @FXML
    private void fecharDialog() {
        dialogCadastro.close();
    }

    @FXML
    private void salvarTurma() {
        String nome = campoNomeTurma.getText();
        String[] alunos = campoAlunos.getText().split("\\n");

        // Aqui você adicionaria dinamicamente a turma ao layout principal
        System.out.println("Nova turma: " + nome);
        for (String aluno : alunos)
            System.out.println(" - " + aluno);

        dialogCadastro.close();
    }


}
