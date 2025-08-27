package org.example;

import org.example.db.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Database.initialize(); // inicializa SQLite

        stage.setScene(new Scene(new Label("ContactBook listo!"), 400, 200));
        stage.setTitle("ContactBook");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
