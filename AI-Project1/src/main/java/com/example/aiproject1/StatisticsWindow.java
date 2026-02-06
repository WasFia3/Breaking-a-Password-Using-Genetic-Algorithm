package com.example.aiproject1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StatisticsWindow {

    public void display(int totalGenerations, long executionTimeMillis, int bestFitness) {
        Stage stage = new Stage();
        stage.setTitle("REPORT");

        DropShadow glow = new DropShadow();
        glow.setColor(Color.LIME);
        glow.setRadius(10);
        glow.setSpread(0.5);

        Label header = new Label("SUCCESSFUL_PASSCODE_GUESS");
        header.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #00FF00;");
        header.setEffect(glow);

        Label genLabel = new Label("> ITERATIONS: " + totalGenerations + " gens");
        double seconds = executionTimeMillis / 1000.0;
        Label timeLabel = new Label("> TIME: " + seconds + "s (" + executionTimeMillis + "ms)");
        Label fitnessLabel = new Label("> FITTNESS: " + bestFitness + " / 32");

        String labelStyle = "-fx-font-family: 'Courier New'; -fx-font-size: 14px; -fx-text-fill: #00FF00;";
        genLabel.setStyle(labelStyle);
        timeLabel.setStyle(labelStyle);
        fitnessLabel.setStyle(labelStyle + " -fx-font-weight: bold;");

        Button closeBtn = new Button("OK");
        closeBtn.setStyle("-fx-background-color: #000; -fx-text-fill: #0f0; -fx-border-color: #0f0; -fx-font-family: 'Courier New';");
        closeBtn.setOnAction(e -> stage.close());

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setStyle("-fx-background-color: #050505; -fx-border-color: #00FF00; -fx-border-width: 2px;");
        layout.getChildren().addAll(header, genLabel, timeLabel, fitnessLabel, closeBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}