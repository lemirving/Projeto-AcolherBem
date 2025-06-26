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

    private boolean editando = false;
    private Usuario usuarioAtualExibido;

    // --- Initialization Method ---
    @FXML
    public void initialize() {
        nomeInput.setVisible(false);
        idadeInput.setVisible(false);

        nomeValorLabel.setVisible(true);
        idadeValorLabel.setVisible(true);

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
                // NOVA LÓGICA: Sempre exibe os botões se o usuário foi carregado com sucesso
                editarPerfilBtn.setVisible(true);
                editarPerfilBtn.setManaged(true);
                trocarFotoBtn.setVisible(true);
                trocarFotoBtn.setManaged(true);
            } else {
                System.err.println("Erro: Não foi possível carregar os dados completos do usuário do banco de dados.");
                exibirAlerta("Erro de Carregamento", "Perfil Não Encontrado", "Não foi possível carregar os dados do seu perfil.");
                editarPerfilBtn.setDisable(true);
                trocarFotoBtn.setDisable(true);
            }
        } else {
            System.out.println("DEBUG: Nenhum usuário logado na sessão.");
            exibirAlerta("Erro", "Nenhum Usuário Logado", "Por favor, faça login para ver seu perfil.");
            editarPerfilBtn.setDisable(true);
            trocarFotoBtn.setDisable(true);
        }
    }

    // --- Method to set the user whose profile will be displayed ---
    public void setUsuario(Usuario usuarioParaExibir) {
        this.usuarioAtualExibido = usuarioParaExibir;

        if (usuarioParaExibir != null) {
            nomeValorLabel.setText(usuarioParaExibir.getNome());
            emailLabel.setText("Email: " + usuarioParaExibir.getEmail());

            if (usuarioParaExibir instanceof Aluno aluno) {
                idadeValorLabel.setText(aluno.getIdade());
                tipoLabel.setText("Tipo: Aluno");
                carregarProfileImage(aluno.getCaminhoImagem());
            } else if (usuarioParaExibir instanceof Professor professor) {
                idadeValorLabel.setText(professor.getIdade());
                tipoLabel.setText("Tipo: Professor");
                carregarProfileImage(professor.getCaminhoImagem());
            } else if (usuarioParaExibir instanceof Psicologo psicologo) {
                idadeValorLabel.setText(psicologo.getIdade());
                tipoLabel.setText("Tipo: Psicólogo");
                carregarProfileImage(psicologo.getCaminhoImagem());
            }
        } else {
            nomeValorLabel.setText("N/A");
            emailLabel.setText("Email: N/A");
            idadeValorLabel.setText("N/A");
            tipoLabel.setText("N/A");

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
    private void handleEditarPerfil() { // Removida a declaração 'throws IOException'
        if (!editando) { // Entering editing mode
            editando = true;

            nomeValorLabel.setVisible(false);
            idadeValorLabel.setVisible(false);

            nomeInput.setVisible(true);
            idadeInput.setVisible(true);

            if (usuarioAtualExibido != null) {
                nomeInput.setText(usuarioAtualExibido.getNome());
                if (usuarioAtualExibido instanceof Aluno a) {
                    idadeInput.setText(a.getIdade());
                } else if (usuarioAtualExibido instanceof Professor p) {
                    idadeInput.setText(p.getIdade());
                } else if (usuarioAtualExibido instanceof Psicologo ps) {
                    idadeInput.setText(ps.getIdade());
                }
            }

            editarPerfilBtn.setText("Salvar");
        } else { // Salvando alterações
            String novoNome = nomeInput.getText().trim(); // Boa prática: remover espaços extras
            String novaIdade = idadeInput.getText().trim(); // Boa prática: remover espaços extras

            // --- VALIDAÇÃO DE ENTRADA ---
            if (novoNome.isEmpty()) {
                exibirAlerta("Erro de Validação", "Nome Inválido", "O nome não pode estar vazio.");
                return; // Para a execução se houver erro
            }
            // Regex corrigida: ^[a-zA-Z\s]{1,40}$
            if (!novoNome.matches("^[a-zA-Z\\s]{1,40}$")) {
                exibirAlerta("Erro de Validação", "Nome Inválido", "O nome deve conter apenas letras e espaços, e ter no máximo 40 caracteres.");
                return; // Para a execução se houver erro
            }

            if (novaIdade.isEmpty()) {
                exibirAlerta("Erro de Validação", "Idade Inválida", "A idade não pode estar vazia.");
                return; // Para a execução se houver erro
            }
            // Regex para idade: apenas números, 1 a 3 dígitos
            if (!novaIdade.matches("\\d{1,2}")) {
                exibirAlerta("Erro de Validação", "Idade Inválida", "A idade deve conter apenas números (até 2 dígitos).");
                return; // Para a execução se houver erro
            }
            // --- FIM DA VALIDAÇÃO DE ENTRADA ---

            boolean sucesso = false;

            if (usuarioAtualExibido != null) {
                if (usuarioAtualExibido instanceof Aluno a) {
                    a.setNome(novoNome);
                    a.setIdade(novaIdade);
                    sucesso = new AlunoDAO().atualizarPerfil(a);
                } else if (usuarioAtualExibido instanceof Professor p) {
                    p.setNome(novoNome);
                    p.setIdade(novaIdade);
                    sucesso = new ProfessorDAO().atualizarPerfil(p);
                } else if (usuarioAtualExibido instanceof Psicologo ps) {
                    ps.setNome(novoNome);
                    ps.setIdade(novaIdade);
                    sucesso = new PsicologoDAO().atualizarPerfil(ps);
                }
            }

            if (sucesso) {
                editando = false;

                nomeValorLabel.setText(novoNome);
                idadeValorLabel.setText(novaIdade);

                nomeInput.setVisible(false);
                idadeInput.setVisible(false);
                nomeValorLabel.setVisible(true);
                idadeValorLabel.setVisible(true);

                editarPerfilBtn.setText("Editar Perfil");
                exibirAlerta("Sucesso", null, "Perfil atualizado com sucesso!");
            } else {
                exibirAlerta("Erro ao Salvar", "Falha na Atualização", "Não foi possível salvar as alterações. Tente novamente.");
                editando = true; // Permanece no modo de edição se o salvamento no banco falhar
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

                if (usuarioAtualExibido instanceof Aluno aluno) {
                    new AlunoDAO().atualizarCaminhoImagem(aluno.getId(), caminho);
                    aluno.setCaminhoImagem(caminho);
                } else if (usuarioAtualExibido instanceof Professor professor) {
                    new ProfessorDAO().atualizarCaminhoImagem(professor.getId(), caminho);
                    professor.setCaminhoImagem(caminho);
                } else if (usuarioAtualExibido instanceof Psicologo psicologo) {
                    new PsicologoDAO().atualizarCaminhoImagem(psicologo.getId(), caminho);
                    psicologo.setCaminhoImagem(caminho);
                }
                exibirAlerta("Sucesso", null, "Foto de perfil alterada com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao carregar imagem: " + e.getMessage());
                exibirAlerta("Erro de Imagem", "Não foi possível carregar a imagem.", "Verifique o arquivo selecionado. Erro: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Erro ao salvar foto de perfil: " + e.getMessage());
                exibirAlerta("Erro", "Falha ao Salvar Foto", "Ocorreu um erro ao tentar salvar a foto de perfil: " + e.getMessage());
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