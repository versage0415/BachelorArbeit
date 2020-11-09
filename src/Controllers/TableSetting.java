package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableSetting implements Initializable {
    @FXML
    public ComboBox <String>Combobox1;
@FXML
    public TextField textfield;
    @FXML
    public Button back;
    @FXML
   // public Button submit1;
    public Button CreateTable;
    public Label message1;
    public Label message2;
    String column;
  String testable ;
    String Value;
    Stage stage1;
    Stage stage2;
    Stage stage3;
    Stage stage4;
    Stage stage5;
    Stage stage6;
    Stage stage7;
    Stage stage8;
    Stage stage9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message1.setVisible(false);
        message2.setVisible(false);

        Combobox1.setEditable(true);
        ObservableList<String> list =  FXCollections.observableArrayList("1","2","3","4","5","6","7","8");
        Combobox1.setItems(list);

        textfield.setText(null);
        System.out.println("The value of textfied is:"+ textfield.getText());
        //   submit1.setOnAction(e ->PrintChoiceNumber() );
        //   submit1.setOnAction(e -> System.out.println("The User selected:"+Combobox1.getValue()) );

    }

    void TestValue(){
        Value = textfield.getText();
        testable =  Combobox1.getSelectionModel().getSelectedItem();
    }



    public void CreateButtonOnClick(ActionEvent actionEvent)  {
        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="1"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);


            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Views/EinTabColumn.fxml"));

                loader.load();
                AnchorPane root1 = loader.getRoot();
                EinTabColumn test = loader.getController();
                test.MyFunction(Value,testable);
                stage1 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene1 = new Scene(root1);
                stage1.setScene(scene1);
                stage1.show();

            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="2"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader1 = new FXMLLoader();
                loader1.setLocation(getClass().getResource("/Views/TabCol2.fxml"));
                loader1.load();
                AnchorPane root2 = loader1.getRoot();

                TabCol2 test1 = loader1.getController();

                test1.MyFunction1(Value,testable);
                stage3 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene3 = new Scene(root2);
                stage3.setScene(scene3);
                stage3.show();

            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="3"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader2 = new FXMLLoader();
                loader2.setLocation(getClass().getResource("/Views/TabCol3.fxml"));
                loader2.load();
                AnchorPane root3 = loader2.getRoot();

                TabCol3 test2 = loader2.getController();

                test2.MyFunction3(Value,testable);
                stage4 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene4 = new Scene(root3);
                stage4.setScene(scene4);
                stage4.show();

            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="4"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader3 = new FXMLLoader();
                loader3.setLocation(getClass().getResource("/Views/TabCol4.fxml"));
                loader3.load();
                AnchorPane root4 = loader3.getRoot();
                TabCol4 test3 = loader3.getController();
                test3.MyFunction4(Value,testable);
                stage5 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene5 = new Scene(root4);
                stage5.setScene(scene5);
                stage5.show();
            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="5"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader5 = new FXMLLoader();
                loader5.setLocation(getClass().getResource("/Views/TabCol5.fxml"));
                loader5.load();
                AnchorPane root5 = loader5.getRoot();
                TabCol5 test4 = loader5.getController();
                test4.MyFunction5(Value,testable);
                stage6 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene6 = new Scene(root5);
                stage6.setScene(scene6);
                stage6.show();
            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="6"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader6 = new FXMLLoader();
                loader6.setLocation(getClass().getResource("/Views/TabCol6.fxml"));
                loader6.load();
                AnchorPane root6 = loader6.getRoot();
                TabCol6 test6 = loader6.getController();
                test6.MyFunction6(Value,testable);
                stage7 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene7 = new Scene(root6);
                stage7.setScene(scene7);
                stage7.show();
            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="7"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader7 = new FXMLLoader();
                loader7.setLocation(getClass().getResource("/Views/TabCol7.fxml"));
                loader7.load();
                AnchorPane root7 = loader7.getRoot();
                TabCol7 test7 = loader7.getController();
                test7.MyFunction7(Value,testable);
                stage8 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene8 = new Scene(root7);
                stage8.setScene(scene8);
                stage8.show();
            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}

        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()=="8"){
            if(textfield.getText()!=null){
                message1.setVisible(false);
                message2.setVisible(false);
            TestValue();
            System.out.println("You click Submit");
            System.out.println(Value);
            System.out.println(testable);
            System.out.println(column);
            try{
                FXMLLoader loader8 = new FXMLLoader();
                loader8.setLocation(getClass().getResource("/Views/TabCol8.fxml"));
                loader8.load();
                AnchorPane root8 = loader8.getRoot();
                TabCol8 test8 = loader8.getController();
                test8.MyFunction8(Value,testable);
                stage9 = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene9 = new Scene(root8);
                stage9.setScene(scene9);
                stage9.show();
            }catch (IOException ex){
                Logger.getLogger(EinTabColumn.class.getName()).log(Level.SEVERE, null,ex);
            }
        }}
        // hier ist to prevent the user what is going on
        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()==null){
            message2.setVisible(true);
        }
        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()!="1"&& Combobox1.getValue()!="2"&& Combobox1.getValue()!="3"&& Combobox1.getValue()!="4"&& Combobox1.getValue()!="5"&& Combobox1.getValue()!="6"&& Combobox1.getValue()!="7"&& Combobox1.getValue()!="8"){
            message2.setVisible(true);
        }
        if(actionEvent.getSource()==CreateTable && Combobox1.getValue()==null&&textfield.getText()!=null){
            message1.setVisible(false);

        }


        if(textfield.getText()==null){
            message1.setVisible(true);
        }
    }

    public void BackButtonOnClick(ActionEvent actionEvent) throws IOException {
        System.out.println("You click Back");
        stage2 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        AnchorPane root2= FXMLLoader.load(getClass().getResource("../Views/StartApp.fxml"));
        Scene scene2= new Scene(root2);
        stage2.setScene(scene2);
        stage2.show();
    }









}
