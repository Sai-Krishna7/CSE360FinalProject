package com.example.loginprototype;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EffortAndDefectLogs extends Application {

    @Override
    public void start(Stage stage) {
        // Define the columns of the table
        TableColumn<Person, String> dateCol = new TableColumn<>("Effort Log for Project 1");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Business Project");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        // Create the "Delete" button column
        TableColumn<Person, Void> deleteCol = new TableColumn<>("Delete");
        deleteCol.setCellFactory(param -> new TableCell<Person, Void>() {
            private final Button proceedToEffortConsole = new Button("Proceed to Effort Console");

            {
                proceedToEffortConsole.setOnAction(event -> {
                    EffortConsole effortConsole = new EffortConsole();
                    try {
                        effortConsole.start(stage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(proceedToEffortConsole);
                }
            }
        });

        // Add the columns to the table
        TableView<Person> table = new TableView<>();
        table.getColumns().addAll(dateCol, ageCol, deleteCol);

        // Create the rows of the table
        ObservableList<Person> data = FXCollections.observableArrayList(
                new Person("John Doe", 30),
                new Person("Jane Smith", 25),
                new Person("Bob Johnson", 40)
        );

        // Add the rows to the table
        table.setItems(data);

        // Display the table in a window
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

}


