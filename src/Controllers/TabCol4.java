package Controllers;

import Model.Tabelle4;
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

public class TabCol4 implements Initializable {
    public Button add;
    public TextField ValueVier;
    public TextField ValueDrei;
    public TextField ValueZwei;
    public TextField ValueEins;
    public TableColumn<Tabelle4,String> TabCol4;
    public TableColumn<Tabelle4,String> TabCol3;
    public TableColumn<Tabelle4,String> TabCol2;
    public TableColumn<Tabelle4,String> TabCol1;
    public TableView<Tabelle4> VierTabCol;
    public Label TableName;
    public Button set;
    public TextField HeaderName1;
    public TextField HeaderName2;
    public TextField HeaderName3;
    public TextField HeaderName4;

    String column;
    ObservableList<Tabelle4> data_Tab4 = FXCollections.observableArrayList();
    public void AddButtonOnClick() {
        data_Tab4.add(new Tabelle4(ValueEins.getText(),ValueZwei.getText(),ValueDrei.getText(),ValueVier.getText()));
        System.out.println(ValueEins.getText()+ " " +ValueZwei.getText()+" "+ValueDrei.getText()+" "+ValueVier.getText());
        VierTabCol.setItems(data_Tab4);
        ValueEins.clear();
        ValueZwei.clear();
        ValueDrei.clear();
        ValueVier.clear();
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

    private void iniTable() {
            TabCol1.setCellValueFactory((new PropertyValueFactory<Tabelle4,String>("Header1")));
            TabCol2.setCellValueFactory((new PropertyValueFactory<Tabelle4,String>("Header2")));
            TabCol3.setCellValueFactory((new PropertyValueFactory<Tabelle4,String>("Header3")));
             TabCol4.setCellValueFactory((new PropertyValueFactory<Tabelle4,String>("Header4")));
            EditTableCols();

    }

    private void loadData() {
        VierTabCol.setItems(data_Tab4);
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
        TabCol4.setCellValueFactory(new PropertyValueFactory<>("Header4"));
        TabCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol4.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader4(event.getNewValue());
        });
        VierTabCol.setEditable(true);
    }

    public void SetButtonOnClick() {
        TabCol1.setText(HeaderName1.getText());
        TabCol2.setText(HeaderName2.getText());
        TabCol3.setText(HeaderName3.getText());
        TabCol4.setText(HeaderName4.getText());
        HeaderName1.clear();
        HeaderName2.clear();
        HeaderName3.clear();
        HeaderName4.clear();
    }

    public void SaveButtonOnClick() throws ClassNotFoundException {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE" + " " + column + "(" + TabCol1.getText() + " " + " varchar (250)," + TabCol2.getText() + " " + "varchar (250)," + TabCol3.getText() + " " + " varchar (250)," + TabCol4.getText() + " " + " varchar (250))");
            preparedStatement.executeUpdate();
            for (Tabelle4 o : data_Tab4) {
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO" + " " + column + "(" + TabCol1.getText() + "," + TabCol2.getText() + "," + TabCol3.getText() + "," + TabCol4.getText() + ")" + "VALUES (" + "'" + o.getHeader1() + "'" + "," + "'" + o.getHeader2() + "'" + "," + "'" + o.getHeader3() + "'" + "," + "'" + o.getHeader4() + "'" + ")");
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
