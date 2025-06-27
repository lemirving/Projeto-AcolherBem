package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.HumorDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Humor;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class TelaListaEmocoesController {

    @FXML private AnchorPane main_anchorPane;

    @FXML
    private void handleTelaListaAlunos() throws IOException {
        ChangeScreen.setHalfScreen(main_anchorPane,"/com/project/project_healtheducation/view/professor/telaListaAlunos.fxml");
    }
    @FXML private TableView<Humor> tabelaEmocoes;
    @FXML private TableColumn<Humor, String> colNomeHumor;
    @FXML private TableColumn<Humor, String> colDescricao;
    @FXML private TableColumn<Humor, String> colData;

    private HumorDAO humorDAO = new HumorDAO();

    @FXML
    public void initialize() {
        colNomeHumor.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNomeHumor())
        );
        colDescricao.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescricao())
        );
        colData.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getData().toString())
        );

        tabelaEmocoes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        carregarEmocoesDoAluno();
    }


    private void carregarEmocoesDoAluno() {
        Aluno alunoLogado = (Aluno) SessaoUsuario.getUsuarioLogado();
        if (alunoLogado == null) {
            System.out.println("Nenhum aluno logado.");
            return;
        }

        System.out.println("Aluno logado: " + alunoLogado.getNome() + " (ID: " + alunoLogado.getId() + ")");

        List<Humor> emocoes = humorDAO.listarEmocoesDoAluno(alunoLogado.getId());
        System.out.println("Qtd emoções encontradas: " + emocoes.size());

        for (Humor h : emocoes) {
            System.out.println(h.getData() + " - " + h.getNomeHumor() + " - " + h.getDescricao());
        }

        tabelaEmocoes.getItems().setAll(emocoes);
    }



}
