package com.example.loginprototype;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DefinitionsPage extends Application {

    public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Set the width for the first column
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(100); // Set the minimum width of the first column
        gridPane.getColumnConstraints().add(column1);

        gridPane.setAlignment(Pos.CENTER_LEFT); // Set alignment to left

        // Project names
        Label projectNamesLabel = new Label("1a - Specify Project Names Here:");
        projectNamesLabel.setMinWidth(250);
        gridPane.add(projectNamesLabel, 0, 1); // Adjust row position
        GridPane.setMargin(projectNamesLabel, new Insets(0, 1, 0, 0)); // Add margin to create space

        for (int i = 0; i < 10; i++) {
            gridPane.add(new Label(Integer.toString(i + 1)), 0, i + 2); // Adjust row position
            TextField projectTextField = new TextField();
            projectTextField.setMinWidth(100);
            projectTextField.setPrefWidth(250);
            gridPane.add(projectTextField, 1, i + 2); // Adjust row position
        }

        // Life cycle steps for each project
        Label lifeCycleLabel = new Label("1b - Specify Life Cycle Steps for each Project:");
        lifeCycleLabel.setMinWidth(300);
        gridPane.add(lifeCycleLabel, 2, 0, 1, 1); // Adjust column and row span
        GridPane.setMargin(lifeCycleLabel, new Insets(0, 0, 0, 20)); // Add margin to create space

        // Row of numbers above life cycle text fields
        for (int i = 0; i < 26; i++) {
            gridPane.add(new Label(Integer.toString(i + 1)), i + 3, 1); // Adjust column position
            ColumnConstraints column = new ColumnConstraints();
            column.setPrefWidth(50); // Set the preferred width for the column
            gridPane.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < 26; j++) {
                TextField textField = new TextField();

                textField.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (newValue.length() > 2) {
                            textField.setText(oldValue);
                        } else {
                            if (!newValue.matches("\\d*")) {
                                textField.setText(newValue.replaceAll("[^\\d]", ""));
                            }
                        }
                    }
                });

                gridPane.add(textField, j + 2, i + 2); // Adjust column position
                textField.setMaxWidth(25);
                textField.setMinWidth(25);
            }
        }

        // Add Effort Categories directly
        Label effortCategoriesTitle = new Label("Effort Categories");
        effortCategoriesTitle.setStyle("-fx-font-weight: bold;");
        gridPane.add(effortCategoriesTitle, 0, 14);

        String[] categories = {
                "1. Plans",
                "2. Deliverables",
                "3. Interruptions",
                "4. Defects",
                "5. Other"
        };

        for (int i = 0; i < categories.length; i++) {
            gridPane.add(new Label(categories[i]), 0, i + 15);
        }


        // Fill out the additional columns
        int[] column1Values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};
        String[] column2Texts = {
                "Problem Understanding", "Conceptual Design Plan", "Requirements", "Conceptual Design", "Conceptual Design Review",
                "Detailed Design Plan", "Detailed Design/Prototype", "Detailed Design Review", "Implementation Plan",
                "Test Case Generation", "Solution Specification", "Solution Review", "Solution Implementation",
                "Unit/System Test", "Reflection", "Repository Update", "Planning", "Information Gathering",
                "Information Understanding", "Verifying", "Outlining", "Drafting", "Finalizing",
                "Team Meeting", "Coach Meeting", "Stakeholder Meeting"
        };
        int[] column3Values = {2,1,2,2,2,1,2,2,1,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2,2};
        int[] column4Values = {1, 3, 1, 1, 1, 4, 2, 2, 5, 3, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 6, 7, 8, 1, 1, 1};
        Label lab = new Label("2. SPECIFY LIFESCYCLE STEPS HERE: ");
        Label ec = new Label("EC");
        Label d = new Label("D");
        lab.setMinWidth(250);
        gridPane.add(lab ,2, 14);
        gridPane.add(ec, 6, 14);
        gridPane.add(d, 7, 14);
        for (int i = 0; i < column2Texts.length; i++) {
            gridPane.add(new Label(Integer.toString(column1Values[i])), 2, i + 15); // Add column1Values before column2Texts
            Label label = new Label(column2Texts[i]);
            label.setMaxWidth(150); // Set a fixed maximum width for the label
            label.setMinWidth(250);
            gridPane.add(label, 3, i + 15); // Adjust column position
            gridPane.add(new Label(Integer.toString(column3Values[i])), 6, i + 15); // Adjust column position
            gridPane.add(new Label(Integer.toString(column4Values[i])), 7, i + 15); // Adjust column position
        }


        // Add buttons at the bottom
        Button proceedToEffortLogConsoleBtn = new Button("Proceed to Effort Log Console");
        proceedToEffortLogConsoleBtn.setOnAction(event -> {
            EffortConsole effortConsole = new EffortConsole();
            try {
                effortConsole.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button proceedToDefectLogConsoleBtn = new Button("Proceed to Defect Log Console");
        Button proceedToEffortLogEditorBtn = new Button("Proceed to Effort Log Editor");
        Button proceedToEffortAndDefectLogsBtn = new Button("Proceed to Effort and Defect Logs");

        // Set row number and column span
        int bottomRow = 42; // Adjust this value if needed to position the buttons below everything
        gridPane.add(proceedToEffortLogConsoleBtn, 1, bottomRow);
        gridPane.add(proceedToDefectLogConsoleBtn, 4, bottomRow);
        gridPane.add(proceedToEffortLogEditorBtn, 7, bottomRow);
        gridPane.add(proceedToEffortAndDefectLogsBtn, 10, bottomRow);

        // Set the minimum width for the buttons
        proceedToEffortLogConsoleBtn.setMinWidth(200); // Adjust this value as needed
        proceedToDefectLogConsoleBtn.setMinWidth(200); // Adjust this value as needed
        proceedToEffortLogEditorBtn.setMinWidth(200); // Adjust this value as needed
        proceedToEffortAndDefectLogsBtn.setMinWidth(200); // Adjust this value as needed

        // Set margins for the buttons
        GridPane.setMargin(proceedToEffortLogConsoleBtn, new Insets(20, 0, 0, 0));
        GridPane.setMargin(proceedToDefectLogConsoleBtn, new Insets(20, 0, 0, 0));
        GridPane.setMargin(proceedToEffortLogEditorBtn, new Insets(20, 0, 0, 0));
        GridPane.setMargin(proceedToEffortAndDefectLogsBtn, new Insets(20, 0, 0, 0));

        // Set horizontal and vertical alignment for the buttons
        GridPane.setHalignment(proceedToEffortLogConsoleBtn, HPos.CENTER);
        GridPane.setValignment(proceedToEffortLogConsoleBtn, VPos.CENTER);
        GridPane.setHalignment(proceedToDefectLogConsoleBtn, HPos.CENTER);
        GridPane.setValignment(proceedToDefectLogConsoleBtn, VPos.CENTER);
        GridPane.setHalignment(proceedToEffortLogEditorBtn, HPos.CENTER);
        GridPane.setValignment(proceedToEffortLogEditorBtn, VPos.CENTER);
        GridPane.setHalignment(proceedToEffortAndDefectLogsBtn, HPos.CENTER);
        GridPane.setValignment(proceedToEffortAndDefectLogsBtn, VPos.CENTER);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true); // This makes the content fit the width of the ScrollPane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        BorderPane definitionsBorderPane = new BorderPane();
        definitionsBorderPane.setPadding(new Insets(10));
        definitionsBorderPane.setCenter(scrollPane);

        Scene definitionsScene = new Scene(definitionsBorderPane, 800, 600);
        primaryStage.setScene(definitionsScene);
        primaryStage.setTitle("Definitions Page");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
