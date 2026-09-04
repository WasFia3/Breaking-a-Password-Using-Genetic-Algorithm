package com.example.aiproject1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GeneticAlgorithmApp extends Application {
    private LineChart<Number, Number> lineChart;
    private TextArea logArea;
    private TextField mutField, popField;
    private int experimentCount = 0;

    public static int lastTotalGenerations = 0;
    public static long lastExecutionTime = 0;

    @Override
    public void start(Stage stage) {
        stage.setTitle("GENETIC ALGORITHM");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/aiproject1/logo.png")));
        ImageView backgroundView = new ImageView();
        try {
            backgroundView.setImage(new Image(getClass().getResource("/com/example/aiproject1/unnamed.jpg").toExternalForm()));
        } catch (Exception ignored) {}
        backgroundView.setPreserveRatio(false);

        NumberAxis xAxis = new NumberAxis(); xAxis.setLabel("GENERATIONS");
        NumberAxis yAxis = new NumberAxis(0, 32, 4); yAxis.setLabel("FITNESS");

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);

        lineChart.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-chart-plot-background: rgba(0,0,0,0.1);");

        String fieldStyle = "-fx-background-color: #050505; -fx-text-fill: #00FF00; -fx-border-color: #00FF00; -fx-border-width: 1px; -fx-font-family: 'Courier New';";
        mutField = new TextField("0.01"); mutField.setPrefWidth(60); mutField.setStyle(fieldStyle);
        popField = new TextField("100"); popField.setPrefWidth(60); popField.setStyle(fieldStyle);

        Button startBtn = new Button("EXECUTE");
        startBtn.setStyle("-fx-background-color: #000; -fx-text-fill: #0f0; -fx-border-color: #0f0; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");

        Button backBtn = new Button("EXIT");
        backBtn.setStyle("-fx-background-color: #000; -fx-text-fill: #f00; -fx-border-color: #f00; -fx-font-family: 'Courier New';");
        backBtn.setOnAction(e -> { new Main().start(new Stage()); stage.close(); });

        Label mLabel = new Label("MUTATION:"); mLabel.setStyle("-fx-text-fill: #0f0; -fx-font-family: 'Courier New';");
        Label pLabel = new Label("POPULATION:"); pLabel.setStyle("-fx-text-fill: #0f0; -fx-font-family: 'Courier New';");

        HBox controls = new HBox(15, mLabel, mutField, pLabel, popField, startBtn, backBtn);
        controls.setPadding(new Insets(15));
        controls.setAlignment(Pos.CENTER);
        controls.setStyle("-fx-background-color: rgba(0, 10, 0, 0.85); -fx-border-color: #00FF00; -fx-border-width: 2px; -fx-border-radius: 5px;");

        logArea = new TextArea();
        logArea.setPrefHeight(150);
        logArea.setStyle("-fx-control-inner-background: #000; -fx-text-fill: #0f0; -fx-font-family: 'Courier New'; -fx-border-color: #0f0;");

        startBtn.setOnAction(e -> startExperiment());

        VBox layout = new VBox(10, lineChart, controls, logArea);
        layout.setPadding(new Insets(15));

        StackPane root = new StackPane(backgroundView, layout);
        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        Scene scene = new Scene(root, 1000, 750);
        scene.getStylesheets().add("data:text/css,.axis {-fx-tick-label-fill: #0f0;} .axis-label {-fx-text-fill: #0f0;}");
        stage.setScene(scene);
        stage.show();
    }

    private void startExperiment() {
        try {
            double mRate = Double.parseDouble(mutField.getText());
            int pSize = Integer.parseInt(popField.getText());

            if (mRate > 1.0 || mRate < 0) {
                showError("Invalid Mutation Rate", "Rate must be between 0 and 1.");
                return;
            }

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("EXP " + (++experimentCount) + " (M=" + mRate + ", P=" + pSize + ")");
            lineChart.getData().add(series);

            long startTime = System.currentTimeMillis();

            new Thread(() -> {
                GeneticAlgorithm ga = new GeneticAlgorithm(pSize, mRate);
                ga.setOnUpdateUI((gen, fit) -> Platform.runLater(() -> {
                    series.getData().add(new XYChart.Data<>(gen, fit));
                    logArea.appendText("GENERATION " + gen + " | FIT: " + fit + "\n");
                    logArea.setScrollTop(Double.MAX_VALUE);

                    if (fit == 32) {
                        lastExecutionTime = System.currentTimeMillis() - startTime;
                        lastTotalGenerations = gen;
                        logArea.appendText(">>> SUCCESS: TARGET FOUND!\n");
                        new StatisticsWindow().display(gen, lastExecutionTime, 32);
                    } else if (gen >= 200) {
                        lastExecutionTime = System.currentTimeMillis() - startTime;
                        logArea.appendText(">>> SYSTEM ALERT: STUCK!\n");
                    }
                }));
                ga.run();
            }).start();

        } catch (NumberFormatException e) {
            showError("Input Error", "Invalid numeric values.");
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}