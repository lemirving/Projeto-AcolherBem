package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;

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

    @FXML protected void registrarSentimento(){
        int valor = (int) sliderSentimento.getValue();
        String descricao = getDescricaoSentimento(valor);

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText("Sua emoção");
        alerta.setContentText("Sentimento registrado: " + descricao);
        // SALVAR NO BANCO DE DADOS
    }

    private String getDescricaoSentimento(int valor) {
        if(valor <= 1) return "Depremido";
        if(valor <= 3) return "Muito triste";
        if(valor <= 4) return "Triste";
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
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/HomeAluno.fxml");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
