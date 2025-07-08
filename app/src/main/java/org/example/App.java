package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Group group = new Group();
        TextArea textArea = new TextArea("David Text Editor");

        group.getChildren().add(textArea);
        Scene scene = new Scene(group, 1920, 1080);
        stage.setTitle("David Text Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(); /*Runs the program*/
    }
}
