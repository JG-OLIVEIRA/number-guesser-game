package com.dev.jorge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class NumberGuesserApplication extends Application {

    int numberOfTries;
    int magicNumber;

    @Override
    public void start(Stage stage) throws Exception {
        var promptLabel = new Label("Guess a number between 1 and 10");
        var inputField = new TextField();
        var guessButton = new Button("Guess");
        var feedbackLabel = new Label();

        var layout = new VBox(10, promptLabel, inputField, guessButton, feedbackLabel);

        var scene = new Scene(layout, 300, 200);
        stage.setTitle("Number Guessing Game");
        stage.setScene(scene);
        stage.show();

        numberOfTries = 0;
        Random random = new Random();
        magicNumber = random.nextInt(10) + 1;

        guessButton.setOnAction(event -> {
            String userInput = inputField.getText();
            try {
                int guessedNumber = Integer.parseInt(userInput);
                numberOfTries++;
                if (guessedNumber < 1 || guessedNumber > 10) {
                    feedbackLabel.setText("Please enter a number between 1 and 10.");
                } else if (guessedNumber < magicNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (guessedNumber > magicNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the number in " + numberOfTries + " tries.");
                    guessButton.setDisable(true);
                }
            } catch (NumberFormatException e) {
                feedbackLabel.setText("Invalid input. Please enter a valid number.");
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(NumberGuesserApplication.class, args);
    }
}
