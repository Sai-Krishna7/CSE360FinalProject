package com.example.loginprototype;

import java.time.Duration;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;


public class DefectConsole extends Application {

    public static void main(String[] args) {
        Application.launch(DefectConsole.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        // Title Label
        Label titleLabel = new Label("Defect Console");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        HBox titleContainer = new HBox(titleLabel);
        titleContainer.setAlignment(Pos.CENTER);
        BorderPane.setMargin(titleContainer, new Insets(20, 0, 20, 0));

        // Project Selection Label and ComboBox
        Label projectSelectionLabel = new Label("1. Select The Project");
        ComboBox<String> projectSelectionComboBox = new ComboBox<>();
        projectSelectionComboBox.setItems(FXCollections.observableArrayList("Project 1", "Project 2", "Project 3"));

        VBox projectSelectionContainer = new VBox(projectSelectionLabel, projectSelectionComboBox);
        projectSelectionContainer.setAlignment(Pos.TOP_LEFT);
        BorderPane.setMargin(projectSelectionContainer, new Insets(20, 0, 20, 20));

        // Clear Defect Log Button and Label
        Label clearDefectLogLabel = new Label("2.a. Clear this Project's Defect Log");
        Button clearDefectLogButton = new Button("Clear this Defect Log");

        VBox clearDefectLogContainer = new VBox(clearDefectLogLabel, clearDefectLogButton);
        clearDefectLogContainer.setAlignment(Pos.TOP_LEFT);
        clearDefectLogContainer.setSpacing(10);

        // Add spacing between project selection and clear defect log containers
        HBox spacer = new HBox();
        spacer.setMinWidth(50); // Adjust this value to move the button and its label further right

        // Combine project selection, spacer, and clear defect log containers
        HBox topContainer = new HBox(projectSelectionContainer, spacer, clearDefectLogContainer);
        topContainer.setSpacing(120);
        // Defect Selection Label and ComboBox
        Label defectSelectionLabel = new Label("2.b. Select one of the following defects to make it the Current Defect or press \"Create a New Defect\"");
        ComboBox<String> defectSelectionComboBox = new ComboBox<>();
        defectSelectionComboBox.setItems(FXCollections.observableArrayList("Defect 1", "Defect 2", "Defect 3"));

        VBox defectSelectionContainer = new VBox(defectSelectionLabel, defectSelectionComboBox);
        defectSelectionContainer.setAlignment(Pos.TOP_LEFT);
        defectSelectionContainer.setSpacing(10);
        BorderPane.setMargin(defectSelectionContainer, new Insets(0, 0, 20, 20));

        // Create New Defect Button
        Button createNewDefectButton = new Button("Create a New Defect");

        VBox createNewDefectContainer = new VBox(createNewDefectButton);
        createNewDefectContainer.setAlignment(Pos.TOP_LEFT);
        BorderPane.setMargin(createNewDefectContainer, new Insets(20, 0, 20, 0));

        // Combine defect selection and create new defect containers
        HBox defectSelectionAndCreateContainer = new HBox(defectSelectionContainer, createNewDefectContainer);
        defectSelectionAndCreateContainer.setSpacing(10);

        // Create a container to hold both topContainer and defectSelectionAndCreateContainer
        VBox leftContainer = new VBox(topContainer, defectSelectionAndCreateContainer);
        leftContainer.setSpacing(20);

        // Add titleContainer and leftContainer to the borderPane
        borderPane.setTop(titleContainer);
        borderPane.setLeft(leftContainer);

        // Defect Attributes Label
        Label defectAttributesLabel = new Label("3. Define or Update the following Current Defect attributes: Num     Defect Name");

        // Defect Attributes TextField
        TextField defectAttributesTextField = new TextField();
        defectAttributesTextField.setPrefWidth(20);

        // Combine defect attributes label and text field
        VBox defectAttributesContainer = new VBox(defectAttributesLabel, defectAttributesTextField);
        defectAttributesContainer.setAlignment(Pos.TOP_LEFT);
        defectAttributesContainer.setSpacing(10);
        BorderPane.setMargin(defectAttributesContainer, new Insets(0, 0, 20, 20));

        // Add defectAttributesContainer to the leftContainer
        leftContainer.getChildren().add(defectAttributesContainer);
        leftContainer.setSpacing(20);

        // Add titleContainer and leftContainer to the borderPane
        borderPane.setTop(titleContainer);
        borderPane.setLeft(leftContainer);

        // Status Label
        Label statusLabel = new Label("Status: open/closed");

        // Close Button
        Button closeButton = new Button("Close");

        // Reopen Button
        Button reopenButton = new Button("Reopen");

        // Buttons Container
        HBox buttonsContainer = new HBox(closeButton, reopenButton);
        buttonsContainer.setSpacing(10); // Adjust the spacing between the buttons

        // Add the status label and buttons container to the leftContainer
        leftContainer.getChildren().addAll(statusLabel, buttonsContainer);

        // Defect Symptoms or Cause/Resolution Label
        Label defectSymptomsLabel = new Label("Defect Symptoms or Cause/Resolution");

        // Defect Symptoms or Cause/Resolution Text Field
        TextField defectSymptomsTextField = new TextField();
        defectSymptomsTextField.setPrefHeight(100); // Set the preferred height of the text field

        // Add the label and text field to the leftContainer
        leftContainer.getChildren().addAll(defectSymptomsLabel, defectSymptomsTextField);

        // Update Information Label
        Label updateInfoLabel = new Label("4. Press \"Update Current Defect\" to save the changes made above.");

        // Buttons
        Button updateDefectButton = new Button("Update the Current Defect");
        Button deleteDefectButton = new Button("Delete the Current Defect");
        Button proceedToEffortLogButton = new Button("Proceed to the Effort Log Console");
        proceedToEffortLogButton.setOnAction(event -> {
            EffortConsole effortConsole = new EffortConsole();
            try {
                effortConsole.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Button container
        HBox buttonContainer = new HBox(updateDefectButton, deleteDefectButton, proceedToEffortLogButton);
        buttonContainer.setSpacing(10); // Set the spacing between buttons

        // Add the label and buttons to the leftContainer
        leftContainer.getChildren().addAll(updateInfoLabel, buttonContainer);


        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Defect Console");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
