package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.utils.ChangeScreen;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField textEmail;
    @FXML
    PasswordField textSenha;


    private boolean validarLogin() {
        String email = textEmail.getText();
        String senha = textSenha.getText();

        // Verifica se algum campo está vazio
        if (email.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Todos os campos devem ser preenchidos.");
            return false;
        }

        // Verifica formato básico do e-mail
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Digite um e-mail válido.");
            return false;
        }

        // Verifica se a senha tem pelo menos 4 caracteres
        if (senha.length() < 4) {
            mostrarAlerta("A senha deve ter pelo menos 4 caracteres.");
            return false;
        }

        // Autenticação

        AlunoDAO alunoDAO = new AlunoDAO();
        boolean alunoAutenticado = alunoDAO.autenticar(email, senha);
        if (alunoAutenticado) {
            Aluno alunoLogado = alunoDAO.buscarPorEmail(email);
            SessaoUsuario.setUsuarioLogado(alunoLogado);
            return true;
        }

        // Tenta autenticar como professor (se quiser)
        ProfessorDAO professorDAO = new ProfessorDAO();
        boolean professorAutenticado = professorDAO.autenticar(email, senha);
        if (professorAutenticado) {
            Professor professorLogado = professorDAO.buscarProfessorPorEmail(email);
            SessaoUsuario.setUsuarioLogado(professorLogado); // Só se SessaoUsuario suportar professor também
            return true;
        }

        // Nenhuma autenticação foi bem-sucedida
        mostrarAlerta("E-mail ou senha incorretos.");
        return false;
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }


    @FXML
    protected void voltarInicio(ActionEvent event) throws IOException{
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");

    }

    @FXML
    protected void irParaHome(ActionEvent event) throws IOException{
        if(validarLogin()){
            Usuario usuario = SessaoUsuario.getUsuarioLogado();
            if(usuario instanceof Aluno){
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
            }else if(usuario instanceof Professor){
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/professor/telaProfessores.fxml");
            }
        }
    }
}