package Controllers;

import Model.Tabelle3;
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

public class TabCol3 implements Initializable {
    public TextField ValueEins;
    public TextField ValueZwei;
    public TextField ValueDrei;
    public TableColumn<Tabelle3,String> TabCol1;
    public TableColumn <Tabelle3,String> TabCol2;
    public TableColumn <Tabelle3,String> TabCol3;
    public TableView <Tabelle3>DreiTabCol;
    public Button add;

    public Label TableName;
    public Button set;
    public TextField HeaderName1;
    public TextField HeaderName2;
    public TextField HeaderName3;
    public Button save;
String column;

    ObservableList<Tabelle3> data_Tab3 = FXCollections.observableArrayList();

    public void AddButtonOnClick() {
        data_Tab3.add(new Tabelle3(ValueEins.getText(),ValueZwei.getText(),ValueDrei.getText()));
        System.out.println(ValueEins.getText()+ " " +ValueZwei.getText()+" "+ValueDrei.getText());
        DreiTabCol.setItems(data_Tab3);
        ValueEins.clear();
        ValueZwei.clear();
        ValueDrei.clear();
    }





    public void MyFunc(String text ){
        TableName.setText(text);
        column=TableName.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniTable();
        loadData();
    }

    private void loadData() {
        DreiTabCol.setItems(data_Tab3);
    }

    private void iniTable() {
        TabCol1.setCellValueFactory((new PropertyValueFactory<Tabelle3,String>("Header1")));
        TabCol2.setCellValueFactory((new PropertyValueFactory<Tabelle3,String>("Header2")));
        TabCol3.setCellValueFactory((new PropertyValueFactory<Tabelle3,String>("Header3")));
        EditTableCols();
    }

    private void EditTableCols() {

        TabCol1.setCellValueFactory(new PropertyValueFactory<>("Header1"));
        TabCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol2.setCellValueFactory(new PropertyValueFactory<>("Header2"));
        TabCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol2.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader2(event.getNewValue());
        });
        TabCol3.setCellValueFactory(new PropertyValueFactory<>("Header3"));
        TabCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol3.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader3(event.getNewValue());
        });
        DreiTabCol.setEditable(true);
    }

    public void SetButtonOnClick() {
        TabCol1.setText(HeaderName1.getText());
        TabCol2.setText(HeaderName2.getText());
        TabCol3.setText(HeaderName3.getText());
        HeaderName1.clear();
        HeaderName2.clear();
        HeaderName3.clear();
    }

    public void SaveButtonOnClick() throws ClassNotFoundException {
        try {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol1.getText()+" "+" varchar (250),"+ TabCol2.getText()+" "+"varchar (250),"+ TabCol3.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        for(Tabelle3 o: data_Tab3){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol1.getText()+","+TabCol2.getText()+","+TabCol3.getText()+")"+"VALUES ("+ "'"+o.getHeader1()+"'"+","+"'"+o.getHeader2()+"'"+","+"'"+o.getHeader3()+"'"+")");
            preparedStatement1.executeUpdate();
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
}
