package com.example.loginprototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
Class: CSE 360 - Group M08
Author: Sai Krishna Deeduvanu
Risk Reduction Prototype: Sign Up / Log In to Effort Logger Console Prototype with a MySQL backend database
Info: In order to fully run this prototype and test out SQL injection please install MySQL and MySQL workbench and
create and replace user local host user and password details below to access the database.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
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
        Button loginButton = new Button("Log In to Effort Console");
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

        //Clear button to delete the SQL entries in the database
        Button clearButton = new Button("Clear");
        HBox hbclearButton = new HBox(10);
        hbclearButton.setAlignment(Pos.BOTTOM_LEFT);
        hbclearButton.getChildren().add(clearButton);
        grid.add(hbclearButton, 0, 5);

        // Create the message label for displaying login status
        final Text message = new Text();
        grid.add(message, 1, 6);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                try {
                    //Connect to database and pass queries to authenticate users.
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
                            message.setText("Authenticated! Continue to Effort Logger Console.");

//                            Parent root;
//                            try {
//                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Scene1.fxml")));
//                                Stage stage = new Stage();
//                                stage.setTitle("My New Stage Title");
//                                stage.setScene(new Scene(root, 450, 450));
//                                stage.show();
//                                // Hide this current window (if this is what you want)
//                                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
//                            }
//                            catch (IOException e) {
//                                e.printStackTrace();
//                            }

                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("Scene1.fxml"));
                                /*
                                 * if "fx:controller" is not set in fxml
                                 * fxmlLoader.setController(NewWindowController);
                                 */
                                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                                Stage stage = new Stage();
                                stage.setTitle("Scene1");
                                stage.setScene(scene);
                                stage.centerOnScreen();
                                stage.hide();
                                // Hide this current window (if this is what you want)
                                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                            } catch (IOException e) {
                                Logger logger = Logger.getLogger(getClass().getName());
                                logger.log(Level.SEVERE, "Failed to create new Window.", e);
                            }
                            
                        } else {
                            //System.out.println("Login unsuccessful");
                            message.setFill(javafx.scene.paint.Color.RED);
                            message.setText("Login Unsuccessful! Invalid username or password.");
                        }
                    } else {
                        //System.out.println("Username not found");
                        message.setFill(javafx.scene.paint.Color.RED);
                        message.setText("User Not Found");
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

                // Connect to the database and authenticate existing users or create new users and insert to database
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseconnect", "root", "2002_Deeduvanu");

                    String query1 = "SELECT UserPassword FROM UserData WHERE UserName = ?";
                    PreparedStatement statement = connection.prepareStatement(query1);
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String passwordFromDatabase = resultSet.getString("UserPassword");
                        String enteredPassword = password;
                        if (passwordFromDatabase.equals(enteredPassword)) {
                            //System.out.println("User already exx successful");
                            message.setFill(javafx.scene.paint.Color.RED);
                            message.setText("User already exists");
                        } else {
                            //System.out.println("Login unsuccessful");
                            message.setFill(javafx.scene.paint.Color.RED);
                            message.setText("Username is not unique.");
                        }
                    } else {
                        String query = "INSERT INTO UserData (UserName, UserPassword) VALUES (?, ?)";
                        PreparedStatement statement1 = connection.prepareStatement(query);
                        statement1.setString(1, username);
                        statement1.setString(2, password);
                        statement1.executeUpdate();
                        message.setFill(javafx.scene.paint.Color.GREEN);
                        message.setText("Sign Up successful!");

                        // Close the statement and connection
                        statement1.close();
                    }
                    statement.close();
                    connection.close();

                    // Insert the data into the table

                } catch (SQLException e) {
                    e.printStackTrace();
                    message.setFill(javafx.scene.paint.Color.RED);
                    message.setText("Invalid username or password.");
                }
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Clear all entries in database, simple functionality to make it easy to clear database.
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/databaseconnect", "root", "2002_Deeduvanu");

                    // Insert the data into the table
                    String query = "DELETE FROM UserData";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.executeUpdate();
                    message.setFill(javafx.scene.paint.Color.GREEN);
                    message.setText("Clear Successful!");

                    // Close the statement and connection
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    message.setFill(javafx.scene.paint.Color.RED);
                    message.setText("Clear Unsuccessful");
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




//
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)



//
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//    private Label clockStatusLabel;
//    private Label clockDurationLabel;
//    private LocalDateTime startTime;
//    private LocalDateTime stopTime;
//    private boolean isClockRunning;
//    private ComboBox<String> projectComboBox;
//    private ComboBox<String> lifeCycleComboBox;
//    private ComboBox<String> effortCategoryComboBox;
//    private ComboBox<String> subordinateComboBox;
//    private Button startButton;
//    private Button stopButton;
//
//    public void start(Stage primaryStage) throws Exception {
//        this.clockStatusLabel = new Label("Clock is stopped");
//        this.clockDurationLabel = new Label();
//        this.projectComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Project 1", "Project 2", "Project 3"}));
//        this.lifeCycleComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Design", "Development", "Testing"}));
//        this.effortCategoryComboBox = new ComboBox(FXCollections.observableArrayList(new String[]{"Coding", "Testing", "Documentation"}));
//        this.subordinateComboBox = new ComboBox();
//        this.startButton = new Button("Start An Activity");
//        this.stopButton = new Button("Stop this Activity");
//        this.stopButton.setDisable(true);
//        this.startButton.setOnAction((event) -> {
//            if (this.isSelectionValid()) {
//                this.startClock();
//                this.startButton.setDisable(true);
//                this.stopButton.setDisable(false);
//            } else {
//                this.clockStatusLabel.setText("Please select project, life cycle, and effort category.");
//            }
//
//        });
//        this.stopButton.setOnAction((event) -> {
//            this.stopClock();
//            this.startButton.setDisable(false);
//            this.stopButton.setDisable(true);
//        });
//        this.effortCategoryComboBox.setOnAction((event) -> {
//            String var2 = (String)this.effortCategoryComboBox.getValue();
//        });
//        Label headerLabel = new Label("Effort Console");
//        headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
//        VBox root = new VBox(new Node[]{headerLabel, new HBox(new Node[]{this.clockStatusLabel, this.clockDurationLabel}), new HBox(new Node[]{new Label("Project: "), this.projectComboBox}), new HBox(new Node[]{new Label("Life Cycle: "), this.lifeCycleComboBox}), new HBox(new Node[]{new Label("Effort Category: "), this.effortCategoryComboBox}), new HBox(new Node[]{new Label("Subordinate: "), this.subordinateComboBox}), new HBox(new Node[]{this.startButton, this.stopButton})});
//        root.setSpacing(10.0D);
//        root.setPadding(new Insets(10.0D));
//        Button effortLogEditorButton = new Button("Effort Log Editor");
//        Button defectLogConsoleButton = new Button("Defect Log Console");
//        Button definitionsButton = new Button("Definitions");
//        Button effortAndDefectLogsButton = new Button("Effort and Defect Logs");
//        HBox bottomBox = new HBox(new Node[]{effortLogEditorButton, defectLogConsoleButton, definitionsButton, effortAndDefectLogsButton});
//        bottomBox.setSpacing(10.0D);
//        bottomBox.setPadding(new Insets(10.0D));
//        BorderPane borderPane = new BorderPane(root);
//        borderPane.setBottom(bottomBox);
//        Scene scene = new Scene(borderPane);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Effort Console");
//        primaryStage.show();
//    }
//
//    private boolean isSelectionValid() {
//        String selectedProject = (String)this.projectComboBox.getValue();
//        String selectedLifeCycle = (String)this.lifeCycleComboBox.getValue();
//        String selectedEffortCategory = (String)this.effortCategoryComboBox.getValue();
//        return selectedProject != null && !selectedProject.isEmpty() && selectedLifeCycle != null && !selectedLifeCycle.isEmpty() && selectedEffortCategory != null && !selectedEffortCategory.isEmpty();
//    }
//
//    private void startClock() {
//        this.clockStatusLabel.setText("Clock is running");
//        this.startTime = LocalDateTime.now();
//        this.isClockRunning = true;
//    }
//
//    private void stopClock() {
//        this.clockStatusLabel.setText("Clock is stopped\t");
//        this.stopTime = LocalDateTime.now();
//        this.isClockRunning = false;
//        Duration duration = Duration.between(this.startTime, this.stopTime);
//        long minutes = duration.toMinutes();
//        double seconds = (double)(duration.getSeconds() % 60L) / 60.0D;
//        String durationString = String.format("%.2f", (double)minutes + seconds);
//        this.clockDurationLabel.setText("Duration: " + durationString + " minutes");
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}

