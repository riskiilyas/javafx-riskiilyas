package com.example.demojavafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/*
    Created by Riski Ilyas
    NRP 5025211189
    26 - 10 - 2022
 */

public class MainApplication extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private int score = 0;
    private Clip clip;
    File file = new File("assets/apple.wav");

    @Override
    public void start(Stage stage) {
        //
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ignored) {}

        //creating a Group object
        Group group = new Group();

        Text counter = new Text("Score : " + score);
        counter.setLayoutX(WIDTH/2.0 -20);
        counter.setLayoutY(20);
        group.getChildren().add(counter);

        Button clickBtn = new Button("Click Me");
        setRandomButton(clickBtn);
        clickBtn.setOnMouseClicked((mouseEvent -> {
            setRandomButton(clickBtn);
            updateScore(counter);
        }));

        group.getChildren().add(clickBtn);

        //Creating a Scene by passing the group object, height and width
        Scene scene = new Scene(group ,600, 600);

        //setting color to the scene
        scene.setFill(Color.CYAN);

        //Setting the title to Stage.
        stage.setTitle("Click Me!");

        //Adding the scene to Stage
        stage.setScene(scene);
        stage.setResizable(false);

        //Displaying the contents of the stage
        stage.show();
    }

    private void updateScore(Text counter) {
        score++;
        counter.setText("Score : " + score);

        clip.setMicrosecondPosition(0);
        clip.start();
    }

    private void setRandomButton(Button btn) {
        btn.setLayoutX(Math.abs(Math.random()*WIDTH-50));
        btn.setLayoutY(Math.abs(Math.random()*HEIGHT-20));
    }

    public static void main(String[] args) {
        launch();
    }
}