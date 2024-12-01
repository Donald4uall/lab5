import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Form");

        // Create labels and input fields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Button submitButton = new Button("Submit");

        // Create a grid layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add components to the grid
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        gridPane.add(ageLabel, 0, 1);
        gridPane.add(ageField, 1, 1);

        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);

        gridPane.add(submitButton, 1, 3);

        // Handle button click
        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String email = emailField.getText().trim();

            // Validate input
            if (name.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required!");
                return;
            }

            if (!ageText.matches("\\d+")) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Age must be a number!");
                return;
            }

            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Age must be greater than zero!");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid email format!");
                return;
            }

            // Show success pop-up
            showAlert(Alert.AlertType.INFORMATION, "Submission Successful",
                    String.format("Name: %s\nAge: %d\nEmail: %s", name, age, email));
        });

        // Create and set the scene
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to display alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
