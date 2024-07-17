package org.example.lab3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Validation");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Name
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        // Contact Number
        Label contactLabel = new Label("Contact Number:");
        GridPane.setConstraints(contactLabel, 0, 1);
        TextField contactInput = new TextField();
        GridPane.setConstraints(contactInput, 1, 1);

        // Postal Code
        Label postalLabel = new Label("Postal Code:");
        GridPane.setConstraints(postalLabel, 0, 2);
        TextField postalInput = new TextField();
        GridPane.setConstraints(postalInput, 1, 2);

        // Validate Button
        Button validateButton = new Button("Validate");
        GridPane.setConstraints(validateButton, 1, 3);
        validateButton.setOnAction(e -> validateInput(nameInput, contactInput, postalInput));

        grid.getChildren().addAll(nameLabel, nameInput, contactLabel, contactInput, postalLabel, postalInput, validateButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void validateInput(TextField nameInput, TextField contactInput, TextField postalInput) {
        String name = nameInput.getText();
        String contact = contactInput.getText();
        String postal = postalInput.getText();

        if (!name.matches("[A-Z][a-zA-Z]*")) {
            showAlert("Invalid Username", "The username must start with an uppercase letter followed by letters.");
        } else if (!contact.matches("\\d{3} \\d{3} \\d{4}") && !contact.matches("\\(\\d{3}\\) \\d{3} \\d{4}")) {
            showAlert("Invalid Contact Number", "The contact number must be exactly 10 digits in the format XXX XXX XXXX or (XXX) XXX XXXX.");
        } else if (!postal.matches("[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d") && !postal.matches("[A-Za-z]\\d[A-Za-z]-?\\d[A-Za-z]\\d")) {
            showAlert("Invalid Postal Code", "The postal code must be in the format P6R 2V8, P6r-2V8, p6r 2v8, or p6r-2v8.");
        } else {
            showAlert("Success", "All inputs are valid!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
