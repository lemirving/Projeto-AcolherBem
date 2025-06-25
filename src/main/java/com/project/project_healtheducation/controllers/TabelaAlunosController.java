package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.TurmaDAO;
import com.project.project_healtheducation.model.Aluno;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaTurmaController {

    private TurmaDAO turmaDAO = new TurmaDAO();
    @FXML
    private TableColumn<Aluno, String> colNomeAluno;
    @FXML
    private TableColumn <Aluno,String> colMatricula;
    @FXML
    private TableColumn <Aluno, String> colHumor;

    @FXML
    public void initialize() {
        colNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colHumor.setCellValueFactory(new PropertyValueFactory<>("humor"));

        carregarTurma("2ºB"); // nome exemplo
    }
}
