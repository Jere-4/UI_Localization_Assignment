package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/sample/layout.fxml")
        );

        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Fuel Cost Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
