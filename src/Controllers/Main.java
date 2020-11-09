package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("../Views/StartApp.fxml"));
        primaryStage.setTitle("MCQ Generator");
        Image image=new Image("/Views/Icon.png");
        primaryStage.getIcons().add(image);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }



}
