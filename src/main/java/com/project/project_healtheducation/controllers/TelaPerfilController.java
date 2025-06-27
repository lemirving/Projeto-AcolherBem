package com.project.project_healtheducation.controllers;

import com.jfoenix.controls.JFXButton;
import com.project.project_healtheducation.dao.AlunoDAO;
import com.project.project_healtheducation.dao.ProfessorDAO;
import com.project.project_healtheducation.dao.PsicologoDAO;
import com.project.project_healtheducation.model.Usuario;
import com.project.project_healtheducation.model.Aluno;
import com.project.project_healtheducation.model.Professor;
import com.project.project_healtheducation.model.Psicologo;
import com.project.project_healtheducation.utils.SessaoUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane; // Importar AnchorPane

import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class TelaPerfilController {

    // --- FXML Elements ---
    @FXML private Label nomeValorLabel;
    @FXML private Label idadeValorLabel;
    @FXML private Label emailLabel;
    @FXML private Label tipoLabel;

    @FXML private ImageView fotoPerfil;

    @FXML private TextField nomeInput;
    @FXML private TextField idadeInput;

    @FXML private JFXButton editarPerfilBtn;
    @FXML private JFXButton trocarFotoBtn;

    @FXML private AnchorPane idadeContainer;

    private boolean editando = false;
    private Usuario usuarioAtualExibido;


    @FXML
    public void initialize() {

        nomeInput.setVisible(false);
        nomeInput.setManaged(false);

        idadeInput.setVisible(false);
        idadeInput.setManaged(false);


        nomeValorLabel.setVisible(true);
        nomeValorLabel.setManaged(true);

        editarPerfilBtn.setVisible(true);
        editarPerfilBtn.setManaged(true);
        trocarFotoBtn.setVisible(true);
        trocarFotoBtn.setManaged(true);
        editarPerfilBtn.setDisable(false);
        trocarFotoBtn.setDisable(false);

        Usuario loggedInUserFromSession = SessaoUsuario.getUsuarioLogado();
        System.out.println("DEBUG: Usuário da sessão: " + (loggedInUserFromSession != null ? loggedInUserFromSession.getEmail() + " (ID: " + loggedInUserFromSession.getId() + ", Tipo: " + loggedInUserFromSession.getTipo() + ")" : "NULO"));

        if (loggedInUserFromSession != null) {
            Usuario userToDisplay = null;

            if (loggedInUserFromSession instanceof Aluno) {
                userToDisplay = new AlunoDAO().buscarAlunoPorId(loggedInUserFromSession.getId());
                System.out.println("DEBUG: Buscando Aluno por ID: " + loggedInUserFromSession.getId());
            } else if (loggedInUserFromSession instanceof Professor) {
                userToDisplay = new ProfessorDAO().buscarProfessorPorId(loggedInUserFromSession.getId());
                System.out.println("DEBUG: Buscando Professor por ID: " + loggedInUserFromSession.getId());
            } else if (loggedInUserFromSession instanceof Psicologo) {
                userToDisplay = new PsicologoDAO().buscarPsicologoPorId(loggedInUserFromSession.getId());
                System.out.println("DEBUG: Buscando Psicologo por ID: " + loggedInUserFromSession.getId());
            }

            System.out.println("DEBUG: Usuário completo do banco (após busca): " + (userToDisplay != null ? userToDisplay.getEmail() + " (Nome: " + userToDisplay.getNome() + ", ID: " + userToDisplay.getId() + ")" : "NULO - Erro na busca ou ID não encontrado!"));

            if (userToDisplay != null) {
                setUsuario(userToDisplay);

                nomeInput.setVisible(false);
                nomeInput.setManaged(false);
                idadeInput.setVisible(false);
                idadeInput.setManaged(false);

            } else {
                System.err.println("Erro: Não foi possível carregar os dados completos do usuário do banco de dados.");
                exibirAlerta("Erro de Carregamento", "Perfil Não Encontrado", "Não foi possível carregar os dados do seu perfil.");

                nomeValorLabel.setVisible(false);
                nomeValorLabel.setManaged(false);
                idadeValorLabel.setVisible(false);
                idadeValorLabel.setManaged(false);
                if (idadeContainer != null) {
                    idadeContainer.setVisible(false);
                    idadeContainer.setManaged(false);
                }
                emailLabel.setVisible(false);
                emailLabel.setManaged(false);
                tipoLabel.setVisible(false);
                tipoLabel.setManaged(false);
                fotoPerfil.setImage(null);

                editarPerfilBtn.setVisible(false);
                editarPerfilBtn.setManaged(false);
                trocarFotoBtn.setVisible(false);
                trocarFotoBtn.setManaged(false);
            }
        } else {
            System.out.println("DEBUG: Nenhum usuário logado na sessão.");
            exibirAlerta("Erro", "Nenhum Usuário Logado", "Por favor, faça login para ver seu perfil.");

            nomeInput.setVisible(false);
            nomeInput.setManaged(false);
            idadeInput.setVisible(false);
            idadeInput.setManaged(false);
            nomeValorLabel.setVisible(false);
            nomeValorLabel.setManaged(false);
            idadeValorLabel.setVisible(false);
            idadeValorLabel.setManaged(false);
            if (idadeContainer != null) {
                idadeContainer.setVisible(false);
                idadeContainer.setManaged(false);
            }
            emailLabel.setVisible(false);
            emailLabel.setManaged(false);
            tipoLabel.setVisible(false);
            tipoLabel.setManaged(false);
            fotoPerfil.setImage(null);

            editarPerfilBtn.setVisible(false);
            editarPerfilBtn.setManaged(false);
            trocarFotoBtn.setVisible(false);
            trocarFotoBtn.setManaged(false);
        }
    }

    public void setUsuario(Usuario usuarioParaExibir) {
        this.usuarioAtualExibido = usuarioParaExibir;

        if (usuarioParaExibir != null) {
            nomeValorLabel.setText(usuarioParaExibir.getNome());
            emailLabel.setText("Email: " + usuarioParaExibir.getEmail());
            tipoLabel.setText("Tipo: " + usuarioParaExibir.getTipo());

            if (usuarioParaExibir instanceof Aluno aluno) {
                if (idadeContainer != null) {
                    idadeContainer.setVisible(true);
                    idadeContainer.setManaged(true);
                }
                idadeValorLabel.setText(aluno.getIdade());
                idadeValorLabel.setVisible(true);
                idadeValorLabel.setManaged(true);
                idadeInput.setVisible(false);
                idadeInput.setManaged(false);
            } else {
                if (idadeContainer != null) {
                    idadeContainer.setVisible(false);
                    idadeContainer.setManaged(false);
                }
                idadeValorLabel.setText("");
                idadeValorLabel.setVisible(false);
                idadeValorLabel.setManaged(false);
                idadeInput.setVisible(false);
                idadeInput.setManaged(false);
            }

            String caminhoImagem = null;
            if (usuarioParaExibir instanceof Aluno aluno) {
                caminhoImagem = aluno.getCaminhoImagem();
            } else if (usuarioParaExibir instanceof Professor professor) {
                caminhoImagem = professor.getCaminhoImagem();
            } else if (usuarioParaExibir instanceof Psicologo psicologo) {
                caminhoImagem = psicologo.getCaminhoImagem();
            }
            carregarProfileImage(caminhoImagem);

        } else {
            nomeValorLabel.setText("N/A");
            nomeValorLabel.setVisible(false); nomeValorLabel.setManaged(false);

            emailLabel.setText("Email: N/A");
            emailLabel.setVisible(false); emailLabel.setManaged(false);

            idadeValorLabel.setText("N/A");
            idadeValorLabel.setVisible(false); idadeValorLabel.setManaged(false);

            if (idadeContainer != null) {
                idadeContainer.setVisible(false);
                idadeContainer.setManaged(false);
            }

            tipoLabel.setText("N/A");
            tipoLabel.setVisible(false); tipoLabel.setManaged(false);

            fotoPerfil.setImage(null);
            editarPerfilBtn.setVisible(false);
            editarPerfilBtn.setManaged(false);
            trocarFotoBtn.setVisible(false);
            trocarFotoBtn.setManaged(false);
        }
    }

    private void carregarProfileImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                try {
                    Image newImage = new Image(file.toURI().toString());
                    if (newImage.isError()) {
                        System.err.println("Erro ao carregar imagem: " + newImage.exceptionProperty().get().getMessage());
                        fotoPerfil.setImage(null);
                    } else {
                        fotoPerfil.setImage(newImage);
                    }
                } catch (Exception e) {
                    System.err.println("Exceção ao carregar imagem de perfil: " + e.getMessage());
                    e.printStackTrace();
                    fotoPerfil.setImage(null);
                }
            } else {
                System.err.println("Caminho da imagem especificado não existe: " + imagePath);
                fotoPerfil.setImage(null);
            }
        } else {
            fotoPerfil.setImage(null);
        }
    }

    @FXML
    private void handleEditarPerfil() {
        if (!editando) {
            editando = true;

            nomeValorLabel.setVisible(false);
            nomeValorLabel.setManaged(false);
            nomeInput.setVisible(true);
            nomeInput.setManaged(true);
            nomeInput.setText(usuarioAtualExibido.getNome());

            if (usuarioAtualExibido instanceof Aluno a) {
                if (idadeContainer != null) {
                    idadeContainer.setVisible(true);
                    idadeContainer.setManaged(true);
                }
                idadeValorLabel.setVisible(false);
                idadeValorLabel.setManaged(false);
                idadeInput.setVisible(true);
                idadeInput.setManaged(true);
                idadeInput.setText(a.getIdade());
            } else {
                if (idadeContainer != null) {
                    idadeContainer.setVisible(false);
                    idadeContainer.setManaged(false);
                }
                idadeValorLabel.setVisible(false);
                idadeValorLabel.setManaged(false);
                idadeInput.setVisible(false);
                idadeInput.setManaged(false);
                idadeInput.setText("");
            }

            editarPerfilBtn.setText("Salvar");
        } else {
            String novoNome = nomeInput.getText().trim();
            String novaIdade = idadeInput.getText().trim();

            if (novoNome.isEmpty()) {
                exibirAlerta("Erro de Validação", "Nome Inválido", "O nome não pode estar vazio.");
                return;
            }
            if (!novoNome.matches("^[a-zA-Z\\s]{1,40}$")) {
                exibirAlerta("Erro de Validação", "Nome Inválido", "O nome deve conter apenas letras e espaços, e ter no máximo 40 caracteres.");
                return;
            }

            if (usuarioAtualExibido instanceof Aluno) {
                if (novaIdade.isEmpty()) {
                    exibirAlerta("Erro de Validação", "Idade Inválida", "A idade não pode estar vazia.");
                    return;
                }
                if (!novaIdade.matches("\\d{1,2}")) {
                    exibirAlerta("Erro de Validação", "Idade Inválida", "A idade deve conter apenas números (até 2 dígitos).");
                    return;
                }
            }
            boolean sucesso = false;

            if (usuarioAtualExibido != null) {
                if (usuarioAtualExibido instanceof Aluno a) {
                    a.setNome(novoNome);
                    a.setIdade(novaIdade);
                    sucesso = new AlunoDAO().atualizarPerfil(a);
                } else if (usuarioAtualExibido instanceof Professor p) {
                    p.setNome(novoNome);
                    sucesso = new ProfessorDAO().atualizarPerfil(p);
                } else if (usuarioAtualExibido instanceof Psicologo ps) {
                    ps.setNome(novoNome);
                    sucesso = new PsicologoDAO().atualizarPerfil(ps);
                }
            }

            if (sucesso) {
                editando = false;

                nomeValorLabel.setText(novoNome);
                nomeValorLabel.setVisible(true);
                nomeValorLabel.setManaged(true);
                nomeInput.setVisible(false);
                nomeInput.setManaged(false);

                if (usuarioAtualExibido instanceof Aluno) {
                    idadeValorLabel.setText(novaIdade);
                    idadeValorLabel.setVisible(true);
                    idadeValorLabel.setManaged(true);
                    idadeInput.setVisible(false);
                    idadeInput.setManaged(false);
                    if (idadeContainer != null) {
                        idadeContainer.setVisible(true);
                        idadeContainer.setManaged(true);
                    }
                } else {
                    idadeValorLabel.setText("");
                    idadeValorLabel.setVisible(false);
                    idadeValorLabel.setManaged(false);
                    idadeInput.setVisible(false);
                    idadeInput.setManaged(false);
                    if (idadeContainer != null) {
                        idadeContainer.setVisible(false);
                        idadeContainer.setManaged(false);
                    }
                }

                editarPerfilBtn.setText("Editar Perfil");
                exibirAlerta("Sucesso", null, "Perfil atualizado com sucesso!");

                SessaoUsuario.setUsuarioLogado(usuarioAtualExibido);
            } else {
                exibirAlerta("Erro ao Salvar", "Falha na Atualização", "Não foi possível salvar as alterações. Tente novamente.");
            }
        }
    }

    @FXML
    private void handleAlterarFotoPerfil() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolher nova foto de perfil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String caminho = file.getAbsolutePath();
            try {
                Image newImage = new Image(file.toURI().toString());
                if (newImage.isError()) {
                    throw new IOException("Failed to load image: " + newImage.exceptionProperty().get().getMessage());
                }
                fotoPerfil.setImage(newImage);

                boolean sucessoAtualizacaoFoto = false;
                if (usuarioAtualExibido instanceof Aluno aluno) {
                    sucessoAtualizacaoFoto = new AlunoDAO().atualizarCaminhoImagem(aluno.getId(), caminho);
                    if (sucessoAtualizacaoFoto) aluno.setCaminhoImagem(caminho);
                } else if (usuarioAtualExibido instanceof Professor professor) {
                    sucessoAtualizacaoFoto = new ProfessorDAO().atualizarCaminhoImagem(professor.getId(), caminho);
                    if (sucessoAtualizacaoFoto) professor.setCaminhoImagem(caminho);
                } else if (usuarioAtualExibido instanceof Psicologo psicologo) {
                    sucessoAtualizacaoFoto = new PsicologoDAO().atualizarCaminhoImagem(psicologo.getId(), caminho);
                    if (sucessoAtualizacaoFoto) psicologo.setCaminhoImagem(caminho);
                }

                if (sucessoAtualizacaoFoto) {
                    exibirAlerta("Sucesso", null, "Foto de perfil alterada com sucesso!");
                    SessaoUsuario.setUsuarioLogado(usuarioAtualExibido);
                } else {
                    exibirAlerta("Erro", "Falha ao Salvar Foto", "Ocorreu um erro ao tentar salvar a foto de perfil no banco de dados.");
                }

            } catch (IOException e) {
                System.err.println("Erro ao carregar imagem: " + e.getMessage());
                exibirAlerta("Erro de Imagem", "Não foi possível carregar a imagem.", "Verifique o arquivo selecionado. Erro: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Erro inesperado ao alterar foto de perfil: " + e.getMessage());
                exibirAlerta("Erro", "Falha Inesperada", "Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
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