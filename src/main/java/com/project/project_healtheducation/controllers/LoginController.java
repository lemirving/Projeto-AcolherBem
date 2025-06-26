package com.project.project_healtheducation.controllers;

import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.dao.PsicologoDAO;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textSenha;


    @FXML
    protected void tentarLogin(ActionEvent event) throws IOException {
        String email = textEmail.getText();
        String senhaDigitada = textSenha.getText();

        // Validações básicas
        if (email.isEmpty() || senhaDigitada.isEmpty()) {
            mostrarAlerta("Preencha todos os campos.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarAlerta("Digite um e-mail válido.");
            return;
        }

        if (senhaDigitada.length() < 6) {
            mostrarAlerta("A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        Usuario usuarioAutenticado = null;

        // Tenta autenticar como Aluno
        AlunoDAO alunoDAO = new AlunoDAO();
        if (alunoDAO.autenticar(email, senhaDigitada)) {
            usuarioAutenticado = alunoDAO.buscarPorEmail(email);
        }

        // Se não for aluno, tenta como professor
        if (usuarioAutenticado == null) {
            ProfessorDAO professorDAO = new ProfessorDAO();
            if (professorDAO.autenticar(email, senhaDigitada)) {
                usuarioAutenticado = professorDAO.buscarProfessorPorEmail(email);
            }
        }

        // Se não for professor, tenta como psicólogo
        if (usuarioAutenticado == null) {
            PsicologoDAO psicologoDAO = new PsicologoDAO();
            if (psicologoDAO.autenticar(email, senhaDigitada)) {
                usuarioAutenticado = psicologoDAO.buscarPorEmail(email);
            }
        }

        // Se autenticado, salva sessão e redireciona
        if (usuarioAutenticado != null) {
            SessaoUsuario.setUsuarioLogado(usuarioAutenticado);

            if (usuarioAutenticado instanceof Aluno) {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/HomeAluno.fxml");
            } else if (usuarioAutenticado instanceof Professor) {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/homeProfPsico.fxml");
            } else if (usuarioAutenticado instanceof Psicologo) {
                // Redirecionamento ainda não implementado
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/homeProfPsico.fxml");
            }

            textEmail.clear();
            textSenha.clear();
        } else {
            mostrarAlerta("E-mail ou senha incorretos. Tente novamente.");
            textSenha.clear();
        }
    }

    private void mostrarAlerta(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    protected void voltarInicio(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }
}
