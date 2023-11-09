package com.example.assignment3_mds569;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class EditorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /**
         * This is the main application class.
         */

        // root stores the MainUI();
        MainUI root = new MainUI();

        Scene scene = new Scene(root);
        stage.setTitle("CMPT 381 Assignment 3: MVC - mds569");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}
