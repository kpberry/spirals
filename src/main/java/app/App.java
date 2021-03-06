package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 * Interface for drawing math spirals.
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Class c = getClass();
        loader.setLocation(c.getResource("/app/App.fxml"));
        Parent root = loader.load();

        // Show the scene

        primaryStage.setTitle("Spirals");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(true);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
