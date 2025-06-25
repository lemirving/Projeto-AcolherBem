package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.HumorDAO;
import com.project.project_healtheducation.dao.HumorDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Humor;
import com.project.project_healtheducation.model.Humor;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class RegistroEmocoesController {

    @FXML
    private Slider sliderSentimento = new Slider();
    @FXML private Label labelSentimentoEscolhido = new Label();




    @FXML
    protected void initialize() {
        if (sliderSentimento == null) {
            System.out.println("ERRO: sliderSentimento está null");
        }
        if (labelSentimentoEscolhido == null) {
            System.out.println("ERRO: labelSentimentoEscolhido está null");
        }
        sliderSentimento.valueProperty().addListener((obs, oldVal, newVal) -> labelSentimentoEscolhido.setText("Estado atual: " + getDescricaoSentimento(newVal.intValue())));
    }

    @FXML
    protected void registrarSentimento(){
        Aluno alunoLogado = (Aluno) SessaoUsuario.getUsuarioLogado();
        if(alunoLogado == null){
            System.out.println("Nenhum usuário logado!");
            return;
        }

        int valor = (int) sliderSentimento.getValue();
        String nomeHumor = getDescricaoSentimento(valor);
        String descricao = "Registro feito automaticamente no app";

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("Deseja registrar essa emoção?");
        ButtonType sim = new ButtonType("Sim");
        ButtonType nao = new ButtonType("Não");
        alerta.getButtonTypes().setAll(sim, nao);

        Optional<ButtonType> opcao = alerta.showAndWait();
        if(opcao.isPresent() && opcao.get() == sim) {
            HumorDAO emocional = new HumorDAO();
            Humor emocao = new Humor(nomeHumor, alunoLogado.getId(), LocalDate.now(), descricao);
            boolean sucesso = emocional.inserirEmocao(emocao);

            if (sucesso) {
                Alert alerta2 = new Alert(Alert.AlertType.INFORMATION);
                alerta2.setHeaderText(null);
                alerta2.setContentText("Emoção registrada com sucesso!");
                alerta2.showAndWait();
            } else {
                Alert alerta3 = new Alert(Alert.AlertType.ERROR);
                alerta3.setHeaderText(null);
                alerta3.setContentText("Erro ao registrar sua emoção.");
                alerta3.showAndWait();
            }
        }
    }

    private String getDescricaoSentimento(int valor) {
        if(valor <= 1) return "Depremido";
        if(valor <= 2) return "Muito triste";
        if(valor <= 3) return "Triste";
        if(valor <= 4) return "desanimado";
        if(valor <= 5) return "Neutro";
        if(valor <= 6) return "OK";
        if(valor <= 7) return "Bem";
        if(valor <= 8) return "Feliz";
        if(valor <= 9) return "Muito feliz";
        return "Radiante";
    }

    @FXML
    protected void voltar(ActionEvent event){
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void btnTelaInicial(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
    }
}
