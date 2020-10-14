package Controllers;

import Model.*;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Werkzeug {
    public static List<String> table=new ArrayList<>();



    public static TableView DisplayTable(String column,String Name, String bedingung) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("hier passiert das scheiße"+"select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);
        System.out.println("select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);

        ResultSet rs = connection.createStatement().executeQuery("select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);

        //Tabelle1
        if (rs.getMetaData().getColumnCount() == 1) {
            ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();
            TableView<Tabelle1> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle1(rs.getString(1)));
            }
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header"));
                Tab.getColumns().add(col);
            }
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle2
        if (rs.getMetaData().getColumnCount() == 2) {
            ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
            TableView<Tabelle2> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle2(rs.getString(1), rs.getString(2)));
                // System.out.println("the header Name1 of the table are:"+rs.getString(1));
                // System.out.println("the header Name2 of the table are:"+rs.getString(2));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;

        }
        //Tabelle3
        if (rs.getMetaData().getColumnCount() == 3) {
            ObservableList<Tabelle3> Donne = FXCollections.observableArrayList();
            TableView<Tabelle3> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle3(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle4
        if (rs.getMetaData().getColumnCount() == 4) {
            ObservableList<Tabelle4> Donne = FXCollections.observableArrayList();
            TableView<Tabelle4> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle4(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle5
        if (rs.getMetaData().getColumnCount() == 5) {
            ObservableList<Tabelle5> Donne = FXCollections.observableArrayList();
            TableView<Tabelle5> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew5.add(new Tabelle5(rs.getString(NameDerHeader5a), rs.getString(NameDerHeader5b), rs.getString(NameDerHeader5c), rs.getString(NameDerHeader5d), rs.getString(NameDerHeader5e)));
                Donne.add(new Tabelle5(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle6
        if (rs.getMetaData().getColumnCount() == 6) {
            ObservableList<Tabelle6> Donne = FXCollections.observableArrayList();
            TableView<Tabelle6> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew6.add(new Tabelle6(rs.getString(NameDerHeader6a), rs.getString(NameDerHeader6b), rs.getString(NameDerHeader6c), rs.getString(NameDerHeader6d), rs.getString(NameDerHeader6e), rs.getString(NameDerHeader6f)));
                Donne.add(new Tabelle6(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle7
        if (rs.getMetaData().getColumnCount() == 7) {
            ObservableList<Tabelle7> Donne = FXCollections.observableArrayList();
            TableView<Tabelle7> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle7(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle8
        if (rs.getMetaData().getColumnCount() == 8) {
            ObservableList<Tabelle8> Donne = FXCollections.observableArrayList();
            TableView<Tabelle8> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle8(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }


        connection.close();
        return null;
    }

    public static TableView DisplayTable1(String column,String Name, String bedingung) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("hier passiert das scheiße"+"select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);
        System.out.println( Name +" "+ bedingung);

        ResultSet rs = connection.createStatement().executeQuery( Name +" "+ bedingung);

        //Tabelle1
        if (rs.getMetaData().getColumnCount() == 1) {
            ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();
            TableView<Tabelle1> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle1(rs.getString(1)));
            }
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header"));
                Tab.getColumns().add(col);
            }
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle2
        if (rs.getMetaData().getColumnCount() == 2) {
            ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
            TableView<Tabelle2> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle2(rs.getString(1), rs.getString(2)));
                // System.out.println("the header Name1 of the table are:"+rs.getString(1));
                // System.out.println("the header Name2 of the table are:"+rs.getString(2));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;

        }
        //Tabelle3
        if (rs.getMetaData().getColumnCount() == 3) {
            ObservableList<Tabelle3> Donne = FXCollections.observableArrayList();
            TableView<Tabelle3> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle3(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle4
        if (rs.getMetaData().getColumnCount() == 4) {
            ObservableList<Tabelle4> Donne = FXCollections.observableArrayList();
            TableView<Tabelle4> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle4(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle5
        if (rs.getMetaData().getColumnCount() == 5) {
            ObservableList<Tabelle5> Donne = FXCollections.observableArrayList();
            TableView<Tabelle5> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew5.add(new Tabelle5(rs.getString(NameDerHeader5a), rs.getString(NameDerHeader5b), rs.getString(NameDerHeader5c), rs.getString(NameDerHeader5d), rs.getString(NameDerHeader5e)));
                Donne.add(new Tabelle5(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle6
        if (rs.getMetaData().getColumnCount() == 6) {
            ObservableList<Tabelle6> Donne = FXCollections.observableArrayList();
            TableView<Tabelle6> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew6.add(new Tabelle6(rs.getString(NameDerHeader6a), rs.getString(NameDerHeader6b), rs.getString(NameDerHeader6c), rs.getString(NameDerHeader6d), rs.getString(NameDerHeader6e), rs.getString(NameDerHeader6f)));
                Donne.add(new Tabelle6(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle7
        if (rs.getMetaData().getColumnCount() == 7) {
            ObservableList<Tabelle7> Donne = FXCollections.observableArrayList();
            TableView<Tabelle7> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle7(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle8
        if (rs.getMetaData().getColumnCount() == 8) {
            ObservableList<Tabelle8> Donne = FXCollections.observableArrayList();
            TableView<Tabelle8> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle8(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }


        connection.close();
        return null;
    }

    public static TableView DisplayTableShiff(String column,String Name, String bedingung) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("hier passiert das scheiße"+"select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);
        System.out.println("select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);

        ResultSet rs = connection.createStatement().executeQuery("select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);

        //Tabelle1
        if (rs.getMetaData().getColumnCount() == 1) {
            ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();
            TableView<Tabelle1> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle1(rs.getString(1)));
            }
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header"));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);

            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle2
        if (rs.getMetaData().getColumnCount() == 2) {
            ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
            TableView<Tabelle2> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle2(rs.getString(2), rs.getString(1)));
                // System.out.println("the header Name1 of the table are:"+rs.getString(1));
                // System.out.println("the header Name2 of the table are:"+rs.getString(2));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);

            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;

        }
        //Tabelle3
        if (rs.getMetaData().getColumnCount() == 3) {
            ObservableList<Tabelle3> Donne = FXCollections.observableArrayList();
            TableView<Tabelle3> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle3(rs.getString(1), rs.getString(3), rs.getString(2)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle4
        if (rs.getMetaData().getColumnCount() == 4) {
            ObservableList<Tabelle4> Donne = FXCollections.observableArrayList();
            TableView<Tabelle4> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle4(rs.getString(4), rs.getString(3), rs.getString(2), rs.getString(1)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle5
        if (rs.getMetaData().getColumnCount() == 5) {
            ObservableList<Tabelle5> Donne = FXCollections.observableArrayList();
            TableView<Tabelle5> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew5.add(new Tabelle5(rs.getString(NameDerHeader5a), rs.getString(NameDerHeader5b), rs.getString(NameDerHeader5c), rs.getString(NameDerHeader5d), rs.getString(NameDerHeader5e)));
                Donne.add(new Tabelle5(rs.getString(2), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(3)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle6
        if (rs.getMetaData().getColumnCount() == 6) {
            ObservableList<Tabelle6> Donne = FXCollections.observableArrayList();
            TableView<Tabelle6> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew6.add(new Tabelle6(rs.getString(NameDerHeader6a), rs.getString(NameDerHeader6b), rs.getString(NameDerHeader6c), rs.getString(NameDerHeader6d), rs.getString(NameDerHeader6e), rs.getString(NameDerHeader6f)));
                Donne.add(new Tabelle6(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(4), rs.getString(6)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle7
        if (rs.getMetaData().getColumnCount() == 7) {
            ObservableList<Tabelle7> Donne = FXCollections.observableArrayList();
            TableView<Tabelle7> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle7(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(7), rs.getString(6), rs.getString(5)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle8
        if (rs.getMetaData().getColumnCount() == 8) {
            ObservableList<Tabelle8> Donne = FXCollections.observableArrayList();
            TableView<Tabelle8> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle8(rs.getString(3), rs.getString(2), rs.getString(1), rs.getString(4), rs.getString(7), rs.getString(6), rs.getString(5), rs.getString(8)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }


        connection.close();
        return null;
    }

    public static TableView DisplayTable1shiff(String column,String Name, String bedingung) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        System.out.println("hier passiert das scheiße"+"select"+" "+column +" "+" from" + " " + Name +" "+ bedingung);
        System.out.println( Name +" "+ bedingung);

        ResultSet rs = connection.createStatement().executeQuery( Name +" "+ bedingung);

        //Tabelle1
        if (rs.getMetaData().getColumnCount() == 1) {
            ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();
            TableView<Tabelle1> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle1(rs.getString(1)));
            }
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header"));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle2
        if (rs.getMetaData().getColumnCount() == 2) {
            ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
            TableView<Tabelle2> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle2(rs.getString(2), rs.getString(1)));
                // System.out.println("the header Name1 of the table are:"+rs.getString(1));
                // System.out.println("the header Name2 of the table are:"+rs.getString(2));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;

        }
        //Tabelle3
        if (rs.getMetaData().getColumnCount() == 3) {
            ObservableList<Tabelle3> Donne = FXCollections.observableArrayList();
            TableView<Tabelle3> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle3(rs.getString(3), rs.getString(2), rs.getString(1)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle4
        if (rs.getMetaData().getColumnCount() == 4) {
            ObservableList<Tabelle4> Donne = FXCollections.observableArrayList();
            TableView<Tabelle4> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle4(rs.getString(1), rs.getString(4), rs.getString(3), rs.getString(2)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle5
        if (rs.getMetaData().getColumnCount() == 5) {
            ObservableList<Tabelle5> Donne = FXCollections.observableArrayList();
            TableView<Tabelle5> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew5.add(new Tabelle5(rs.getString(NameDerHeader5a), rs.getString(NameDerHeader5b), rs.getString(NameDerHeader5c), rs.getString(NameDerHeader5d), rs.getString(NameDerHeader5e)));
                Donne.add(new Tabelle5(rs.getString(3), rs.getString(5), rs.getString(1), rs.getString(4), rs.getString(2)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle6
        if (rs.getMetaData().getColumnCount() == 6) {
            ObservableList<Tabelle6> Donne = FXCollections.observableArrayList();
            TableView<Tabelle6> Tab = new TableView<>();
            while (rs.next()) {
                //DataNew6.add(new Tabelle6(rs.getString(NameDerHeader6a), rs.getString(NameDerHeader6b), rs.getString(NameDerHeader6c), rs.getString(NameDerHeader6d), rs.getString(NameDerHeader6e), rs.getString(NameDerHeader6f)));
                Donne.add(new Tabelle6(rs.getString(3), rs.getString(2), rs.getString(1), rs.getString(5), rs.getString(4), rs.getString(6)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle7
        if (rs.getMetaData().getColumnCount() == 7) {
            ObservableList<Tabelle7> Donne = FXCollections.observableArrayList();
            TableView<Tabelle7> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle7(rs.getString(1), rs.getString(4), rs.getString(3), rs.getString(2), rs.getString(6), rs.getString(5), rs.getString(7)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }
        //Tabelle8
        if (rs.getMetaData().getColumnCount() == 8) {
            ObservableList<Tabelle8> Donne = FXCollections.observableArrayList();
            TableView<Tabelle8> Tab = new TableView<>();
            while (rs.next()) {
                Donne.add(new Tabelle8(rs.getString(1), rs.getString(4), rs.getString(3), rs.getString(2), rs.getString(5), rs.getString(8), rs.getString(7), rs.getString(6)));
            }
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1, String>("Header" + i));
                Tab.getColumns().add(col);
            }
            Collections.shuffle(Donne);

            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            return Tab;
        }


        connection.close();
        return null;
    }




   /* public static String getDuplicate(List<String> list1,List<String> list2){
        for(int i=0;i<list1.size();i++){
            if(list2.contains(list1.get(i))){
                return list1.get(i);

            }
        }
        return null;

    }*/
   /* public static void getTab() throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        ResultSet rs2 = connection.createStatement().executeQuery("select * from SYS.SYSTABLES where TABLETYPE ='T'");
        while (rs2.next()) {
           table.add(rs2.getString(2));

        }
    }*/


}
