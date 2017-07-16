package com.ubs.opsit.interviews;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClockStarter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/berlin-clock.fxml"));
        primaryStage.setTitle("Berlin Clock");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
