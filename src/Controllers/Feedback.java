package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Feedback {
    public CheckBox RAnswer1;
    public CheckBox RAnswer2;
    public CheckBox RAnswer3;
    public CheckBox RAnswer4;
    public Button exit;
    public Label feedback;
    public Button back;
    Stage stage;

    public void HandleButtonOnClick(ActionEvent actionEvent) throws IOException {
        stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        AnchorPane root1= FXMLLoader.load(getClass().getResource("../Views/StartApp.fxml"));
        Scene scene1= new Scene(root1);
        if(actionEvent.getSource()==exit){
            stage.setScene(scene1);
            stage.show();
        }
        AnchorPane root2= FXMLLoader.load(getClass().getResource("../Views/TablesAndQuestion.fxml"));
        Scene scene2= new Scene(root2);
        if(actionEvent.getSource()==back){
            stage.setScene(scene2);
            stage.show();
        }
    }
}
