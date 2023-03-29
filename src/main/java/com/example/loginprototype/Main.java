package com.example.loginprototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
        primaryStage.setTitle("Login");
        primaryStage.centerOnScreen();

        // Create the GridPane layout to hold the login form
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create the title
        Text title = new Text("SignUp/Login");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        // Create the username label and text field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);
        TextField usernameField = new TextField();
        grid.add(usernameField, 1, 1);

        // Create the password label and password field
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        // Create the Log In button
        Button loginButton = new Button("Log In");
        HBox hbloginButton = new HBox(10);
        hbloginButton.setAlignment(Pos.BOTTOM_LEFT);
        hbloginButton.getChildren().add(loginButton);
        grid.add(hbloginButton, 0, 4);

        // Create the Sign Up button
        Button signupButton = new Button("Sign Up");
        HBox hbSignUpButton = new HBox(10);
        hbSignUpButton.setAlignment(Pos.BOTTOM_RIGHT);
        hbSignUpButton.getChildren().add(signupButton);
        grid.add(hbSignUpButton, 1, 4);

        // Create the message label for displaying login status
        final Text message = new Text();
        grid.add(message, 1, 6);

        // Set the action for the login button
//        loginButton.setOnAction(event -> {
//            String username = usernameField.getText();
//            String password = passwordField.getText();
//
//            // Check if the username and password are correct
//            if (username.equals("admin") && password.equals("password")) {
//                message.setFill(javafx.scene.paint.Color.GREEN);
//                message.setText("Login successful!");
//            } else {
//                message.setFill(javafx.scene.paint.Color.RED);
//                message.setText("Invalid username or password.");
//            }
//        });

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseconnect", "root", "2002_Deeduvanu");
                    String query = "SELECT UserPassword FROM UserData WHERE UserName = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String passwordFromDatabase = resultSet.getString("UserPassword");
                        String enteredPassword = password;
                        if (passwordFromDatabase.equals(enteredPassword)) {
                            System.out.println("Login successful");
                            message.setFill(javafx.scene.paint.Color.GREEN);
                            message.setText("Login successful!");
                        } else {
                            //System.out.println("Login unsuccessful");
                            message.setFill(javafx.scene.paint.Color.RED);
                            message.setText("Login Unsuccessful! Invalid username or password.");
                        }
                    } else {
                        //System.out.println("Username not found");
                        message.setFill(javafx.scene.paint.Color.RED);
                        message.setText("Username not found");
                    }
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                // Connect to the database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseconnect", "root", "2002_Deeduvanu");

                    // Insert the data into the table
                    String query = "INSERT INTO UserData (UserName, UserPassword) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, password);
                    statement.executeUpdate();
                    message.setFill(javafx.scene.paint.Color.GREEN);
                    message.setText("Sign Up successful!");

                    // Close the statement and connection
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    message.setFill(javafx.scene.paint.Color.RED);
                    message.setText("Invalid username or password.");
                }
            }
        });

        // Create the scene and set it on the stage
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}