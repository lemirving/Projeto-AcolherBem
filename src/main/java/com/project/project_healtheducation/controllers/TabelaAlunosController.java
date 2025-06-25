package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.TurmaDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Turma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class TabelaAlunosController {

    private TurmaDAO turmaDAO = new TurmaDAO();

    @FXML private TableView<Aluno> tabelaAlunos;
    @FXML private TableColumn<Aluno, String> colNomeAluno;
    @FXML private TableColumn<Aluno, String> colTurma;
    @FXML private TableColumn<Aluno, String> colMatricula;
    @FXML private TableColumn<Aluno, String> colHumor;

    private AlunoDAO dao = new AlunoDAO();
    @FXML
    public void initialize() {
        colNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colTurma.setCellValueFactory(new PropertyValueFactory<>("turmaNome"));
        colHumor.setCellValueFactory(new PropertyValueFactory<>("humor"));
        tabelaAlunos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colNomeAluno.setMinWidth(80);
        colTurma.setMinWidth(100);
        colMatricula.setMinWidth(100);
        colHumor.setMinWidth(100);

    }
    private void carregarTurma(String nomeTurma){
        List<Aluno> listaAlunos = dao.listarTodosComUltimoHumor();
        tabelaAlunos.getItems().setAll(listaAlunos);


    }

}
