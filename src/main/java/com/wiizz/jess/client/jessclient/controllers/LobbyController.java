package com.wiizz.jess.client.jessclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class LobbyController {
    public TableView gamesTable;
    public Button createButton;
    public Label serverName;
    public PasswordField passwordInput;
    public Button joinButton;

    private Socket clientSocket;

    public void initialize(){

    }

    public void setClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    public void createTable(ActionEvent actionEvent) throws IOException {
        joinTable(actionEvent);
    }

    public void joinTable(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Stage newStage = new Stage();
        newStage.setTitle(stage.getTitle());
        newStage.getIcons().setAll(stage.getIcons());

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/wiizz/jess/client/jessclient/fxml/game.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.show();
        stage.close();
    }
}
