package com.project.project_healtheducation.controllers.home;

import com.jfoenix.controls.JFXButton;
import com.project.project_healtheducation.controllers.TabelaAlunosController;
import com.project.project_healtheducation.utils.ChangeScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeProfessorController {

    @FXML
    private JFXButton btnPerfil;
    @FXML
    private JFXButton btnAlunos;
    @FXML
    private JFXButton btnTurmas;
    @FXML
    private JFXButton btnConfig;
    @FXML
    private JFXButton btnSair;

    @FXML
    private AnchorPane main_anchorPane;
//    private void loadPage(String fxml){
//        try {
//            URL resource = getClass().getResource("/com/project/project_healtheducation/view/professor/telaPerfil.fxml");
//            if( resource == null){
//                throw new IOException("FXML não encontrado:"+ fxml);
//            }
//            AnchorPane pane = FXMLLoader.load(resource);
//            main_anchorPane.getChildren().setAll(pane);
//        } catch (IOException e) {
//            showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada." + e.getMessage());
//        }
//    }


    @FXML
    private void handleTelaListaAlunos() {
        try {

            String fxmlPath = "/com/project/project_healtheducation/view/professor/telaListaAlunos.fxml";


            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent loadedPane = loader.load(); // Carrega o FXML


            TabelaAlunosController tabelaAlunosController = loader.getController();

            // 4. Chama o método refreshData() no controller da TabelaAlunos
            if (tabelaAlunosController != null) {
                tabelaAlunosController.refreshData();
            } else {
                System.err.println("Erro: TabelaAlunosController não encontrado após carregar FXML.");
            }

            // 5. Adiciona o painel carregado ao main_anchorPane (simulando setHalfScreen)
            // Limpa o conteúdo anterior e define o novo
            main_anchorPane.getChildren().setAll(loadedPane);
            // Opcional: ajustar os anchors para preencher o AnchorPane pai
            AnchorPane.setTopAnchor(loadedPane, 0.0);
            AnchorPane.setBottomAnchor(loadedPane, 0.0);
            AnchorPane.setLeftAnchor(loadedPane, 0.0);
            AnchorPane.setRightAnchor(loadedPane, 0.0);

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela da lista de alunos: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Carregamento");

            alert.setHeaderText("Não foi possível carregar a lista de alunos.");
            alert.setContentText("Detalhes: " + e.getMessage());

            alert.showAndWait();
        }
    }

    // /com/project/project_healtheducation/view/professor/telaPerfil.fxml
    @FXML
    private void handleTelaPerfil(ActionEvent event) {
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/telaPerfil.fxml");
    }

    @FXML
    private void handleTelaConfigs(ActionEvent event){
        ChangeScreen.setHalfScreen(main_anchorPane, "/com/project/project_healtheducation/view/telaConfigs.fxml");

    }


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
//        btnAlunos.setOnAction(e -> System.out.println("Abrir Alunos"));
//        btnPerfil.setOnAction(e -> System.out.println("Abrir Perfil"));
//        btnTurmas.setOnAction(e -> System.out.println("Abrir Turmas"));
//        btnConfig.setOnAction(e -> System.out.println("Abrir Configurações"));
//        btnSair.setOnAction(e -> System.out.println("Sair da tela professor"));

    }

    @FXML
    protected void handleVoltar(ActionEvent event) throws IOException {
        ChangeScreen.setScreen(event, "/com/project/project_healtheducation/view/paginaInicial.fxml");
    }


}
