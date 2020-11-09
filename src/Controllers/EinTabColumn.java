package Controllers;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EinTabColumn implements Initializable {
    //public TableColumn TabCol;
    @FXML
   public TableView<Tabelle1> EinTabCol;
    @FXML
    public Button back;
   @FXML
    public TableColumn<Tabelle1, String> TabCol;
    @FXML
    public Button submit2;
@FXML
    public Label NameTable;
    @FXML
    public TextField AddValue;
    @FXML
    public Button Save;
    @FXML
    public Button addButton;
    @FXML
    public Button set;
    @FXML
    public TextField HeaderName;
    String  y;
    Stage stage1;
    Stage stage2;
    public ObservableList<Tabelle1> data = FXCollections.observableArrayList();
String column;
    //TablesAndQuestion test= new TablesAndQuestion();

    public void MyFunction(String text , String i ){
        NameTable.setText(text);
        column=NameTable.getText();
        y=i;
    }

    public void CreateNewTableButtonOnClick(ActionEvent actionEvent) throws IOException {
        stage1 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        AnchorPane root1= FXMLLoader.load(getClass().getResource("../Views/TableSetting.fxml"));
        Scene scene1= new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }
    public void SubmitButtonOnClick(ActionEvent actionEvent) throws IOException {


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Views/QuestionChoice.fxml"));
            loader.load();
            AnchorPane root1 = loader.getRoot();
            QuestionChoice test = loader.getController();
           // test.SendData1(NameTable.getText(),TabCol.getText());


        stage2 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        Scene scene1= new Scene(root1);
        stage2.setScene(scene1);
        stage2.show();
    }

    public void SaveButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        for(Tabelle1 o: data){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol.getText()+")"+"VALUES ("+ "'"+o.getHeader()+"'"+")");
            preparedStatement1.executeUpdate();
        }
    }



    public void loadData(){
//data.add(new Tabelle1("Versage"));
        EinTabCol.setItems(data);
    }

    public void AddButtonOnClick(ActionEvent actionEvent) throws  ClassNotFoundException {
                data.add(new Tabelle1(AddValue.getText()));
                System.out.println(AddValue.getText());
                EinTabCol.setItems(data);
                AddValue.clear();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
iniTable();
loadData();
    }

    public void iniTable(){
        TabCol.setCellValueFactory((new PropertyValueFactory<Tabelle1,String>("Header")));
        EditTableCols();
    }
    public  void EditTableCols(){
        TabCol.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"));
        TabCol.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader(event.getNewValue());
        });
        EinTabCol.setEditable(true);
    }

    public void SetButtonOnClick(ActionEvent actionEvent) {
        TabCol.setText(HeaderName.getText());
        HeaderName.clear();
        HeaderName.setVisible(true);
    }
}
