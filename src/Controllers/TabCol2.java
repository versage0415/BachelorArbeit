package Controllers;

import Model.Tabelle2;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TabCol2 implements Initializable {
    public TableView <Tabelle2>ZweiTabCol;
    public Label TableName;
    public TableColumn <Tabelle2,String> TabCol1;
    public TableColumn <Tabelle2,String> TabCol2;
    public TextField ValueEins;
    public TextField ValueZwei;
    public Button add;

    public Button set;
    public Button save;
    public TextField HeaderName1;
    public TextField HeaderName2;

    String column;
    ObservableList<Tabelle2> data_Tab2 = FXCollections.observableArrayList();



    public void MyFunc(String text ){
        TableName.setText(text);
        column=TableName.getText();
    }

    public void AddButtonOnClick() {
        data_Tab2.add(new Tabelle2(ValueEins.getText(),ValueZwei.getText()));
        System.out.println(ValueEins.getText()+ " " +ValueZwei.getText());
        ZweiTabCol.setItems(data_Tab2);
        ValueEins.clear();
        ValueZwei.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniTable();
        loadData();
    }

    private void loadData() {
        ZweiTabCol.setItems(data_Tab2);
    }

    private void iniTable() {
        TabCol1.setCellValueFactory((new PropertyValueFactory<Tabelle2,String>("Header1")));
        TabCol2.setCellValueFactory((new PropertyValueFactory<Tabelle2,String>("Header2")));
        EditTableCols();
    }

    private void EditTableCols() {
        TabCol1.setCellValueFactory(new PropertyValueFactory<Tabelle2,String>("Header1"));
        TabCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol2.setCellValueFactory(new PropertyValueFactory<Tabelle2,String>("Header2"));

        TabCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol2.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader2(event.getNewValue());
        });
        ZweiTabCol.setEditable(true);
    }



    public void SetButtonOnClick() {
        TabCol1.setText(HeaderName1.getText());
        TabCol2.setText(HeaderName2.getText());
        HeaderName1.clear();
        HeaderName2.clear();
    }

    public void SaveButtonOnClick() throws ClassNotFoundException{
       try {
           ConnectionClass connectionClass=new ConnectionClass();
           Connection connection=connectionClass.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE" + " " + column + "(" + TabCol1.getText() + " " + " varchar (250)," + TabCol2.getText() + " " + " varchar (250))");
           preparedStatement.executeUpdate();
           for (Tabelle2 o : data_Tab2) {
               PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO" + " " + column + "(" + TabCol1.getText() + "," + TabCol2.getText() + ")" + "VALUES (" + "'" + o.getHeader1() + "'" + "," + "'" + o.getHeader2() + "'" + ")");
               preparedStatement1.executeUpdate();
           }
           preparedStatement.close();
           connection.close();
           Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Information Dialog");
           alert.setContentText("Save Successfully");
           alert.show();
       } catch (SQLException error1){
           Alert alert1=new Alert(Alert.AlertType.ERROR);
           alert1.setTitle("SQL Syntax Error");
           alert1.setContentText("There is some syntax Error in your input.Maybe there is some token in your input or you use some Word as Table name that the Database already use ");
           alert1.show();
       }
    }
}
