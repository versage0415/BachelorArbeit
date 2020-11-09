package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller  implements Initializable {
    public ImageView ImageArea;
    Stage stage;
    public Button TableSetting;


        //scene.getStylesheets().add("TableStyle.css");

    public void HandleButtonOnClick(ActionEvent event) throws IOException {
        stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        AnchorPane root= FXMLLoader.load(getClass().getResource("../Views/TableSetting.fxml"));
        Scene scene= new Scene(root);

        scene.getStylesheets().add("TableStyle.css");

    stage.setScene(scene);
    stage.show();


    }
    void loadImage() throws FileNotFoundException {

        Image image=new Image("/Views/StartApp1.png");
        ImageArea.setImage(image);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            loadImage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
