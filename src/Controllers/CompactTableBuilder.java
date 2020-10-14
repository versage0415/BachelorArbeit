package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompactTableBuilder implements Initializable {
    public TextField textfield;
    public ComboBox<String> combo;
    public AnchorPane anchor;
    Stage stage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo.setEditable(true);
        ObservableList<String> list =  FXCollections.observableArrayList("1","2","3","4","5","6","7","8");
        combo.setItems(list);

    }

    public void ComboEvent(ActionEvent actionEvent) throws IOException {
        stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        SelectionModel<String> sm= combo.getSelectionModel();
        if(sm.getSelectedItem().equals("1")){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/EinTabColumn.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            EinTabColumn test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("2")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol2.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol2 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("3")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol3.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol3 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("4")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol4.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol4 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("5")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol5.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol5 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("6")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol6.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol6 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("7")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol7.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol7 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
        if(sm.getSelectedItem().equals("8")){
            anchor.getChildren().clear();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TabCol8.fxml"));
            loader.load();
            AnchorPane root = loader.getRoot();
            TabCol8 test = loader.getController();
            test.MyFunc(textfield.getText());
            anchor.getChildren().add(root);
        }
    }
}
