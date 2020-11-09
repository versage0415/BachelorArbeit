package Controllers;

import Model.QuestionClass;
import Model.Tabelle1;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuestionChoice implements Initializable {
    public Button submit3;
    public Button back;
    @FXML
    public CheckBox selection;
    @FXML
    public CheckBox projection;
    @FXML
    public CheckBox union;
    @FXML
    public CheckBox intersection;
    @FXML
    public CheckBox differenz;
    @FXML
    public CheckBox division;
    @FXML
    public CheckBox Kproduct;
    @FXML
    public CheckBox NJoin;
    @FXML
    public CheckBox LOJ;
    @FXML
    public CheckBox ROJ;
    Stage stage;
    public ObservableList<String> TableName = FXCollections.observableArrayList();
    public ObservableList<String> SelectionQ = FXCollections.observableArrayList();
    List<String> box=new ArrayList<>();
    List<String> result =new ArrayList<>();
    public ObservableList<QuestionClass> ProjectionQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> UnionQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> IntersectionQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> DifferenzQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> DivisionQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> KproductQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> NjoinQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> LoJQ = FXCollections.observableArrayList();
    public ObservableList<QuestionClass> RoJQ = FXCollections.observableArrayList();


 public String getOperator(){
     RandomClass obj=new RandomClass();
     if(selection.isSelected()){

         box.add("1");
     }
     if(projection.isSelected()){

         box.add("2");
     }
     if(union.isSelected()){

         box.add("3");
     }
     if(intersection.isSelected()){

         box.add("4");
     }
     if(differenz.isSelected()){

         box.add("5");
     }
     if(division.isSelected()){

         box.add("6");
     }
     if(Kproduct.isSelected()){

         box.add("7");
     }
     if(NJoin.isSelected()){

         box.add("8");
     }
     if(LOJ.isSelected()){

         box.add("9");
     }
     if(ROJ.isSelected()){

         box.add("10");
     }

     if(box.size()==1){
         return box.get(0);
     }
     if(box.size()!=0 &&box.size()>1){
         return obj.getRandomElement(box);
     }
     if(box.size()==0){
         return null;
     }
     return null;
 }
 public List<String> getQuetion(String Opeator){
     QuestionClass question=new QuestionClass();
    // result=question.getQuetionList();
     return result;
 }




    public void HandleButtonOnClick(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        if(actionEvent.getSource()==submit3){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/TablesAndQuestion.fxml"));
            loader.load();
            AnchorPane root1 = loader.getRoot();
            TablesAndQuestion test = loader.getController();
            String op=getOperator();
            test.MyQuestion(op);


                    //Projection,Union,Intersection,Differenz,Division,Kproduct,Njoin,LoJ,RoJ,box10);
            Scene scene1= new Scene(root1);
            stage.setScene(scene1);
            stage.show();
        }
        AnchorPane root2= FXMLLoader.load(getClass().getResource("../Views/TableSetting.fxml"));
        Scene scene2= new Scene(root2);
        if(actionEvent.getSource()==back){
            stage.setScene(scene2);
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
