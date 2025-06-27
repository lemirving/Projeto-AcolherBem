package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.model.Aluno;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaListaAlunosController {
    public TelaListaAlunosController(){

    }

    @FXML
    private TableView<Aluno> tabelaAlunos;

    @FXML
    private TableColumn<Aluno, String> colNome;
    @FXML
    private TableColumn<Aluno, String> colEmail;
    @FXML
    private TableColumn<Aluno, String> colMatricula;
    @FXML
    private TableColumn<Aluno, String> colHumor;

    @FXML
    public void initialize() {
        tabelaAlunos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colHumor.setCellValueFactory(new PropertyValueFactory<>("humor"));


    }
}
