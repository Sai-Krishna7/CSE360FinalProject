package com.example.loginprototype;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;

public class HelloController {
    public void connectButton(ActionEvent event)
    {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();

        String connectQuery = "";
    }
}