package com.example.ap_project;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloController {
    int timer = 0;
    static Parent root;
    static Scene scene;
    Stage stage;
    private Rectangle r;
    private final Controller controller = new Controller();
    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    int stickHeight = 0;
    int stickWidth = 4;

    private boolean GameOver = false;
    private static boolean isRotated = false;

    private boolean flag = false;

    private ImageView im;
    private Controller c = new Controller();
    AnimationTimer gameLoop;

    HelloController(Parent root, Scene scene){
        this.root=root;
        this.scene = scene;
    }

    public void generate_rectangle(){
        double x = 0;
        double y = 500;
        int height = 300;
        System.out.println(4);
        int width = 150;

        Rectangle r1 = new Rectangle(x, y,width, height);
        r1.setFill(Color.BLACK);
        r1.setStroke(Color.BLACK);
        System.out.println(5);

        if (root instanceof javafx.scene.layout.Pane) {
            javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
            paneRoot.getChildren().add(r1);
        }
        rectangles.add(r1);

        for(int i = 1; i<90; i++){
            randomRect(rectangles.get(i-1));
        }

//        for(Rectangle rr: rectangles) System.out.println(rr);

    }

    public void randomRect(Rectangle r1){
        Random random = new Random();

        double randomWidth = random.nextDouble() * 100 + 71;
        double randomX = r1.getX() + r1.getWidth() + random.nextDouble() * 150 + 70;
        double tempX = randomX;

        Rectangle r2 = new Rectangle(tempX, r1.getY(),randomWidth, r1.getHeight());

        if (root instanceof javafx.scene.layout.Pane) {
            javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
            paneRoot.getChildren().add(r2);
        }

        rectangles.add(r2);
//        System.out.println(rectangles);
    }

    public void stick(double y, double x) throws IOException {
        stickHeight = 0;
        r = new Rectangle(x,y,stickWidth,stickHeight+5);
        r.setFill(Color.BLUE);
        if (root instanceof javafx.scene.layout.Pane) {
            javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
            paneRoot.getChildren().add(r);
        }
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.Z) {
                stickHeight = stickHeight+10;
                r.setHeight(stickHeight);
                r.setY(r.getY() - 10);
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.Z) {
                try {
                    rotate();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                isRotated = true;
            }
        });
    }

    public void rotate() throws IOException{
        ActionEvent e1 = new ActionEvent(im,scene);
        Rotate rotate = new Rotate();
        rotate.setAngle(90);
        rotate.setPivotX(r.getX());
        rotate.setPivotY(r.getY()+stickHeight);
        r.getTransforms().add(rotate);
        double a = rectangles.get(1).getX();
        System.out.println(a);
//        transition3.play();
        SequentialTransition st;
        if((stickHeight<(rectangles.get(1).getX()+rectangles.get(1).getWidth())&&(stickHeight>(rectangles.get(1).getX()-rectangles.get(0).getWidth())))){
            TranslateTransition transition2  = new TranslateTransition(Duration.seconds(0.5),im);
            transition2.setByX(rectangles.get(1).getX()-70);
            PauseTransition pt1 = new PauseTransition(Duration.seconds(0.5));
            st = new SequentialTransition(transition2,pt1);
            st.play();
            move();
        }
        else{
            TranslateTransition transition3 = new TranslateTransition(Duration.seconds(0.5), im);
            transition3.setToX(stickHeight);
            TranslateTransition transition  = new TranslateTransition(Duration.seconds(0.5),im);
            transition.setByY(900);
            PauseTransition pt1 = new PauseTransition(Duration.seconds(0.5));
            st = new SequentialTransition(transition3,pt1,transition);
            st.play();
            GameOver = true;
        }

        if(GameOver) {
            PauseTransition pt = new PauseTransition(Duration.seconds(2));
            pt.play();
            pt.setOnFinished((ActionEvent event)-> {
                try {
                    gameOver(e1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }
//        move();
    }
    public void player(){
        Image ch = new Image("file:src/main/resources/images/character.png");
        im = new ImageView(ch);
        if (root instanceof javafx.scene.layout.Pane) {
            javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
            paneRoot.getChildren().addAll(im);
        }
        double width = 71, height = 71;
        im.setFitHeight(height);
        im.setFitWidth(width);
        im.setY(500-height);
        im.setX(150-width-3);
    }

    public void movePlayer(){

    }

    public void move() {
        TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.3), rectangles.get(0));
        transition1.setToX(-rectangles.get(0).getWidth());

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.3), rectangles.get(1));
        transition2.setToX(-rectangles.get(1).getX());

        TranslateTransition transition4 = new TranslateTransition(Duration.seconds(0.3), rectangles.get(2));
        transition4.setToX(-rectangles.get(1).getX());

        TranslateTransition transition3 = new TranslateTransition(Duration.seconds(0.3), im);
        transition3.setToX(-r.getX()+75);

        SequentialTransition st = new SequentialTransition(transition1, new PauseTransition(Duration.seconds(0.5)), transition2, transition3, new PauseTransition(Duration.seconds(0.5)), transition4);
        st.play();

        Random random = new Random();

//        for(int j = 3; j<rectangles.size(); j++){
//            TranslateTransition transition7 = new TranslateTransition(Duration.seconds(0.3), rectangles.get(j));
//            transition7.setToX(-random.nextDouble() * 200 + 70);
//            transition7.play();
//        }

        System.out.println("\n"+rectangles);
        if(!GameOver) st.setOnFinished((ActionEvent event)->{
//            rectangles.get(1).setX(0);
            rectangles.remove(0);

//            for(int i=1;i<rectangles.size();i++){
//                rectangles.get(i).setX(rectangles.get(i).getX()-rectangles.get(0).getX());
//            }
            System.out.println(rectangles.get(0).getX());
            System.out.println(rectangles.size());
            if (root instanceof javafx.scene.layout.Pane) {
                javafx.scene.layout.Pane paneRoot = (javafx.scene.layout.Pane) root;
//                paneRoot.getChildren().remove(rectangles.get(0));
//                r.setHeight(0);
//                r.setX(rectangles.get(0).getWidth()-3);
                paneRoot.getChildren().remove(r);
                r = null;
            }
            System.out.println(rectangles.size());
            startGame();
    });

    }
    public void startGame(){
        timer += 1;
        if (timer == 1) {
            player();
            generate_rectangle();
        }
        try {
            stick(500, rectangles.get(0).getWidth()-3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void gameOver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Game-Over.fxml"));
        Parent root = loader.load();
        scene = new Scene(root,538, 800);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public int getTimer() {
        return timer;
    }

    public Parent getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Rectangle getR() {
        return r;
    }

    public Controller getController() {
        return controller;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public int getStickHeight() {
        return stickHeight;
    }

    public int getStickWidth() {
        return stickWidth;
    }

    public boolean isGameOver() {
        return GameOver;
    }

    public static boolean isIsRotated() {
        return isRotated;
    }

    public boolean isFlag() {
        return flag;
    }

    public ImageView getIm() {
        return im;
    }

    public Controller getC() {
        return c;
    }

    public AnimationTimer getGameLoop() {
        return gameLoop;
    }
}