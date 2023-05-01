package com.example.loginprototype;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create column constraints
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70);

        // Add column constraints to the grid pane
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Add a label and a text field
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        // Add them to the grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        // Add a label and a text field with a longer text
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField("This is a longer text that should not push the adjacent column away.");

        // Add them to the grid pane
        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionField, 1, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Text Fields");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
