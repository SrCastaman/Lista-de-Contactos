package org.example;

import org.example.db.Database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Database.initialize();


        // Cargar el archivo FXML de la vista principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/view/contact_view.fxml"));
        Parent root = loader.load();

        // Configurar la escena
        Scene scene = new Scene(root);

        primaryStage.setTitle("Gestor de Contactos");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // opcional
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Lanza la aplicación JavaFX
    }
}
