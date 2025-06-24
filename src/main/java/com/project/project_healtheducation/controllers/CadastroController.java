package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CadastroController extends AlunoDAO {

    @FXML private TextField textNome;
    @FXML private TextField textEmail;
    @FXML private PasswordField textSenha;
    @FXML private PasswordField textSenhaConfirm;
    @FXML private Label labelAnoTurma;
    @FXML private TextField textAnoTurma;

    @FXML private RadioButton radioAluno;
    @FXML private RadioButton radioProfessor;
    @FXML private RadioButton radioPsicologo;

    private ToggleGroup groupRadios;

    @FXML
    private void initialize() {
        groupRadios = new ToggleGroup();
        radioAluno.setToggleGroup(groupRadios);
        radioProfessor.setToggleGroup(groupRadios);
        radioPsicologo.setToggleGroup(groupRadios);

        labelAnoTurma.setVisible(false);
        textAnoTurma.setVisible(false);

        groupRadios.selectedToggleProperty().addListener((obs, antigo, novo) -> {
            if (novo != null) {
                RadioButton selecionado = (RadioButton) novo;
                boolean isAluno = selecionado.getText().equalsIgnoreCase("Aluno");
                labelAnoTurma.setVisible(isAluno);
                textAnoTurma.setVisible(isAluno);
            }
        });
    }

    private Aluno obterDadosFormulario() {
        Aluno aluno = new Aluno();
        aluno.setNome(textNome.getText());
        aluno.setEmail(textEmail.getText());
        aluno.setSenha(textSenha.getText());
        aluno.setNomeTurma(textAnoTurma.getText());

        RadioButton selecionado = (RadioButton) groupRadios.getSelectedToggle();
        if (selecionado != null) {
            aluno.setTipo(selecionado.getText());
        }

        return aluno;
    }

    private boolean validarCadastro() {
        String nome = textNome.getText();
        String email = textEmail.getText();
        String senha = textSenha.getText();
        String senhaConfirm = textSenhaConfirm.getText();
        String nomeTurma = textAnoTurma.getText();

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaConfirm.isEmpty() || nomeTurma.isEmpty()) {
            alerta.setContentText("Todos os campos devem ser preenchidos.");
            alerta.showAndWait();
            return false;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            alerta.setContentText("Email inválido.");
            alerta.showAndWait();
            return false;
        }

        if (senha.length() < 6) {
            alerta.setContentText("A senha deve conter no mínimo 6 caracteres.");
            alerta.showAndWait();
            return false;
        }

        if (!senha.equals(senhaConfirm)) {
            alerta.setContentText("As senhas não coincidem.");
            alerta.showAndWait();
            return false;
        }

        if (groupRadios.getSelectedToggle() == null) {
            alerta.setContentText("Selecione o tipo de usuário.");
            alerta.showAndWait();
            return false;
        }

        return true;
    }

    private boolean inserirUsuario() {
        if (validarCadastro()) {
            Aluno aluno = obterDadosFormulario();

//            if (!"Aluno".equalsIgnoreCase(aluno.getTipo())) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setHeaderText(null);
//                alert.setContentText("Este formulário permite apenas o cadastro de Alunos.");
//                alert.showAndWait();
//                return false;
//            }

            boolean sucesso = inserirAluno(aluno);

            if (sucesso) return true;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Erro ao cadastrar usuário.");
        alert.showAndWait();
        return false;
    }

    @FXML
    protected void voltarInicio(ActionEvent event) {
        try {
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/paginaInicial.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irParaHome(ActionEvent event) {
        if (inserirUsuario()) {
            try {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/HomeAluno.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
