package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.TurmaDAO;
import com.project.project_healtheducation.model.Aluno;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;

import javax.security.auth.callback.Callback;

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
        colNomeAluno.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNome())
        );
        colMatricula.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMatricula())
        );
        colTurma.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNomeTurma())
        );
        colHumor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHumorAtual())
        );

        tabelaAlunos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colNomeAluno.setMinWidth(80);
        colTurma.setMinWidth(100);
        colMatricula.setMinWidth(100);
        colHumor.setMinWidth(100);

        carregarTurma();
    }

    private void carregarTurma(){
        List<Aluno> listaAlunos = dao.listarTodosComUltimoHumor();
        System.out.println("Qtd alunos: " + listaAlunos.size());
        listaAlunos.forEach(a -> System.out.println(a.getNome() + " - " + a.getHumorAtual()));
        tabelaAlunos.getItems().setAll(listaAlunos);
    }
    public void refreshData() {
        System.out.println("Atualizando dados da tabela de alunos...");
        carregarTurma();
    }


}
