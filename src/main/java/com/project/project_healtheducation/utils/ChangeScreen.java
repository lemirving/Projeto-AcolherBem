package com.project.project_healtheducation.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ChangeScreen {

        public static void setScreen(ActionEvent event, String caminho) throws IOException {
        // CARREGA O NOVO FXML
        FXMLLoader fxmlLoader = new FXMLLoader(ChangeScreen.class.getResource(caminho));
        Parent root = fxmlLoader.load();

        // PEGA A JANELA ATUAL (STATE)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // MANTÉM O TAMANHO DA JANELA
        double width = stage.getWidth();
        double height = stage.getHeight();

        // CRIA CENA
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);

        stage.show();
    }
        public static void showError(String titulo, String mensagem){
                javafx.scene.control.Alert alert = new  javafx.scene.control.Alert(Alert.AlertType.ERROR);
                alert.setTitle(titulo);
                alert.setHeaderText(null);
                alert.setContentText(mensagem);
                alert.showAndWait();
        }
    public static void setHalfScreen(AnchorPane pane, String caminho){
            try {
                    URL resource = ChangeScreen.class.getResource(caminho);
                    if (resource == null) {
                            throw new IOException(caminho);
                    }
                    FXMLLoader loader = new FXMLLoader(resource);
                    AnchorPane tela = loader.load();

                    pane.getChildren().clear();
                    pane.getChildren().add(tela);

                    AnchorPane.setTopAnchor(tela, 0.0);
                    AnchorPane.setBottomAnchor(tela, 0.0);
                    AnchorPane.setLeftAnchor(tela, 0.0);
                    AnchorPane.setRightAnchor(tela, 0.0);

            } catch (IOException e) {
                    showError("Erro ao carregar a tela", "Não foi possível abrir a tela selecionada. " + e.getMessage());
            }
    }
}
