package com.wiizz.jess.client.jessclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Jess Client");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(Client.class.getResourceAsStream("icon.png"))));

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/wiizz/jess/client/jessclient/fxml/game.fxml")));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        String css = Objects.requireNonNull(getClass().getResource("/com/wiizz/jess/client/jessclient/fxml/style.css")).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}