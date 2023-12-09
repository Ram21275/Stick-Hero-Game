package com.example.ap_project;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;

public class HelloApplication extends Application {
    MediaPlayer mediaPlayer;
//    Username u = Username.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 538, 800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}