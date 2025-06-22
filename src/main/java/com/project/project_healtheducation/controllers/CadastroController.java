package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CadastroController {

    @FXML
    private TextField textNome;
    @FXML
    private TextField textEmail;
    @FXML
    private PasswordField textSenha;
    @FXML
    private PasswordField textSenhaConfirm;

    @FXML private RadioButton radioAluno;
    @FXML private RadioButton radioProfessor;
    @FXML private RadioButton radioPsicologo;

    private ToggleGroup groupRadios;


    @FXML
    private void initialize(){
        groupRadios = new ToggleGroup();
        radioAluno.setToggleGroup(groupRadios);
        radioProfessor.setToggleGroup(groupRadios);
        radioPsicologo.setToggleGroup(groupRadios);
    }


    private boolean validarCadastro(){

        String nome = textNome.getText();
        String email = textEmail.getText();
        String senha = textSenha.getText();
        String senhaConfirm = textSenhaConfirm.getText();

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        if(nome.isEmpty() || senha.isEmpty() || senhaConfirm.isEmpty() || email.isEmpty() ){
            alerta.setContentText("Os campos devem ser preenchidos");
            alerta.showAndWait();

            return false;
        }
        if(!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")){
            alerta.setContentText("Email inválido");
            alerta.showAndWait();
            return false;
        }

        if(senha.length() < 6){
            alerta.setContentText("A senha deve conter no mínimo 6 caracteres");
            alerta.showAndWait();
            return false;
        }

        if(!senha.equals(senhaConfirm)){
            alerta.setContentText("Os campos senha e confirmar senha devem ser iguais");
            alerta.showAndWait();
            return false;
        }

        if(groupRadios.getSelectedToggle() == null){
            alerta.setContentText("Nenhuma opção selecionada! Por favor, selecione uma opção");
            alerta.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    protected void voltarInicio(ActionEvent event) {
        try{
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    protected void irParaHome(ActionEvent event) {
        try{
            if(validarCadastro())
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/HomeAluno.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
