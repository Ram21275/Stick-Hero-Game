package com.example.ap_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    Scene scene;

    @FXML
            private TextField user;
    @FXML
            private Label label;

    Stage stage;

    MediaPlayer mediaPlayer;

//    private final Image image = character.getImage();

    public void setUsername() {
        String username = user.getText();
        System.out.println(username);
    }

    public void switchMainMenu(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("initial.fxml"));
        /*Username u = Username.getInstance();
        u.setName(user.getText());
        System.out.println(u.getName());*/
        Parent root = loader.load();
        scene = new Scene(root,538, 800);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchGamePlay(ActionEvent event) throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        scene = new Scene(root, 538, 800);
//        Image ch = new Image("file:src/main/resources/images/background.jpeg");
//        ImageView im = new ImageView(ch);
//        if (root instanceof javafx.scene.layout.Pane) {
//            javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
//            paneRoot.getChildren().addAll(im);
//        }
        HelloController h = new HelloController(root,scene);
        h.startGame();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchPause(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("pause.fxml"));
        Parent root = loader.load();
        scene = new Scene(root,538, 800);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void switchOver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Game-Over.fxml"));
        Parent root = loader.load();
        scene = new Scene(root,538, 800);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void gameSwitchOff(ActionEvent event){
        Platform.exit();
    }

    public void media2() throws IOException{
        String audioFilePath = "src/main/resources/images/audio.m4a";
        Media media = new Media(new File(audioFilePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void offMusic() throws IOException{
        mediaPlayer.stop();
    }
}
