package Controllers;

import Model.Tabelle6;
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

public class TabCol6 implements Initializable {
    public Button CreateTable;
    public Button Submit;
    public TableView<Tabelle6> SechsTabCol;
    public TableColumn<Tabelle6,String> TabCol1;
    public TableColumn <Tabelle6,String> TabCol2;
    public TableColumn <Tabelle6,String> TabCol3;
    public TableColumn<Tabelle6,String> TabCol4;
    public TableColumn<Tabelle6,String> TabCol5;
    public TableColumn<Tabelle6,String> TabCol6;
    public Label TableName;
    public TextField VakueEins;
    public TextField ValueZwei;
    public TextField ValueDrei;
    public TextField ValueVier;
    public TextField ValueFuenf;
    public TextField ValueSechs;
    public Button add;
    public Button save;
    public Button set;
    public TextField HeaderName1;
    public TextField HeaderName2;
    public TextField HeaderName3;
    public TextField HeaderName4;
    public TextField HeaderName5;
    public TextField HeaderName6;
    Stage stage1;
    Stage stage2;
    String column;
    String y;
    ObservableList<Tabelle6> data_Tab6 = FXCollections.observableArrayList();
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
        data_Tab6.add(new Tabelle6(VakueEins.getText(),ValueZwei.getText(),ValueDrei.getText(),ValueDrei.getText(),ValueFuenf.getText(),ValueSechs.getText()));
        System.out.println(VakueEins.getText()+ " " +ValueZwei.getText()+" "+ValueDrei.getText()+" "+ValueVier.getText()+""+ValueFuenf.getText());
        SechsTabCol.setItems(data_Tab6);
        VakueEins.clear();
        ValueZwei.clear();
        ValueDrei.clear();
        ValueVier.clear();
        ValueFuenf.clear();
        ValueSechs.clear();
    }
    public void MyFunction6(String text , String i ){
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
        SechsTabCol.setItems(data_Tab6);
    }

    private void iniTable() {
        TabCol1.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header1")));
        TabCol2.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header2")));
        TabCol3.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header3")));
        TabCol4.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header4")));
        TabCol5.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header5")));
        TabCol6.setCellValueFactory((new PropertyValueFactory<Tabelle6,String>("Header6")));
        EditTableCols();
    }

    private void EditTableCols() {
        TabCol1.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header1"));
        TabCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol1.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol2.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header2"));
        TabCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol2.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader2(event.getNewValue());
        });
        TabCol3.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header3"));
        TabCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol3.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader3(event.getNewValue());
        });
        TabCol4.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header4"));
        TabCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol4.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader4(event.getNewValue());
        });
        TabCol5.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header5"));
        TabCol5.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol5.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        TabCol6.setCellValueFactory(new PropertyValueFactory<Tabelle6,String>("Header6"));
        TabCol6.setCellFactory(TextFieldTableCell.forTableColumn());
        TabCol6.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setHeader1(event.getNewValue());
        });
        SechsTabCol.setEditable(true);
    }

    public void SaveButtonOnClick(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE"+" "+column+"("+ TabCol1.getText()+" "+" varchar (250),"+ TabCol2.getText()+" "+"varchar (250),"+ TabCol3.getText()+" "+" varchar (250)," + TabCol4.getText()+" "+" varchar (250),"+ TabCol5.getText()+" "+" varchar (250),"+ TabCol6.getText()+" "+" varchar (250))" );
        preparedStatement.executeUpdate();
        for(Tabelle6 o: data_Tab6){
            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO"+ " "+column +"("+TabCol1.getText()+","+TabCol2.getText()+","+TabCol3.getText()+","+TabCol4.getText()+","+TabCol5.getText()+","+TabCol6.getText()+")"+"VALUES ("+ "'"+o.getHeader1()+"'"+","+"'"+o.getHeader2()+"'"+","+"'"+o.getHeader3()+"'"+","+"'"+o.getHeader4()+"'"+","+"'"+o.getHeader5()+"'"+","+"'"+o.getHeader6()+"'"+")");
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
        HeaderName1.clear();
        HeaderName2.clear();
        HeaderName3.clear();
        HeaderName4.clear();
        HeaderName5.clear();
        HeaderName6.clear();
    }
}
