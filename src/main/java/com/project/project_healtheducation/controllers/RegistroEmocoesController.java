package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.HumorDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Humor;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*; // Importa todos os controles Alert, ButtonType, Label, Slider
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class RegistroEmocoesController {

    @FXML private Slider sliderSentimento;
    @FXML private Label labelSentimentoEscolhido;
    @FXML private TextArea textAreaDescricao; // <-- Adicione esta linha

    private static final String HUMOR_PESSIMO = "Péssimo";
    private static final String HUMOR_TRISTE = "Triste";
    private static final String HUMOR_NEUTRO = "Neutro";
    private static final String HUMOR_BEM = "Bem";
    private static final String HUMOR_FELIZ = "Feliz";
    private static final String HUMOR_RADIANTE = "Radiante";


    @FXML
    protected void initialize() {
        if (sliderSentimento != null && labelSentimentoEscolhido != null) {
            sliderSentimento.valueProperty().addListener((obs, oldVal, newVal) -> {
                labelSentimentoEscolhido.setText("Estado atual: " + getDescricaoSentimento(newVal.intValue()));
            });
            labelSentimentoEscolhido.setText("Estado atual: " + getDescricaoSentimento((int) sliderSentimento.getValue()));
        }

        if (textAreaDescricao != null) {
            textAreaDescricao.setText("");
        }
    }

    @FXML
    protected void registrarSentimento(){
        Aluno alunoLogado = (Aluno) SessaoUsuario.getUsuarioLogado();
        if(alunoLogado == null){
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Sessão Inválida", "Nenhum aluno logado. Por favor, faça login novamente.");
            return;
        }

        int valorSlider = (int) sliderSentimento.getValue();
        String nomeHumor = getDescricaoSentimento(valorSlider);
        String descricaoRegistro = textAreaDescricao.getText();

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Deseja registrar essa emoção como '" + nomeHumor + "'?");
        ButtonType sim = new ButtonType("Sim");
        ButtonType nao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmacao.getButtonTypes().setAll(sim, nao);

        Optional<ButtonType> opcao = confirmacao.showAndWait();
        if(opcao.isPresent() && opcao.get() == sim) {
            HumorDAO humorDAO = new HumorDAO();
            Humor emocao = new Humor(nomeHumor, alunoLogado.getId(), LocalDate.now(), descricaoRegistro);
            boolean sucesso = humorDAO.inserirEmocao(emocao);

            if (sucesso) {
                exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", null, "Emoção registrada com sucesso!");
                textAreaDescricao.clear();
                sliderSentimento.setValue(2);
            } else {
                exibirAlerta(Alert.AlertType.ERROR, "Erro", null, "Erro ao registrar sua emoção. Tente novamente.");
            }
        }
    }

    private String getDescricaoSentimento(int valor) {
        switch (valor) {
            case 0: return "Péssimo";
            case 1: return "Triste";
            case 2: return "Neutro";
            case 3: return "Bem";
            case 4: return "Feliz";
            case 5: return "Radiante";
            default: return "Desconhecido";
        }
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String cabecalho, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }
}