package com.example.loginprototype;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.LocalDateTime;

public class EffortConsole extends Application {
    private Label clockStatusLabel;
    private Label clockDurationLabel;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private boolean isClockRunning;
    private ComboBox<String> projectComboBox;
    private ComboBox<String> lifeCycleComboBox;
    private ComboBox<String> effortCategoryComboBox;
    private ComboBox<String> subordinateComboBox;
    private Button startButton;
    private Button stopButton;

    public void start(Stage primaryStage) throws Exception {
        this.clockStatusLabel = new Label("Clock is stopped");
        this.clockDurationLabel = new Label();
        this.projectComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Project 1", "Project 2", "Project 3"}));
        this.lifeCycleComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Design", "Development", "Testing"}));
        this.effortCategoryComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Coding", "Testing", "Documentation"}));
        this.subordinateComboBox = new ComboBox();
        this.startButton = new Button("Start An Activity");
        this.stopButton = new Button("Stop this Activity");
        this.stopButton.setDisable(true);
        this.startButton.setOnAction((event) -> {
            if (this.isSelectionValid()) {
                this.startClock();
                this.startButton.setDisable(true);
                this.stopButton.setDisable(false);
            } else {
                this.clockStatusLabel.setText("Please select project, life cycle, and effort category.");
            }

        });
        this.stopButton.setOnAction((event) -> {
            this.stopClock();
            this.startButton.setDisable(false);
            this.stopButton.setDisable(true);
        });
        this.effortCategoryComboBox.setOnAction((event) -> {
            String var2 = (String)this.effortCategoryComboBox.getValue();
        });
        Label headerLabel = new Label("Effort Console");
        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        VBox root = new VBox(new Node[]{headerLabel, new HBox(new Node[]{this.clockStatusLabel, this.clockDurationLabel}), new HBox(new Node[]{new Label("Project: "), this.projectComboBox}), new HBox(new Node[]{new Label("Life Cycle: "), this.lifeCycleComboBox}), new HBox(new Node[]{new Label("Effort Category: "), this.effortCategoryComboBox}), new HBox(new Node[]{new Label("Subordinate: "), this.subordinateComboBox}), new HBox(new Node[]{this.startButton, this.stopButton})});
        root.setSpacing(10.0D);
        root.setPadding(new Insets(10.0D));
        Button effortLogEditorButton = new Button("Effort Log Editor");
        effortLogEditorButton.setOnAction(event -> {
            EffortLogEditor effortLogEditor = new EffortLogEditor();
            try {
                effortLogEditor.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button defectLogConsoleButton = new Button("Defect Log Console");
        defectLogConsoleButton.setOnAction(event -> {
            DefectConsole defectConsole = new DefectConsole();
            try {
                defectConsole.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Connecting Effort Console Page with Definitions Page
        Button definitionsButton = new Button("Definitions");
        definitionsButton.setOnAction(event -> {
            DefinitionsPage definitionsPage = new DefinitionsPage();
            try {
                definitionsPage.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Button effortAndDefectLogsButton = new Button("Effort and Defect Logs");
        HBox bottomBox = new HBox(new Node[]{effortLogEditorButton, defectLogConsoleButton, definitionsButton, effortAndDefectLogsButton});
        bottomBox.setSpacing(10.0D);
        bottomBox.setPadding(new Insets(10.0D));
        BorderPane borderPane = new BorderPane(root);
        borderPane.setBottom(bottomBox);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Effort Console");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private boolean isSelectionValid() {
        String selectedProject = (String)this.projectComboBox.getValue();
        String selectedLifeCycle = (String)this.lifeCycleComboBox.getValue();
        String selectedEffortCategory = (String)this.effortCategoryComboBox.getValue();
        return selectedProject != null && !selectedProject.isEmpty() && selectedLifeCycle != null && !selectedLifeCycle.isEmpty() && selectedEffortCategory != null && !selectedEffortCategory.isEmpty();
    }

    private void startClock() {
        this.clockStatusLabel.setText("Clock is running");
        this.startTime = LocalDateTime.now();
        this.isClockRunning = true;
    }

    private void stopClock() {
        this.clockStatusLabel.setText("Clock is stopped\t");
        this.stopTime = LocalDateTime.now();
        this.isClockRunning = false;
        Duration duration = Duration.between(this.startTime, this.stopTime);
        long minutes = duration.toMinutes();
        double seconds = (double)(duration.getSeconds() % 60L) / 60.0D;
        String durationString = String.format("%.2f", (double)minutes + seconds);
        this.clockDurationLabel.setText("Duration: " + durationString + " minutes");
    }

    public static void main(String[] args) {
        launch(args);
    }
}