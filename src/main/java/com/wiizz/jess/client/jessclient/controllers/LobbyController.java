package com.wiizz.jess.client.jessclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class LobbyController {
    public TableView<GameModel> gamesTable;
    public TableColumn<GameModel, String> gamesTableUserColumn;
    public TableColumn<GameModel, String> gamesTableTimeColumn;
    public TableColumn<GameModel, Boolean> gamesTablePasswordColumn;
    public Button createButton;
    public Label serverName;
    public PasswordField passwordInput;
    public Button joinButton;

    private Socket clientSocket;

    private BufferedReader in;
    private BufferedWriter out;

    private GameModel selectedGame;

    public void initialize(){
        gamesTableUserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        gamesTableTimeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        gamesTablePasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        gamesTable.getSelectionModel().selectedItemProperty().addListener((observableValue, serverModel, t1) -> {
            if (gamesTable.getSelectionModel().getSelectedItem() != null){
                selectedGame = gamesTable.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void setClientSocket(Socket clientSocket){
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        downloadGameList();
    }

    public void downloadGameList(){
        String received;

        try {
            received = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (received.isEmpty()) return;

        String[] games = received.split(";");
        for (String game: games) {
            String[] args = game.split(",");
            gamesTable.getItems().add(new GameModel(args[0], args[1], Boolean.getBoolean(args[2])));
        }
    }

    public void setServerName(String serverName){
        this.serverName.setText(serverName);
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
