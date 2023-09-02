package com.wiizz.jess.client.jessclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class MainController {
    public Button connectButton;
    public TableView<ServerModel> serverTable;
    public TableColumn<ServerModel, String> serverTableNameColumn;
    public TableColumn<ServerModel, String> serverTableIPColumn;
    public TableColumn<ServerModel, Integer> serverTablePortColumn;
    public TextField usernameInput;
    public TextField portInput;
    public TextField ipAddressInput;

    private BroadcastListener broadcastListener;

    public void initialize(){
        serverTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        serverTableIPColumn.setCellValueFactory(new PropertyValueFactory<>("IP"));
        serverTablePortColumn.setCellValueFactory(new PropertyValueFactory<>("port"));

        serverTable.getSelectionModel().selectedItemProperty().addListener((observableValue, serverModel, t1) -> {
            if (serverTable.getSelectionModel().getSelectedItem() != null){
                ServerModel item = serverTable.getSelectionModel().getSelectedItem();
                ipAddressInput.setText(item.getIP());
                portInput.setText(String.valueOf(item.getPort()));
            }
        });

        broadcastListener = new BroadcastListener(this);
        broadcastListener.start();
    }

    public void connectAction(ActionEvent actionEvent) throws IOException {
        //TODO
        if (usernameInput.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You need to input a username to connect");
            alert.showAndWait();
            return;
        }

        Socket clientSocket;

        try {
            clientSocket = new Socket(InetAddress.getByName(ipAddressInput.getText()), Integer.parseInt(portInput.getText()));
        } catch (UnknownHostException | ConnectException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Could not find server");
            alert.showAndWait();
            return;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Port must be a number");
            alert.showAndWait();
            return;
        }


        broadcastListener.interrupt();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Stage newStage = new Stage();
        newStage.setTitle(stage.getTitle());
        newStage.getIcons().setAll(stage.getIcons());

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/wiizz/jess/client/jessclient/fxml/lobby.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        LobbyController controller = loader.getController();
        controller.setClientSocket(clientSocket);

        newStage.setScene(scene);
        newStage.show();
        stage.close();
    }

    public boolean isServerInTable(String IP){
        for (ServerModel server: serverTable.getItems()) {
            if (Objects.equals(server.getIP(), IP)) return true;
        }
        return false;
    }

    public void addServerToTable(String name, String IP, String port) {
        serverTable.getItems().add(new ServerModel(name, IP, Integer.parseInt(port)));
    }
}
