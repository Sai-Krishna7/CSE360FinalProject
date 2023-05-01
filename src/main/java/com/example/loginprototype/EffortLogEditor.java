package com.example.loginprototype;

import javafx.application.Application;
import java.time.Duration;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EffortLogEditor extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create the grid pane and set its properties
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(20);

        // Set the title at the top in the center
        Label titleLabel = new Label("Effort Log Editor");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(titleLabel, 0, 0, 2, 1);
        gridPane.getChildren().add(titleLabel);

        // Add the rows with labels and combo boxes
        //First Row
        Label label1 = new Label("Project Number");
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.setPrefWidth(200);
        GridPane.setConstraints(label1, 0, 1);
        GridPane.setConstraints(comboBox1, 1, 1);
        gridPane.getChildren().addAll(label1, comboBox1);

        // Spacing between combo box and text field
        gridPane.add(new Label(), 2, 1);

        // Second text field and label
        Label label2 = new Label("Project Type");
        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.setPrefWidth(100);
        GridPane.setConstraints(label2, 3, 1);
        GridPane.setConstraints(comboBox2, 4, 1);
        gridPane.getChildren().addAll(label2, comboBox2);

        //Second Row
        Label label3 = new Label("Select Effort Log Entry to Edit");
        ComboBox<String> comboBox3 = new ComboBox<>();
        comboBox3.setPrefWidth(300);
        GridPane.setConstraints(label3, 0, 2);
        GridPane.setConstraints(comboBox3, 1, 2, 2, 1);
        gridPane.getChildren().addAll(label3, comboBox3);

        //Third Row
        Label label4 = new Label("Modify Date");
        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(150);
        GridPane.setConstraints(label4, 0, 3);
        GridPane.setConstraints(datePicker, 1, 3);
        gridPane.getChildren().addAll(label4, datePicker);

        gridPane.add(new Label(), 2, 3);

        Label label5 = new Label("Life Cycle Step");
        ComboBox<String> comboBox4 = new ComboBox<>();
        comboBox4.setPrefWidth(100);
        GridPane.setConstraints(label5, 3, 3);
        GridPane.setConstraints(comboBox4, 4, 3);
        gridPane.getChildren().addAll(label5, comboBox4);

        //Fourth Row
        Label label6 = new Label("Modify Start Time");
        TextField textField1 = new TextField();
        textField1.setPrefWidth(150);
        GridPane.setConstraints(label6, 0, 4);
        GridPane.setConstraints(textField1, 1, 4);
        gridPane.getChildren().addAll(label6, textField1);

        gridPane.add(new Label(), 2, 4);

        Label label7 = new Label("Effort Category");
        ComboBox<String> comboBox5 = new ComboBox<>();
        comboBox5.setPrefWidth(100);
        GridPane.setConstraints(label7, 3, 4);
        GridPane.setConstraints(comboBox5, 4, 4);
        gridPane.getChildren().addAll(label7, comboBox5);

        //Fifth Row
        Label label8 = new Label("Modify Stop Time");
        TextField textField2 = new TextField();
        textField2.setPrefWidth(150);
        GridPane.setConstraints(label8, 0, 5);
        GridPane.setConstraints(textField2, 1, 5);
        gridPane.getChildren().addAll(label8, textField2);

        gridPane.add(new Label(), 2, 5);

        Label label9 = new Label("Deliverable Type");
        ComboBox<String> comboBox6 = new ComboBox<>();
        comboBox6.setPrefWidth(100);
        GridPane.setConstraints(label9, 3, 5);
        GridPane.setConstraints(comboBox6, 4, 5);
        gridPane.getChildren().addAll(label9, comboBox6);

        //Sixth Row
        Label label10 = new Label("Modify Effort Estimate");
        TextField textField3 = new TextField();
        textField3.setPrefWidth(150);
        GridPane.setConstraints(label10, 0, 6);
        GridPane.setConstraints(textField3, 1, 6);
        gridPane.getChildren().addAll(label10, textField3);

        gridPane.add(new Label(), 2, 6);

        Button updateEntry = new Button("Update This Entry");
        //updateEntry.setPrefWidth(100);
        GridPane.setConstraints(updateEntry, 3, 6);
        Button deleteEntry = new Button("Delete This Entry");
        //deleteEntry.setPrefWidth(100);
        GridPane.setConstraints(deleteEntry, 4, 6);
        gridPane.getChildren().addAll(updateEntry, deleteEntry);

        //Seventh Row
        Button splitEntry = new Button("Split This Entry into Two Entries");
        //updateEntry.setPrefWidth(100);
        GridPane.setConstraints(splitEntry, 3, 7, 2, 1);
        gridPane.getChildren().addAll(splitEntry);
        final Text message = new Text();
        gridPane.add(message, 0, 7, 2, 1);
        updateEntry.setOnAction(event -> {
            message.setFill(Color.FORESTGREEN);
            message.setText("These changes have been updated and saved.");
        });

        deleteEntry.setOnAction(event -> {
            message.setFill(Color.RED);
            message.setText("These changes have been deleted.");
        });

        splitEntry.setOnAction(event -> {
            message.setFill(Color.FORESTGREEN);
            message.setText("This entry has been split into two and saved.");
        });

        //Eight Row
        Button clearLog = new Button("Clear This Effort Log");
        //updateEntry.setPrefWidth(100);
        GridPane.setConstraints(clearLog, 0, 8);
        Button proceedToEffortConsole = new Button("Proceed to Effort Console");
        proceedToEffortConsole.setOnAction(event -> {
            EffortConsole effortConsole = new EffortConsole();
            try {
                effortConsole.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //deleteEntry.setPrefWidth(100);
        GridPane.setConstraints(proceedToEffortConsole, 3, 8, 2, 1);
        gridPane.getChildren().addAll(clearLog, proceedToEffortConsole);

        clearLog.setOnAction(event -> {
            message.setFill(Color.RED);
            message.setText("The Effort Log has been cleared.");
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




