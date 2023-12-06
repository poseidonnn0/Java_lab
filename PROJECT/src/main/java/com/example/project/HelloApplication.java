package com.example.project;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {

        Pane root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        Scene scene = new Scene(root, 654, 165);
        stage.setScene(scene);

        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent arg0) {

                Platform.exit();
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}