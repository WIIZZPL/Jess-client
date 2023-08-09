package com.wiizz.jess.client.jessclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainController {
    public Button connectButton;
    public TableView serverTable;
    public TextField usernameInput;
    public TextField portInput;
    public TextField ipAddressInput;

    public void initialize(){

    }

    public void connectAction(ActionEvent actionEvent) throws IOException {
        //TODO

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Stage newStage = new Stage();
        newStage.setTitle(stage.getTitle());
        newStage.getIcons().setAll(stage.getIcons());

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/wiizz/jess/client/jessclient/fxml/lobby.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.show();
        stage.close();
    }

}
