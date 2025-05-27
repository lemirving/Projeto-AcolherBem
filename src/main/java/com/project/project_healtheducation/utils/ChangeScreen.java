package com.project.project_healtheducation.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
}
