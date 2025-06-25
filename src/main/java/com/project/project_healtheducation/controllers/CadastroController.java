package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.dao.PsicologoDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CadastroController {

    @FXML private TextField textNome;
    @FXML private TextField textEmail;
    @FXML private PasswordField textSenha;
    @FXML private PasswordField textSenhaConfirm;
    @FXML private  TextField textMatricula;

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
    }

    private Aluno obterDadosFormularioAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(textNome.getText());
        aluno.setEmail(textEmail.getText());
        aluno.setSenha(textSenha.getText());
        aluno.setMatricula(textMatricula.getText());

        RadioButton selecionado = (RadioButton) groupRadios.getSelectedToggle();
        if (selecionado != null) {
            aluno.setTipo(selecionado.getText());
        }

        return aluno;
    }

    private Professor obterDadosFormularioProfessor() {
        Professor professor = new Professor();
        professor.setNome(textNome.getText());
        professor.setEmail(textEmail.getText());
        professor.setSenha(textSenha.getText());

        RadioButton selecionado = (RadioButton) groupRadios.getSelectedToggle();
        if (selecionado != null) {
            professor.setTipo(selecionado.getText());
        }

        return professor;
    }

    private Psicologo obterDadosFormularioPsicologo(){
        Psicologo psicologo = new Psicologo();
        psicologo.setNome(textNome.getText());
        psicologo.setEmail(textEmail.getText());
        psicologo.setSenha(textSenha.getText());

        RadioButton selecionado = (RadioButton) groupRadios.getSelectedToggle();
        if (selecionado != null) {
            psicologo.setTipo(selecionado.getText());
        }

        return psicologo;
    }

    private boolean validarCadastro() {
        String nome = textNome.getText();
        String email = textEmail.getText();
        String matricula = textMatricula.getText();
        String senha = textSenha.getText();
        String senhaConfirm = textSenhaConfirm.getText();

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaConfirm.isEmpty() || matricula.isEmpty()) {
            alerta.setContentText("Todos os campos devem ser preenchidos.");
            alerta.showAndWait();
            return false;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            alerta.setContentText("Email inválido.");
            alerta.showAndWait();
            return false;
        }
//        if (!matricula.matches("^\\d{9}$")) {
//            alerta.setContentText("Matrícula inválida.");
//            alerta.showAndWait();
//            return false;
//        }

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
        if (!validarCadastro()) return false;
        AlunoDAO alunoDAO = new AlunoDAO();
        String tipo = ((RadioButton) groupRadios.getSelectedToggle()).getText();

        if (tipo.equalsIgnoreCase("Aluno")) {
            Aluno aluno = obterDadosFormularioAluno();
            if (alunoDAO.buscarPorEmail(aluno.getEmail()) != null) {
                mostrarAlerta("Este e-mail já está cadastrado.");
                return false;
            }
            return alunoDAO.inserirAluno(aluno);

        } else if (tipo.equalsIgnoreCase("Professor")) {
            Professor professor = obterDadosFormularioProfessor();
            ProfessorDAO dao = new ProfessorDAO();

            if (dao.buscarProfessorPorEmail(professor.getEmail()) != null) {
                mostrarAlerta("Este e-mail já está cadastrado.");
                return false;
            }
            return dao.inserirProfessor(professor);

        } else {
            Psicologo psicologo = obterDadosFormularioPsicologo();
            PsicologoDAO daoPs = new PsicologoDAO();

            if(daoPs.buscarPorEmail(psicologo.getEmail()) != null){
                mostrarAlerta("Este e-mail já está cadastrado");
                return false;
            }
            return daoPs.inserirPsicologo(psicologo);
        }
    }

    private void mostrarAlerta(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    protected void voltarInicio(ActionEvent event) {
        try {
            ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void irParaHome(ActionEvent event) {
        if (inserirUsuario()) {
            try {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
