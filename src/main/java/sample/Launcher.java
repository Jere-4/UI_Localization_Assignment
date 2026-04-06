package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                Launcher.class.getResource("/fxml/main_view.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Fuel Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
