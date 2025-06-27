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
    @FXML private TextField textMatricula;
    @FXML private TextField textIdade;
    @FXML private TextField textTurma;

    @FXML private Label labelNome;
    @FXML private Label labelEmail;
    @FXML private Label labelIdade;
    @FXML private Label labelMatricula;
    @FXML private Label labelSenha;
    @FXML private Label labelConfirmSenha;
    @FXML private Label labelTurma;

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


        groupRadios.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String tipoSelecionado = ((RadioButton) newValue).getText();
                atualizarVisibilidadeCampos(tipoSelecionado);
            } else {
                atualizarVisibilidadeCampos(null);
            }
        });

        radioAluno.setSelected(true);
    }


    private void atualizarVisibilidadeCampos(String tipo) {
        boolean isAluno = "Aluno".equalsIgnoreCase(tipo);
        boolean isProfessor = "Professor".equalsIgnoreCase(tipo);
        boolean isPsicologo = "Psicologo".equalsIgnoreCase(tipo);


        boolean isAnyTypeSelected = (tipo != null);
        labelNome.setVisible(isAnyTypeSelected); textNome.setVisible(isAnyTypeSelected);
        labelEmail.setVisible(isAnyTypeSelected); textEmail.setVisible(isAnyTypeSelected);
        labelSenha.setVisible(isAnyTypeSelected); textSenha.setVisible(isAnyTypeSelected);
        labelConfirmSenha.setVisible(isAnyTypeSelected); textSenhaConfirm.setVisible(isAnyTypeSelected);

        labelMatricula.setVisible(isAluno); textMatricula.setVisible(isAluno);
        labelTurma.setVisible(isAluno); textTurma.setVisible(isAluno);
        labelIdade.setVisible(isAluno); textIdade.setVisible(isAluno);


        if (!isAluno) {
            textMatricula.clear();
            textTurma.clear();
            textIdade.clear();
        }

    }


    private Aluno obterDadosFormularioAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(textNome.getText());
        aluno.setEmail(textEmail.getText());
        aluno.setSenha(textSenha.getText());
        aluno.setIdade(textIdade.getText());
        aluno.setNomeTurma(textTurma.getText());
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
        String senha = textSenha.getText();
        String senhaConfirm = textSenhaConfirm.getText();

        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senhaConfirm.isEmpty()) {
            alerta.setContentText("Nome, E-mail e Senhas devem ser preenchidos.");
            alerta.showAndWait();
            return false;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            alerta.setContentText("E-mail inválido.");
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

        RadioButton selecionado = (RadioButton) groupRadios.getSelectedToggle();
        if (selecionado == null) {
            alerta.setContentText("Selecione o tipo de usuário (Aluno, Professor ou Psicólogo).");
            alerta.showAndWait();
            return false;
        }

        String tipo = selecionado.getText();
        if (tipo.equalsIgnoreCase("Aluno")) {
            String matricula = textMatricula.getText();
            String turmaAluno = textTurma.getText();
            String idade = textIdade.getText();

            if (matricula.isEmpty() || turmaAluno.isEmpty() || idade.isEmpty()) {
                alerta.setContentText("Para Aluno, Matrícula, Turma e Idade devem ser preenchidos.");
                alerta.showAndWait();
                return false;
            }
            if (!matricula.matches("^\\d{9}$")) {
                alerta.setContentText("Matrícula inválida. Deve conter 9 dígitos numéricos.");
                alerta.showAndWait();
                return false;
            }
            if (!turmaAluno.matches("^[1-9][A-Z]$")) {
                alerta.setContentText("Turma inválida. Use o formato '2B', '3A', etc.");
                alerta.showAndWait();
                return false;
            }
            try {
                int idadeNum = Integer.parseInt(idade);
                if (idadeNum <= 0 || idadeNum > 120) { // Exemplo de faixa de idade
                    alerta.setContentText("Idade inválida.");
                    alerta.showAndWait();
                    return false;
                }
            } catch (NumberFormatException e) {
                alerta.setContentText("Idade deve ser um número válido.");
                alerta.showAndWait();
                return false;
            }
        }

        return true;
    }
    // CadastroController.java
    private boolean inserirUsuario() {
        if (!validarCadastro()) {
            // validarCadastro() already shows an alert, so just return false.
            return false;
        }

        AlunoDAO alunoDAO = new AlunoDAO();
        String tipo = ((RadioButton) groupRadios.getSelectedToggle()).getText();

        boolean success = false;

        if (tipo.equalsIgnoreCase("Aluno")) {
            Aluno aluno = obterDadosFormularioAluno();
            if (alunoDAO.buscarPorEmail(aluno.getEmail()) != null) {
                mostrarAlerta("Erro de Cadastro", "E-mail já Cadastrado", "Este e-mail já está cadastrado como Aluno.");
            } else {
                success = alunoDAO.inserirAluno(aluno);
                if (!success) {
                    mostrarAlerta("Erro de Cadastro", "Falha na Inserção", "Não foi possível cadastrar o Aluno. Verifique os dados ou tente novamente.");
                }
            }
        } else if (tipo.equalsIgnoreCase("Professor")) {
            Professor professor = obterDadosFormularioProfessor();
            ProfessorDAO dao = new ProfessorDAO();
            if (dao.buscarProfessorPorEmail(professor.getEmail()) != null) {
                mostrarAlerta("Erro de Cadastro", "E-mail já Cadastrado", "Este e-mail já está cadastrado como Professor.");
            } else {
                success = dao.inserirProfessor(professor);
                if (!success) {
                    mostrarAlerta("Erro de Cadastro", "Falha na Inserção", "Não foi possível cadastrar o Professor. Verifique os dados ou tente novamente.");
                }
            }
        } else {
            Psicologo psicologo = obterDadosFormularioPsicologo();
            PsicologoDAO daoPs = new PsicologoDAO();
            if(daoPs.buscarPorEmail(psicologo.getEmail()) != null){
                mostrarAlerta("Erro de Cadastro", "E-mail já Cadastrado", "Este e-mail já está cadastrado como Psicólogo.");
            } else {
                success = daoPs.inserirPsicologo(psicologo);
                if (!success) {
                    mostrarAlerta("Erro de Cadastro", "Falha na Inserção", "Não foi possível cadastrar o Psicólogo. Verifique os dados ou tente novamente.");
                }
            }
        }
        return success;
    }

    private void mostrarAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert;
        if (titulo.contains("Erro") || titulo.contains("Falha")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
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
            mostrarAlerta("Sucesso", "Sucesso na inserção", "Vá para o Login.");
            try {
                ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                exibirAlerta("Erro ao trocar janela", "Erro de Navegação", "Erro ao carregar tela de login.");
            }
        }
    }
    private void exibirAlerta(String titulo, String cabecalho, String conteudo) {
        Alert alert;
        if (titulo.contains("Erro") || titulo.contains("Falha")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

}