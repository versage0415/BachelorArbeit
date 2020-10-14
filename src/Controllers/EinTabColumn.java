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
    public TableColumn<Tabelle1, String> TabCol;
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

    public ObservableList<Tabelle1> data = FXCollections.observableArrayList();
String column;

    public void MyFunc(String text ){
        NameTable.setText(text);
        column=NameTable.getText();
    }



    public void SaveButtonOnClick(ActionEvent actionEvent) throws ClassNotFoundException {
        try {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        System.out.println("the table:"+column+" "+"is created");
        for(Tabelle1 o: data){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol.getText()+")"+"VALUES ("+ "'"+o.getHeader()+"'"+")");
            preparedStatement1.executeUpdate();
            System.out.println("the value:"+o.getHeader()+" "+"is insered");
        }
        preparedStatement.close();
        connection.close();
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Save Successfully");
            alert.show();
        }catch (SQLException error1){
            Alert alert1=new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("SQL Syntax Error");
            alert1.setContentText("There is some syntax Error in your input.Maybe there is some token in your input or you use some Word as Table name that the Database already use");
            alert1.show();
        }

    }
    public void loadData(){
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

    public void SetButtonOnClick(ActionEvent actionEvent){
        TabCol.setText(HeaderName.getText());
        HeaderName.clear();
        HeaderName.setVisible(true);
    }
}
