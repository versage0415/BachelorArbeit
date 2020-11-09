package Controllers;

import Model.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TablesAndQuestion implements Initializable {
    public HBox TableNameArea;
    @FXML
    private Label Question;
    @FXML
    private CheckBox Answer1;
    @FXML
    private CheckBox Answer2;
    @FXML
    private CheckBox Answer3;
    @FXML
    private CheckBox Answer4;
    @FXML
    private Button AnswerCheck;
    @FXML
    private Button back;
    @FXML
    private HBox HboxArea;
    private Label TableNameId;
    private Label HeaderName;
    Stage stage;
    List<String> Box=new ArrayList<>();
    List <String> list= new ArrayList<>();

   public ObservableList<String> TableName = FXCollections.observableArrayList();
    public List<String> selection = new ArrayList<>();
    public ObservableList<String> projection = FXCollections.observableArrayList();
    public ObservableList<String> union = FXCollections.observableArrayList();
    public ObservableList<String> intersection = FXCollections.observableArrayList();
    public ObservableList<String> differenz = FXCollections.observableArrayList();
    public ObservableList<String> division = FXCollections.observableArrayList();
    public ObservableList<String> kprodukt = FXCollections.observableArrayList();
    public ObservableList<String> Njoin = FXCollections.observableArrayList();
    public ObservableList<String> LoJ = FXCollections.observableArrayList();
    public ObservableList<String> RoJ = FXCollections.observableArrayList();

   public void MyQuestion(String valeurSelectionner) throws SQLException, ClassNotFoundException {
RandomClass randomClass=new RandomClass();
       QuestionClass ques=new QuestionClass();
       AnswerClass answer=new AnswerClass();
   List<String> result=new ArrayList();
   result=ques.getList(valeurSelectionner);

       Question.setText(result.get(0));

       Answer1.setText(result.get(1));
       Answer2.setText(result.get(2));
       Answer3.setText(result.get(3));
       Answer4.setText(result.get(4));
        }




    void DropTable() throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();

        for(int i=0; i<TableName.size();i++){
            String sql="DROP TABLE "+TableName.get(i);
          Statement statement = connection.createStatement();
          statement.execute(sql);
        }

    }


    public void HandleButtonOnClick(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        stage =(Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        AnchorPane root1= FXMLLoader.load(getClass().getResource("../Views/Feedback.fxml"));
        Scene scene1= new Scene(root1);
        if(actionEvent.getSource()==AnswerCheck){
            DropTable();
            stage.setScene(scene1);
            stage.show();
        }
        AnchorPane root2= FXMLLoader.load(getClass().getResource("../Views/TableSetting.fxml"));
        Scene scene2= new Scene(root2);
        if(actionEvent.getSource()==back){
            DropTable();
            System.out.println("the size of the Box is:"+Box.size());
            stage.setScene(scene2);
            stage.show();
        }
    }





    void loadTable() throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        DatabaseMetaData md= connection.getMetaData();
        ResultSet rs1= md.getTables("bachelorarbeit",null,"%",null);
        while (rs1.next()){
            System.out.println("The table name are:"+""+rs1.getString(3));
            TableName.add(rs1.getString(3));
        }
        for(int j = 0; j< TableName.size(); j++){
        ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ TableName.get(j));
        //Tabelle1
        if(rs.getMetaData().getColumnCount()==1){
            ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();
            TableView<Tabelle1> Tab =new TableView<>();
            while ( rs.next()){
                Donne.add(new Tabelle1(rs.getString(1)));
             //   System.out.println("the header Name of the table are:"+rs.getString(1));
            }

            for(int i=0; i<rs.getMetaData().getColumnCount();i++){
                TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"));
                Tab.getColumns().add(col);
            }
            Tab.setEditable(true);
            Tab.setItems(Donne);
            Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            //Tab.setFixedCellSize(Donne.size());
            //Tab.setMaxHeight(10);
            Tab.setPrefWidth(100);
            Label label=new Label();
            label.setText(TableName.get(j));
            HboxArea.getStylesheets().add("TableStyle.css");
            HboxArea.setSpacing(5);
            TableNameArea.setSpacing(10);
            TableNameArea.getChildren().add(label);

            HboxArea.getChildren().add(Tab);}
            //Tabelle2
            if(rs.getMetaData().getColumnCount()==2){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                TableView<Tabelle2> Tab =new TableView<>();
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                   // System.out.println("the header Name1 of the table are:"+rs.getString(1));
                   // System.out.println("the header Name2 of the table are:"+rs.getString(2));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));

                    Tab.getColumns().add(col);
                }


                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);
            }
            //Tabelle3
            if(rs.getMetaData().getColumnCount()==3){
                ObservableList<Tabelle3> Donne = FXCollections.observableArrayList();
                TableView<Tabelle3> Tab =new TableView<>();
                while (rs.next()) {
                    Donne.add(new Tabelle3(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}
            //Tabelle4
            if(rs.getMetaData().getColumnCount()==4){
                ObservableList<Tabelle4> Donne = FXCollections.observableArrayList();
                TableView<Tabelle4> Tab =new TableView<>();
                while (rs.next()) {
                    Donne.add(new Tabelle4(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}
            //Tabelle5
            if(rs.getMetaData().getColumnCount()==5){
                ObservableList<Tabelle5> Donne = FXCollections.observableArrayList();
                TableView<Tabelle5> Tab =new TableView<>();
                while (rs.next()) {
                    //DataNew5.add(new Tabelle5(rs.getString(NameDerHeader5a), rs.getString(NameDerHeader5b), rs.getString(NameDerHeader5c), rs.getString(NameDerHeader5d), rs.getString(NameDerHeader5e)));
                    Donne.add(new Tabelle5(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}
            //Tabelle6
            if(rs.getMetaData().getColumnCount()==6){
                ObservableList<Tabelle6> Donne = FXCollections.observableArrayList();
                TableView<Tabelle6> Tab =new TableView<>();
                while (rs.next()) {
                    //DataNew6.add(new Tabelle6(rs.getString(NameDerHeader6a), rs.getString(NameDerHeader6b), rs.getString(NameDerHeader6c), rs.getString(NameDerHeader6d), rs.getString(NameDerHeader6e), rs.getString(NameDerHeader6f)));
                    Donne.add(new Tabelle6(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}
            //Tabelle7
            if(rs.getMetaData().getColumnCount()==7){
                ObservableList<Tabelle7> Donne = FXCollections.observableArrayList();
                TableView<Tabelle7> Tab =new TableView<>();
                while (rs.next()) {
                    Donne.add(new Tabelle7(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}
            //Tabelle8
            if(rs.getMetaData().getColumnCount()==8){
                ObservableList<Tabelle8> Donne = FXCollections.observableArrayList();
                TableView<Tabelle8> Tab =new TableView<>();
                while (rs.next()) {
                    Donne.add(new Tabelle8(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
                for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
                    TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i));
                    col.setCellValueFactory(new PropertyValueFactory<Tabelle1,String>("Header"+i));
                    Tab.getColumns().add(col);
                }
                Tab.setEditable(true);
                Tab.setItems(Donne);
                Tab.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                Label label=new Label();
                label.setText(TableName.get(j));
                HboxArea.getStylesheets().add("TableStyle.css");
                HboxArea.setSpacing(5);
                TableNameArea.setSpacing(10);
                TableNameArea.getChildren().add(label);
                HboxArea.getChildren().add(Tab);}

        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            loadTable();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
