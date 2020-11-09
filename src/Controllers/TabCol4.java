package Controllers;

import Model.Tabelle4;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    public Button Submit;
    public Button CreateTable;
    public Button set;
    public TextField HeaderName1;
    public TextField HeaderName2;
    public TextField HeaderName3;
    public TextField HeaderName4;
    String y;
    Stage stage1;
    Stage stage2;
    String column;
    ObservableList<Tabelle4> data_Tab4 = FXCollections.observableArrayList();
    public void AddButtonOnClick(ActionEvent actionEvent) {
        data_Tab4.add(new Tabelle4(ValueEins.getText(),ValueZwei.getText(),ValueDrei.getText(),ValueVier.getText()));
        System.out.println(ValueEins.getText()+ " " +ValueZwei.getText()+" "+ValueDrei.getText()+" "+ValueVier.getText());
        VierTabCol.setItems(data_Tab4);
        ValueEins.clear();
        ValueZwei.clear();
        ValueDrei.clear();
        ValueVier.clear();
    }

    public void SubmitButtonOnClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Views/QuestionChoice.fxml"));
        loader.load();
        AnchorPane root1 = loader.getRoot();
        QuestionChoice test = loader.getController();
        stage2 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene1= new Scene(root1);
        stage2.setScene(scene1);
        stage2.show();
    }

    public void CreateTableButtonOnClick(ActionEvent actionEvent) throws IOException {
        stage1 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        AnchorPane root1= FXMLLoader.load(getClass().getResource("../Views/TableSetting.fxml"));
        Scene scene1= new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

    public void MyFunction4(String text , String i ){
        TableName.setText(text);
        y=i;
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
        TabCol1.setCellValueFactory(new PropertyValueFactory<Tabelle4,String>("Header1"));
        TabCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol2.setCellValueFactory(new PropertyValueFactory<Tabelle4,String>("Header2"));
        TabCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol2.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader2(event.getNewValue());
        });
        TabCol3.setCellValueFactory(new PropertyValueFactory<Tabelle4,String>("Header3"));
        TabCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol3.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader3(event.getNewValue());
        });
        TabCol4.setCellValueFactory(new PropertyValueFactory<Tabelle4,String>("Header4"));
        TabCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol4.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader4(event.getNewValue());
        });
        VierTabCol.setEditable(true);
    }

    public void SetButtonOnClick(ActionEvent actionEvent) {
        TabCol1.setText(HeaderName1.getText());
        TabCol2.setText(HeaderName2.getText());
        TabCol3.setText(HeaderName3.getText());
        TabCol4.setText(HeaderName4.getText());
        HeaderName1.clear();
        HeaderName2.clear();
        HeaderName3.clear();
        HeaderName4.clear();
    }

    public void SaveButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol1.getText()+" "+" varchar (250),"+ TabCol2.getText()+" "+"varchar (250),"+ TabCol3.getText()+" "+" varchar (250)," + TabCol4.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        for(Tabelle4 o: data_Tab4){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol1.getText()+","+TabCol2.getText()+","+TabCol3.getText()+","+TabCol4.getText()+")"+"VALUES ("+ "'"+o.getHeader1()+"'"+","+"'"+o.getHeader2()+"'"+","+"'"+o.getHeader3()+"'"+","+"'"+o.getHeader4()+"'"+")");
            preparedStatement1.executeUpdate();
        }
    }
}
