package com.example.loginprototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class Scene1 implements Initializable {

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // Create an instance of the OtherJavaClass
        EffortConsole otherClass = new EffortConsole();
        // Call a method or pass data to the other Java class
        Stage primaryStage = new Stage();
        try {
            otherClass.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
