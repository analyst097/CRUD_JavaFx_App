package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        AnchorPane rootNode = loader.load();
        primaryStage.setTitle("CRUD Javafx Application");
        primaryStage.setScene(new Scene(rootNode));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
