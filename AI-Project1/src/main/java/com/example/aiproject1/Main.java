package com.example.aiproject1;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GENETIC ALGORITHM");

        // Background pic
        ImageView backgroundView = new ImageView();
        try {
            Image backgroundImg = new Image(getClass().getResource("/com/example/aiproject1/unnamed.jpg").toExternalForm());
            backgroundView.setImage(backgroundImg);
        } catch (Exception e) {
            System.out.println("Background image not found.");
        }

        backgroundView.setPreserveRatio(false);

        // Labels
        Label titleLabel = new Label("> GENETIC ALGORITHM: PROJECT ONE");
        titleLabel.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #00FF00;");

        Label subTitle = new Label("COMP338 - Artificial Intelligence");
        subTitle.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 16px; -fx-text-fill: #008F11;");

        // Button Start
        Button startBtn = createButton("START");
        startBtn.setOnAction(e -> {
            GeneticAlgorithmApp app = new GeneticAlgorithmApp();
            Stage appStage = new Stage();
            app.start(appStage);
            primaryStage.close();
        });

        VBox contentBox = new VBox(25, titleLabel, subTitle, startBtn);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(50));
        contentBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, contentBox);

        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root, 650, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button createButton(String labelText) {
        Button button = new Button(labelText);

        // Style btn
            button.setStyle(
                "-fx-background-color: #0d0d0d; " +
                        "-fx-border-color: #00FF00; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-text-fill: #00FF00; " +
                        "-fx-font-family: 'Courier New'; " +
                        "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 10px 25px;"
        );
        button.setPrefWidth(220);

        // Uh more styling

        DropShadow glow = new DropShadow();
        glow.setColor(Color.valueOf("#00FF00"));
        glow.setRadius(15);
        glow.setSpread(0.4);

        ScaleTransition scaleOn = new ScaleTransition(Duration.millis(150), button);
        scaleOn.setToX(1.1); scaleOn.setToY(1.1);

        ScaleTransition scaleOff = new ScaleTransition(Duration.millis(150), button);
        scaleOff.setToX(1.0); scaleOff.setToY(1.0);

        button.setOnMouseEntered(e -> {
            button.setEffect(glow);
            scaleOn.playFromStart();
        });

        button.setOnMouseExited(e -> {
            button.setEffect(null);
            scaleOff.playFromStart();
        });

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}