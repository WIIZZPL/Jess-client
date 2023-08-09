module com.wiizz.jess.client.jessclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wiizz.jess.client.jessclient to javafx.fxml;
    exports com.wiizz.jess.client.jessclient;
    exports com.wiizz.jess.client.jessclient.controllers;
    opens com.wiizz.jess.client.jessclient.controllers to javafx.fxml;
}