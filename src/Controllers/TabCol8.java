package Controllers;

import Model.Tabelle8;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TabCol8 implements Initializable {
    @FXML
    public Button CreateTable;
    @FXML
    public Button Submit;
    @FXML
    public TableView<Tabelle8> AchtTabCol;
    @FXML
    public TableColumn<Tabelle8,String> TabCol1;
    @FXML
    public TableColumn<Tabelle8,String> TabCol2;
    @FXML
    public TableColumn<Tabelle8,String> TabCol3;
    @FXML
    public TableColumn<Tabelle8,String> TabCol4;
    @FXML
    public TableColumn<Tabelle8,String> TabCol5;
    @FXML
    public TableColumn<Tabelle8,String> TabCol6;
    @FXML
    public TableColumn<Tabelle8,String> TabCol7;
    @FXML
    public TableColumn<Tabelle8,String> TabCol8;
    @FXML
    public Label TableName;
    @FXML
    public TextField ValueEins;
    @FXML
    public TextField ValueZwei;
    @FXML
    public TextField ValueDrei;
    @FXML
    public TextField ValueVier;
    @FXML
    public TextField ValueFuenf;
    @FXML
    public TextField ValueSechs;
    @FXML
    public Button add;
    @FXML
    public TextField ValueSieben;
    @FXML
    public TextField ValueAcht;
    public Button save;
    public Button set;
    public TextField HeaderName1;
    public TextField HeaderName2;
    public TextField HeaderName3;
    public TextField HeaderName4;
    public TextField HeaderName5;
    public TextField HeaderName6;
    public TextField HeaderName7;
    public TextField HeaderName8;
    Stage stage1;
    Stage stage2;
    String column;
    String y;
    ObservableList<Tabelle8> data_Tab8 = FXCollections.observableArrayList();
    public void CreateTableButtonOnClick(ActionEvent actionEvent) throws IOException {
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
        stage2 =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene1= new Scene(root1);
        stage2.setScene(scene1);
        stage2.show();
    }

    public void AddButtonOnClick(ActionEvent actionEvent) {
        data_Tab8.add(new Tabelle8(ValueEins.getText(),
                ValueZwei.getText(),
                ValueDrei.getText(),
                ValueVier.getText(),
                ValueFuenf.getText(),
                ValueSechs.getText(),
                ValueSieben.getText(),
                ValueAcht.getText()));
        System.out.println(ValueEins.getText()+ " " +ValueZwei.getText()+" "+ValueDrei.getText()+" "+ValueVier.getText()+""+ValueFuenf.getText()+""+ValueSechs.getText()+""+ValueSieben.getText()+""+ValueAcht.getText());
        AchtTabCol.setItems(data_Tab8);
        ValueEins.clear();
        ValueZwei.clear();
        ValueDrei.clear();
        ValueVier.clear();
        ValueFuenf.clear();
        ValueSechs.clear();
        ValueSieben.clear();
        ValueAcht.clear();
    }

    public void MyFunction8(String text , String i ){
        TableName.setText(text);
        y=i;
        column=TableName.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniTable();
        loadData();
    }

    private void loadData() {
        AchtTabCol.setItems(data_Tab8);
    }

    private void iniTable() {
        TabCol1.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header1")));
        TabCol2.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header2")));
        TabCol3.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header3")));
        TabCol4.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header4")));
        TabCol5.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header5")));
        TabCol6.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header6")));
        TabCol7.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header7")));
        TabCol8.setCellValueFactory((new PropertyValueFactory<Tabelle8,String>("Header8")));
        EditTableCols();
    }

    private void EditTableCols() {
        TabCol1.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header1"));
        TabCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol2.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header2"));
        TabCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol2.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader2(event.getNewValue());
        });
        TabCol3.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header3"));
        TabCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol3.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader3(event.getNewValue());
        });
        TabCol4.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header4"));
        TabCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol4.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader4(event.getNewValue());
        });
        TabCol5.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header5"));
        TabCol5.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol5.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol6.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header6"));
        TabCol6.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol6.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol7.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header7"));
        TabCol7.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol7.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol8.setCellValueFactory(new PropertyValueFactory<Tabelle8,String>("Header8"));
        TabCol8.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol8.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        AchtTabCol.setEditable(true);
    }

    public void SaveButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol1.getText()+" "+" varchar (250),"+ TabCol2.getText()+" "+"varchar (250),"+ TabCol3.getText()+" "+" varchar (250)," + TabCol4.getText()+" "+" varchar (250),"+ TabCol5.getText()+" "+" varchar (250),"+ TabCol6.getText()+" "+" varchar (250),"+ TabCol7.getText()+" "+" varchar (250),"+ TabCol8.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        for(Tabelle8 o: data_Tab8){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol1.getText()+","+TabCol2.getText()+","+TabCol3.getText()+","+TabCol4.getText()+","+TabCol5.getText()+","+TabCol6.getText()+","+TabCol7.getText()+","+TabCol8.getText()+")"+"VALUES ("+ "'"+o.getHeader1()+"'"+","+"'"+o.getHeader2()+"'"+","+"'"+o.getHeader3()+"'"+","+"'"+o.getHeader4()+"'"+","+"'"+o.getHeader5()+"'"+","+"'"+o.getHeader6()+"'"+","+"'"+o.getHeader7()+"'"+","+"'"+o.getHeader8()+"'"+")");
            preparedStatement1.executeUpdate();
        }
    }

    public void SetButtonOnClick(ActionEvent actionEvent) {
        TabCol1.setText(HeaderName1.getText());
        TabCol2.setText(HeaderName2.getText());
        TabCol3.setText(HeaderName3.getText());
        TabCol4.setText(HeaderName4.getText());
        TabCol5.setText(HeaderName5.getText());
        TabCol6.setText(HeaderName6.getText());
        TabCol7.setText(HeaderName7.getText());
        TabCol8.setText(HeaderName8.getText());
        HeaderName1.clear();
        HeaderName2.clear();
        HeaderName3.clear();
        HeaderName4.clear();
        HeaderName5.clear();
        HeaderName6.clear();
        HeaderName7.clear();
        HeaderName8.clear();
    }
}
