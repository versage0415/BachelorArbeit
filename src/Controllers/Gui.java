package Controllers;

import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Gui implements Initializable {
    public ListView RelationList;
    public ListView OperatorList;
    public Button CreateNewRelation;
    public Button DropRelation;
    public Button ShowRelation;
    public AnchorPane anchorMainWindows;
    public ListView QuestionList;
    public CheckBox Answer1;
    public CheckBox Answer2;
    public CheckBox Answer3;
    public CheckBox Answer4;
    public Label Question;
    public AnchorPane ErrorPane;
    public Button SetNewAnswers;
    public Button GenerateAnswers;
    public Button RefreshRelationList;
    public Button ShowQuestion;
    public Button LoadTable;
    public Button LoadOperator;

    ///parameter für question1
    public AnchorPane question1;
    public AnchorPane anchorMainTable1;
    public AnchorPane anchorOperator;
    public Label labelOperator;
    public AnchorPane anchorRelation1;
    public AnchorPane anchorRelation2;
    public AnchorPane anchorRelation3;
    public AnchorPane anchorRelation4;
    public ListView ConstraintList;
    public Line FilQuestion1;
    public Line file2;
    public Line file3;
    public Line File4;
    public TextField TextInputQuestion1;
    public GridPane GridpaneQ1;
    public Label Relation4Q1;
    public Label Relation3Q1;
    public Label Relation2Q1;
    public Label Relation1Q1;
    //Parameter Question2
    public AnchorPane Question2;
    public AnchorPane PaneTab1;
    public AnchorPane PaneTab2;
    public AnchorPane PaneOperator;
    public Label LabOperator;
    public Line fileRelation2Q2;
    public Button loadExo;
    public ImageView imageView;

    public Button Help;
    public Line fileQ2;
    public AnchorPane PaneRessultTab1;
    public TextField constraintQ2;
    public AnchorPane PaneRessultTab2;
    //parameter for binary operator
    public Button LoadTable1;
    public Button LoadTable2;
    //Parameter Question3
    public AnchorPane Question3;
    public AnchorPane PaneTab1Q3;
    public AnchorPane PaneTab2Q3;
    public AnchorPane PaneOperatorQ3;
    public Label LabelOperatorQ3;
    public AnchorPane PaneResultQ3;
    public Line fileQ3;
    public TextField ConstraintQ3;
    //Parameter Question4
    public AnchorPane Question4;
    public AnchorPane PaneTab1Q4;
    public AnchorPane PaneOperator1Q4;
    public Label LabelOperator1Q4;
    public AnchorPane SubResultTab1Q4;
    public AnchorPane ResultTab2Q4;
    public AnchorPane PaneTab2Q4;
    public AnchorPane PaneTab3Q4;
    public AnchorPane PaneOperator2Q4;
    public Label LabelOperator2Q4;
    public AnchorPane ResultTab3Q4;
    public ListView ConstraintListOperator1Q4;
    public ListView ConstraintListOperator2Q4;
    public TextField ConstraintOperator2Q4;
    public TextField ConstraintOperator1Q4;
    public Button LoadOperator1;
    public Button LoadOperator2;
    public Button GenerateSubresult;
    public AnchorPane ResultTab4Q4;
    public Line FileQ4;
    public Button refresh;

    List<String>Header1=new ArrayList<>();
    List<String>Header2=new ArrayList<>();
    String ColValue="";
    List<String>finaleHeader=new ArrayList<>();
    int count1=0;


    int screen1, screen2, screen3,screen4;
    String Table;
    List<String> cons=new ArrayList<>();
    int op=0;
    int op1=0;
    String constraint;
    TableView tableau= new TableView();

    List<String> RelationName =new ArrayList<>();
 List<String> NewRelationName =new ArrayList<>();
    List<String> ques =new ArrayList<>();
    List<String> TabList =new ArrayList<>();
    public void CreateNewRelationBtn(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        Stage stage=new Stage();
        stage.setTitle("Create new Table");
        AnchorPane root= FXMLLoader.load(getClass().getResource("/Views/CompaktTableBuilder.fxml"));
        Scene scene= new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        refreshList();
    }

    public void DropRelationBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        SelectionModel<String> sm= RelationList.getSelectionModel();
        RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try{
        for(int i=0; i<RelationName.size();i++){
            if(sm.getSelectedItem().equals(RelationName.get(i))){
                RelationList.getItems().remove(i);
                String sql="DROP TABLE "+" "+RelationName.get(i);
                Statement statement = connection.createStatement();
                statement.execute(sql);
                refreshList();
                break;
            }
        }
        }catch (NullPointerException e){
            ErrorPane.getChildren().clear();
            Label label=new Label("please select the relation you want to drop!");
            ErrorPane.getChildren().add(label);
        }
        connection.close();
    }

    public void ShowRelationBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Stage stage=new Stage();
        stage.setTitle("TableView");
        SelectionModel<String> sm= RelationList.getSelectionModel();
        RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
        for(int i=0;i<RelationName.size();i++){
            if(sm.getSelectedItem().equals(RelationName.get(i))){
                TableView tab=Werkzeug.DisplayTable("*",RelationName.get(i),"");
                stage.setScene(new Scene(tab,300,300));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        }
        }catch (NullPointerException e){
            ErrorPane.getChildren().clear();
            Label label=new Label("please select a relation first!");
            ErrorPane.getChildren().add(label);
        }
    }

    public void SetNewAnswersBtn(ActionEvent actionEvent) {

        Stage stage=new Stage();
        stage.setTitle("Edit Answers");
        AnchorPane root=new AnchorPane();
        TextField textField1=new TextField();
        textField1.setPromptText("Enter the Question hier");
        TextField textField2=new TextField();
        textField2.setPromptText("Enter the first Answer hier");
        TextField textField3=new TextField();
        textField3.setPromptText("Enter the second Answer hier");
        TextField textField4=new TextField();
        textField4.setPromptText("Enter the third Answer hier");
        TextField textField5=new TextField();
        textField5.setPromptText("Enter the fourth Answer hier");
        Button button=new Button("Save");
        VBox vBox=new VBox(textField1,textField2,textField3,textField4,textField5,button);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.setSpacing(8);
        button.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {

                if(!textField1.getText().isEmpty()){
                    Question.setText(textField1.getText());
                   }
                if(textField1.getText().isEmpty()){
                    return;
                }
                //////
                if(!textField2.getText().isEmpty()){
                    Answer1.setVisible(true);
                    Answer1.setText(textField2.getText());
                    }
                if(textField2.getText().isEmpty()){
                    Answer1.setVisible(false);
                }
                //////
                if(!textField3.getText().isEmpty()){
                    Answer2.setVisible(true);
                    Answer2.setText(textField3.getText());
                    }
                if(textField3.getText().isEmpty()){
                    Answer2.setVisible(false);
                }
                ////////
                if(!textField4.getText().isEmpty()){
                    Answer3.setVisible(true);
                    Answer3.setText(textField4.getText());
                    }
                if(textField4.getText().isEmpty()){
                    Answer3.setVisible(false);
                }
                ///////
                if(!textField5.getText().isEmpty()){
                    Answer4.setVisible(true);
                    Answer4.setText(textField5.getText());
                }
                if(textField5.getText().isEmpty()){
                    Answer4.setVisible(false);
                }
            }
        });
        root.getChildren().add(vBox);
        stage.setScene(new Scene(root,300,300));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
    public void GenerateAnswersBtn() throws SQLException, ClassNotFoundException  {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        RandomClass obj=new RandomClass();
        String projectCol;
        List<String>val=new ArrayList<>();
        List<String>val1=new ArrayList<>();
        List <String> projectval=new ArrayList<>();

        if(op==1){
            if(screen1==1){
                try {
                    ErrorPane.getChildren().clear();
        ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ Table);
        String ColumnName=rs.getMetaData().getColumnName(obj.getrandomColumn(rs.getMetaData().getColumnCount()));
        rs= connection.createStatement().executeQuery("select" +" "+ColumnName+" "+ "from"+" "+ Table);
        while ( rs.next()){
            val.add(rs.getString(obj.getrandomColumn(rs.getMetaData().getColumnCount())));
        }
        connection.close();
    TableView tab = Werkzeug.DisplayTable("*", Table, "where" + " " + TextInputQuestion1.getText());
    tab.setMaxWidth(147);
    tab.setMaxHeight(153);
    anchorRelation1.getChildren().add(tab);
        TableView tab1 = Werkzeug.DisplayTableShiff("*", Table, "where" + " " + TextInputQuestion1.getText());
        tab1.setMaxWidth(147);
        tab1.setMaxHeight(153);
        anchorRelation2.getChildren().add(tab1);
         if (val.size() != 0) {
        TableView tab3 = Werkzeug.DisplayTableShiff("*", Table, "where" + " " + ColumnName + "=" + "'" + val.get(0) + "'");
        tab3.setMaxWidth(147);
        tab3.setMaxHeight(153);
        anchorRelation3.getChildren().add(tab3);

        TableView tab4 = Werkzeug.DisplayTable("*", Table, "where" + " " + ColumnName + "=" + "'" + val.get(0) + "'");
        tab4.setMaxWidth(147);
        tab4.setMaxHeight(153);
        anchorRelation4.getChildren().add(tab4);
    }

                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Error in your Constraint. The model is: column='x' ");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen2==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. try by Exercice 1");
                ErrorPane.getChildren().add(label);
            }
            if(screen3==1){
             ErrorPane.getChildren().clear();
             Label label=new Label("this Exercice can't be generated by this Operator. try by Exercice 1");
             ErrorPane.getChildren().add(label);
            }   
            if(screen4==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("we expect a binary operator here");
                ErrorPane.getChildren().add(label);
            }

}
        if(op==2){
            if (screen1==1){
                try {
                    SelectionModel<String> sm = ConstraintList.getSelectionModel();
                    ConstraintList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    for (int i = 0; i < ConstraintList.getItems().size(); i++) {
                        cons.add(ConstraintList.getItems().get(i).toString());
                        if (sm.getSelectedItem().equals(ConstraintList.getItems().get(i))) {
                            constraint = ConstraintList.getItems().get(i).toString();
                            cons.remove(i);
                        }
                    }
                    TableView tab1 = Werkzeug.DisplayTable(constraint, Table, "");
                    tab1.setMaxWidth(147);
                    tab1.setMaxHeight(153);
                    anchorRelation1.getChildren().add(tab1);
                    TableView tab3 = Werkzeug.DisplayTableShiff(constraint, Table, "");
                    tab3.setMaxWidth(147);
                    tab3.setMaxHeight(153);
                    anchorRelation2.getChildren().add(tab3);
                    if (cons.size() > 1) {
                        TableView tab2 = Werkzeug.DisplayTable(cons.get(0), Table, "");
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        anchorRelation3.getChildren().add(tab2);
                        TableView tab4 = Werkzeug.DisplayTableShiff(cons.get(0), Table, "");
                        tab4.setMaxWidth(147);
                        tab4.setMaxHeight(153);
                        anchorRelation4.getChildren().add(tab4);
                    }

                    if (cons.size() < 1) {
                        anchorRelation4.setVisible(false);
                        File4.setVisible(false);
                        Relation4Q1.setVisible(false);
                    }
                   /* if (cons.size() < 2) {
                        anchorRelation3.setVisible(false);
                        file3.setVisible(false);
                        Relation3Q1.setVisible(false);
                    }*/
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen2==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. try by Exercice 1");
                ErrorPane.getChildren().add(label);
            }
            if(screen3==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. try by Exercice 1");
                ErrorPane.getChildren().add(label);
            }
            if(screen4==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("we expect a binary operator here");
                ErrorPane.getChildren().add(label);
            }
}
        if(op==3){
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1){
                try {
                    fileQ2.setVisible(false);
                    constraintQ2.setVisible(false);
                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Union" + " " + "select * from" + " " + TabList.get(1));
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);

                    TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "Union All" + " " + "select * from" + " " + TabList.get(1));
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                try {
                    fileQ2.setVisible(false);
                    constraintQ2.setVisible(false);
                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Union" + " " + "select * from" + " " + TabList.get(1));
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneResultQ3.getChildren().add(tab);
                    LabelOperatorQ3.setText("?");

                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if(op1==1){
               // try {
String subresult="(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")";
                    TableView tab = Werkzeug.DisplayTable1("*",subresult , "Union" + " " + "(select * from" + " " + TabList.get(1)+")");
                    tab.setMaxWidth(147);
                    tab.setMaxHeight(153);
                    ResultTab2Q4.getChildren().add(tab);

                    TableView tab1 = Werkzeug.DisplayTable1("*", subresult, "Union All" + " " + "(select * from" + " " + TabList.get(1)+")");
                    tab1.setMaxWidth(147);
                    tab1.setMaxHeight(153);
                    ResultTab3Q4.getChildren().add(tab1);
                    /////
                    TableView tab2 = Werkzeug.DisplayTable1shiff("*", subresult, "Union" + " " + "(select * from" + " " + TabList.get(1)+")");
                    tab2.setMaxWidth(147);
                    tab2.setMaxHeight(153);
                    ResultTab4Q4.getChildren().add(tab2);
            }
                if(op1==2){
                    try {
                        TableView tab = Werkzeug.DisplayTable1("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Union" + " " + "select * from" + " " + TabList.get(1));
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab);

                        TableView tab1 = Werkzeug.DisplayTable1("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Union All" + " " + "select * from" + " " + TabList.get(1));
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab1);

                        TableView tab2 = Werkzeug.DisplayTable1shiff("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Union" + " " + "select * from" + " " + TabList.get(1));
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    }catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }
                }
        }

}
        if(op==4) {
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1){
                try {
                    fileQ2.setVisible(false);
                    constraintQ2.setVisible(false);
                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Intersect" + " " + "select * from" + " " + TabList.get(1));
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);
                    ///
                    TableView tab1 = Werkzeug.DisplayTableShiff("*", TabList.get(0), "Intersect" + " " + "select * from" + " " + TabList.get(1));
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Intersect" + " " + "select * from" + " " + TabList.get(1));
                tab.setMaxWidth(200);
                tab.setMaxHeight(159);
                PaneResultQ3.getChildren().add(tab);
                LabelOperatorQ3.setText("?");
            }
            if(screen4==1) {
                if (op1 == 1){
                    try {
                        TableView tab = Werkzeug.DisplayTable1("*","(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")", "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        ResultTab2Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab1.setMaxWidth(200);
                        tab1.setMaxHeight(159);
                        ResultTab3Q4.getChildren().add(tab1);

                        TableView tab2 = Werkzeug.DisplayTable1shiff("*","(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")", "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab2.setMaxWidth(200);
                        tab2.setMaxHeight(159);
                        ResultTab4Q4.getChildren().add(tab2);
                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: " + e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }
            }
                if (op1 == 2){
                    try {
                        TableView tab = Werkzeug.DisplayTable1("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable1("*",TabList.get(0), "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTable1shiff("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Intersect" + " " + "select * from" + " " + TabList.get(1));
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: " + e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }
                }
            }

      }
        if(op==5){
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1) {
                try {
                    fileQ2.setVisible(false);
                    constraintQ2.setVisible(false);
                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Except" + " " + "select * from" + " " + TabList.get(1));
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);
                    ////
                    TableView tab1 = Werkzeug.DisplayTableShiff("*", TabList.get(0), "Except" + " " + "select * from" + " " + TabList.get(1));
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Except" + " " + "select * from" + " " + TabList.get(1));
                tab.setMaxWidth(200);
                tab.setMaxHeight(159);
                PaneResultQ3.getChildren().add(tab);
                LabelOperatorQ3.setText("?");
            }
            if(screen4==1){
                if (op1==1){
                    try {

                        TableView tab = Werkzeug.DisplayTable1("*","(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")", "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab1.setMaxWidth(200);
                        tab1.setMaxHeight(159);
                        ResultTab2Q4.getChildren().add(tab1);
                        ////
                        TableView tab2 = Werkzeug.DisplayTable1shiff("*","(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")", "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab2.setMaxWidth(200);
                        tab2.setMaxHeight(159);
                        ResultTab4Q4.getChildren().add(tab2);
                    }catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        TableView tab = Werkzeug.DisplayTable1("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable1("*",TabList.get(0), "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        //
                        TableView tab2 = Werkzeug.DisplayTable1shiff("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")", "Except" + " " + "select * from" + " " + TabList.get(1));
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    }catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }

                }

            }
        }
        if(op==6) {
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1) {
                fileQ2.setVisible(false);
                constraintQ2.setVisible(false);
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                    connection.close();
                    if (Header1.size() > Header2.size()) {
                        Header1.removeAll(Header2);
                        for (int i = 0; i < Header1.size(); i++) {
                            ColValue += Header1.get(i);
                            if (i != Header1.size() - 1) {
                                ColValue += ",";
                            }
                        }
                        finaleHeader = Header1;
                        TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");
                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        PaneRessultTab1.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTableShiff(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");
                        tab1.setMaxWidth(200);
                        tab1.setMaxHeight(159);
                        PaneRessultTab2.getChildren().add(tab1);
                    }
                else if (Header1.size() < Header2.size()) {
                        Header2.removeAll(Header1);
                        for (int i = 0; i < Header2.size(); i++) {
                            ColValue += Header2.get(i);
                            if (i != Header2.size() - 1) {
                                ColValue += ",";
                            }
                        }
                        finaleHeader = Header1;

                        TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        PaneRessultTab1.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTableShiff(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                        tab1.setMaxWidth(200);
                        tab1.setMaxHeight(159);
                        PaneRessultTab2.getChildren().add(tab1);
                    }
                    else{
                        ErrorPane.getChildren().clear();
                        Label label = new Label("the both Table are not compatible for a Division");
                        ErrorPane.getChildren().add(label);
                    }
                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("the both Table are not compatible for a Division");
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    //System.out.println("the header1 size is:" + Header1.size());
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                    //System.out.println("the header2 size is:" + Header2.size());
                    connection.close();
                    if (Header1.size() > Header2.size()) {
                        Header1.removeAll(Header2);
                        for (int i = 0; i < Header1.size(); i++) {
                            ColValue += Header1.get(i);
                            if (i != Header1.size() - 1) {
                                ColValue += ",";
                            }
                        }
                        //System.out.println("Colvalue=" + ColValue);
                        finaleHeader = Header1;
                        // System.out.println("voici la magie seulement:" + ColValue + " " + TabList.get(1) + " " + "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");

                        TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");

                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        PaneResultQ3.getChildren().add(tab);
                        LabelOperatorQ3.setText("?");
                    }
                    if (Header1.size() < Header2.size()) {
                        Header2.removeAll(Header1);
                        for (int i = 0; i < Header2.size(); i++) {
                            ColValue += Header2.get(i);
                            if (i != Header2.size() - 1) {
                                ColValue += ",";
                            }
                        }
                        finaleHeader = Header1;

                        TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                        tab.setMaxWidth(200);
                        tab.setMaxHeight(159);
                        PaneResultQ3.getChildren().add(tab);
                        LabelOperatorQ3.setText("?");
                    }
                    if (Header1.size() == Header2.size()) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("the both Table are not compatible for a Division");
                        ErrorPane.getChildren().add(label);
                    }
                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("the both Table are not compatible for a Division");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if (op1==1){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText());
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        connection.close();
                        if (Header1.size() > Header2.size()) {
                            Header1.removeAll(Header2);
                            for (int i = 0; i < Header1.size(); i++) {
                                ColValue += Header1.get(i);
                                if (i != Header1.size() - 1) {
                                    ColValue += ",";
                                }
                            }
                            finaleHeader = Header1;
                            TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(1) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");
                            tab.setMaxWidth(147);
                            tab.setMaxHeight(153);
                            ResultTab3Q4.getChildren().add(tab);
                            TableView tab1 = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" +" "+ConstraintOperator1Q4.getText()+" "+"and"+ " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0)+ ")");
                            tab1.setMaxWidth(147);
                            tab1.setMaxHeight(153);
                            ResultTab2Q4.getChildren().add(tab1);
                            TableView tab2 = Werkzeug.DisplayTableShiff(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(1) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(0) + ")");
                            tab2.setMaxWidth(147);
                            tab2.setMaxHeight(153);
                            ResultTab4Q4.getChildren().add(tab2);
                        }
                        else if (Header1.size() < Header2.size()) {
                            Header2.removeAll(Header1);
                            for (int i = 0; i < Header2.size(); i++) {
                                ColValue += Header2.get(i);
                                if (i != Header2.size() - 1) {
                                    ColValue += ",";
                                }
                            }
                            finaleHeader = Header1;
                            System.out.println("Colvalue=" + ColValue);
                            TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(1) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                            tab.setMaxWidth(147);
                            tab.setMaxHeight(153);
                            ResultTab3Q4.getChildren().add(tab);
                            TableView tab1 = Werkzeug.DisplayTable(ColValue,TabList.get(0), "WHERE" +" "+ConstraintOperator1Q4.getText()+" "+"and"+ " " + Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                            tab1.setMaxWidth(147);
                            tab1.setMaxHeight(153);
                            ResultTab2Q4.getChildren().add(tab1);
                            TableView tab2 = Werkzeug.DisplayTableShiff(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(1) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                            tab2.setMaxWidth(147);
                            tab2.setMaxHeight(153);
                            ResultTab4Q4.getChildren().add(tab2);
                        }
                        else {
                            ErrorPane.getChildren().clear();
                            Label label = new Label("the both Table are not compatible for a Division");
                            ErrorPane.getChildren().add(label);
                        }
                    } catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label = new Label("the both Table are not compatible for a Division");
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0));
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }

                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }

                        connection.close();
                        System.out.println("thsize of Header1 is:"+Header1.size());
                        System.out.println("thsize of Header2 is:"+Header2.size());
                        if (Header1.size() > Header2.size()) {
                            Header1.removeAll(Header2);
                            for (int i = 0; i < Header1.size(); i++) {
                                ColValue += Header1.get(i);
                                if (i != Header1.size() - 1) {
                                    ColValue += ",";
                                }
                            }
                            finaleHeader = Header1;
                            TableView tab = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + TabList.get(0) + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*)FROM" + " " + TabList.get(0) + ")");
                            tab.setMaxWidth(147);
                            tab.setMaxHeight(153);
                            ResultTab3Q4.getChildren().add(tab);
                            TableView tab1 = Werkzeug.DisplayTable(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + "(select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0)+")"+"as Subresult" + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*)FROM" + " " + "(select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0)+")"+"as Subresult1" + ")");
                            tab1.setMaxWidth(147);
                            tab1.setMaxHeight(153);
                            ResultTab2Q4.getChildren().add(tab1);
                            TableView tab2 = Werkzeug.DisplayTableShiff(ColValue, TabList.get(1), "WHERE" + " " + Header2.get(0) + " " + "IN ( SELECT" + " " + Header2.get(0) + " " + "FROM" + " " + "(select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0)+")"+"as Subresult" + " )" + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*)FROM" + " " + "(select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0)+")"+"as Subresult1" + ")");
                            tab2.setMaxWidth(147);
                            tab2.setMaxHeight(153);
                            ResultTab4Q4.getChildren().add(tab2);
                        }
                       else if (Header1.size() < Header2.size()) {
                            Header2.removeAll(Header1);
                            for (int i = 0; i < Header2.size(); i++) {
                                ColValue += Header2.get(i);
                                if (i != Header2.size() - 1) {
                                    ColValue += ",";
                                }
                            }
                            finaleHeader = Header1;
                            TableView tab = Werkzeug.DisplayTable(ColValue, "(select"+" "+ ConstraintOperator1Q4.getText() +" "+ "from" + " " + TabList.get(0)+")"+"as Subresult", "WHERE" +" "+ Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                            tab.setMaxWidth(147);
                            tab.setMaxHeight(153);
                            ResultTab3Q4.getChildren().add(tab);
                            TableView tab1 = Werkzeug.DisplayTable(ColValue, TabList.get(0), "WHERE" + " " + Header1.get(0) + " " + "IN ( SELECT" + " " + Header1.get(0) + " " + "FROM" + " " + TabList.get(1) + ") " + "GROUP BY" + " " + ColValue + " " + "HAVING COUNT (*) = ( SELECT COUNT(*) FROM" + " " + TabList.get(1) + ")");
                            tab1.setMaxWidth(147);
                            tab1.setMaxHeight(153);
                            ResultTab2Q4.getChildren().add(tab1);

                        }

                        else  {
                            ErrorPane.getChildren().clear();
                            Label label = new Label("the both Table are not compatible for a Division");
                            ErrorPane.getChildren().add(label);
                        }
                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is some syntax Error.Please check the syntax that you Enter as Constraint. The model is: column1,column2,...");
                        ErrorPane.getChildren().add(label);
                    }
                }

            }
        }
        if(op==7) {
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1){
                try {
            fileQ2.setVisible(false);
            constraintQ2.setVisible(false);
            TableView tab = Werkzeug.DisplayTable("*",TabList.get(0),","+TabList.get(1));
            tab.setMaxWidth(200);
            tab.setMaxHeight(159);
            PaneRessultTab1.getChildren().add(tab);
            TableView tab1 = Werkzeug.DisplayTableShiff("*",TabList.get(0),","+TabList.get(1));
            tab1.setMaxWidth(200);
            tab1.setMaxHeight(159);
            PaneRessultTab2.getChildren().add(tab1);
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen3==1){
                try {
                    TableView tab = Werkzeug.DisplayTable("*",TabList.get(0),TabList.get(0)+" "+","+TabList.get(1));
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneResultQ3.getChildren().add(tab);
                    LabelOperatorQ3.setText("?");
                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if (op1==1){
                    try {


                    TableView tab = Werkzeug.DisplayTable("*",TabList.get(0),TabList.get(0)+","+TabList.get(1) +" "+"where"+" "+ConstraintOperator1Q4.getText());
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*",TabList.get(0),TabList.get(0)+" "+","+TabList.get(1));
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTableShiff("*",TabList.get(0),TabList.get(0)+","+TabList.get(1) +" "+"where"+" "+ConstraintOperator1Q4.getText());
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    }catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label=new Label("Syntax Error:check the syntax of the constraint or maybe the Table are not compatible for this operation");
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        TableView tab = Werkzeug.DisplayTable("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+") as subresult", ","+TabList.get(1));
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*",TabList.get(0),","+TabList.get(1));
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTableShiff("*","(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+") as subresult", ","+TabList.get(1));
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    }catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                        ErrorPane.getChildren().add(label);
                    }
                }
            }
        }
        if(op==8) {
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1) {
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Inner Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);
                    TableView tab1 = Werkzeug.DisplayTableShiff("*", TabList.get(0), "Inner Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);
                   // PaneRessultTab2.setVisible(false);
                    //fileRelation2Q2.setVisible(true);
                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen3==1){
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                    //String p = Werkzeug.getDuplicate(Header1, Header2);

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Inner Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneResultQ3.getChildren().add(tab);
                    LabelOperatorQ3.setText("?");


                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if (op1==1){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery( "(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }

                        TableView tab = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "Inner Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                    TableView tab1 = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "Inner Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText());
                    tab1.setMaxWidth(147);
                    tab1.setMaxHeight(153);
                    ResultTab2Q4.getChildren().add(tab1);
                    TableView tab2 = Werkzeug.DisplayTableShiff("*",TabList.get(0) +" ", "Inner Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                    tab2.setMaxWidth(147);
                    tab2.setMaxHeight(153);
                    ResultTab4Q4.getChildren().add(tab2);

                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+" "+") as subresult");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        TableView tab = Werkzeug.DisplayTable("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "Inner Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "Inner Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTableShiff("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "Inner Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);

                    } catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
                }
            }
        }
        if(op==9){
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1) {
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }

                   // String p = Werkzeug.getDuplicate(Header1, Header2);

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Left Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);
                    TableView tab1 = Werkzeug.DisplayTableShiff("*", TabList.get(0), "Left Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);

                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }

                   // String p = Werkzeug.getDuplicate(Header1, Header2);

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "Left Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneResultQ3.getChildren().add(tab);
                    LabelOperatorQ3.setText("?");


                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if (op1==1){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery( "(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        TableView tab = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "Left Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "Left Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText());
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);

                        TableView tab2 = Werkzeug.DisplayTableShiff("*",TabList.get(0) +" ", "Left Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);

                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+" "+") as subresult");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        TableView tab = Werkzeug.DisplayTable("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "Left Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "Left Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);

                        TableView tab2 = Werkzeug.DisplayTableShiff("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "Left Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);

                    } catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
            }
        }
        }
        if(op==10){
            if(screen1==1){
                ErrorPane.getChildren().clear();
                Label label=new Label("this Exercice can't be generated by this Operator. ");
                ErrorPane.getChildren().add(label);
            }
            if (screen2==1) {
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                    //String p = Werkzeug.getDuplicate(Header1, Header2);

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "right Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneRessultTab1.getChildren().add(tab);
                    TableView tab1 = Werkzeug.DisplayTableShiff("*", TabList.get(0), "right Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(159);
                    PaneRessultTab2.getChildren().add(tab1);
                   // PaneRessultTab2.setVisible(false);
                   // fileRelation2Q2.setVisible(false);

                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if (screen3==1){
                try {
                    ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + TabList.get(0));
                    ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                    for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                        Header1.add(rs1.getMetaData().getColumnName(i));
                    }
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                        Header2.add(rs.getMetaData().getColumnName(i));
                    }
                   // String p = Werkzeug.getDuplicate(Header1, Header2);

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "right Join" + " " + TabList.get(1) + "  " + constraintQ2.getText());
                    tab.setMaxWidth(200);
                    tab.setMaxHeight(159);
                    PaneResultQ3.getChildren().add(tab);
                    LabelOperatorQ3.setText("?");

                } catch (SQLException e) {
                    ErrorPane.getChildren().clear();
                    Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                    ErrorPane.getChildren().add(label);
                }
            }
            if(screen4==1){
                if (op1==1){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("(select * from"+" "+TabList.get(0)+" "+"where"+" "+ConstraintOperator1Q4.getText()+")");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) { //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        TableView tab = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "right Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*",TabList.get(0) +" ", "right Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText());
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTableShiff("*",TabList.get(0) +" ", "right Join" + " " + TabList.get(1)+ " "  + ConstraintOperator2Q4.getText()+ " " +"where"+" "+ConstraintOperator1Q4.getText()+" ");
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);

                    } catch (SQLException e) {
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
                }
                if (op1==2){
                    try {
                        ResultSet rs = connection.createStatement().executeQuery("select * from" + " " + "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+" "+") as subresult");
                        ResultSet rs1 = connection.createStatement().executeQuery("select * from" + " " + TabList.get(1));
                        for (int i = 1; i <= rs1.getMetaData().getColumnCount(); i++) { //result.get(6)    Header1=//result.get(6)
                            Header1.add(rs1.getMetaData().getColumnName(i));
                        }
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){ //result.get(5)     Header2=//result.get(5)
                            Header2.add(rs.getMetaData().getColumnName(i));
                        }
                        TableView tab = Werkzeug.DisplayTable("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "right Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab.setMaxWidth(147);
                        tab.setMaxHeight(153);
                        ResultTab3Q4.getChildren().add(tab);
                        TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "right Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab1.setMaxWidth(147);
                        tab1.setMaxHeight(153);
                        ResultTab2Q4.getChildren().add(tab1);
                        TableView tab2 = Werkzeug.DisplayTableShiff("*", "(select"+" "+ConstraintOperator1Q4.getText()+" "+" from"+" "+TabList.get(0)+")as SubResult", "right Join" + " " + TabList.get(1) + " " + ConstraintOperator2Q4.getText());
                        tab2.setMaxWidth(147);
                        tab2.setMaxHeight(153);
                        ResultTab4Q4.getChildren().add(tab2);
                    } catch (SQLException e){
                        ErrorPane.getChildren().clear();
                        Label label = new Label("there is a error in a syntax of the constraint. You have to Use USING(X) or use ON(TABLE.X=TABLE.X)");
                        ErrorPane.getChildren().add(label);
                    }
                }

            }
        }

    }
    public void loadOperator(){
        OperatorList.getItems().add("Selection (σ)");
        OperatorList.getItems().add("Projection (π)");
        OperatorList.getItems().add("Union (∪)");
        OperatorList.getItems().add("Intersection (∩)");
        OperatorList.getItems().add("Difference (-)");
        OperatorList.getItems().add("Division (÷)");
        OperatorList.getItems().add("Cartesian Product (X)");
        OperatorList.getItems().add("Natural Join (⋈)");
        OperatorList.getItems().add("Left Outer Join (⟕) ");
        OperatorList.getItems().add("Right Outer Join (⟖)");

    }
    public void loadRelations(){
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            ResultSet rs = connection.createStatement().executeQuery("select * from SYS.SYSTABLES where TABLETYPE ='T'");
            while (rs.next()) {
                RelationName.add(rs.getString(2));
                RelationList.getItems().add(rs.getString(2));
            }
            connection.close();
        }catch (SQLException e){
            e.getMessage();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void loadQuestion()  {
        QuestionList.getItems().add("Exercice 1");
        ques.add("Exercice 1");
        QuestionList.getItems().add("Exercice 2");
        ques.add("Exercice 2");
        QuestionList.getItems().add("Exercice 3");
        ques.add("Exercice 3");
        QuestionList.getItems().add("Exercice 4");
        ques.add("Exercice 4");


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            loadRelations();
            loadOperator();
            loadQuestion();

    }

    public   void refreshList() throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select * from SYS.SYSTABLES where TABLETYPE ='T'");
        NewRelationName.clear();
        RelationName.clear();
        RelationList.getItems().clear();

        while (rs.next()) {
            RelationName.add(rs.getString(2));
            RelationList.getItems().add(rs.getString(2));
        }

        connection.close();


    }

    public void RefreshRelationListBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        refreshList();
    }

    public void ShowQuestionBtn(ActionEvent actionEvent) throws IOException {
        SelectionModel<String> sm = QuestionList.getSelectionModel();
        QuestionList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println(ques.size());
        for(int i=0;i<ques.size();i++){
            if(sm.getSelectedItem().equals("Exercice 1")){
                screen1=1;
                screen2=0;
                screen3=0;
                screen4=0;
                imageView.setVisible(false);
                Question3.setVisible(false);
                Question2.setVisible(false);
                question1.setVisible(true);
                Question4.setVisible(false);
                LoadTable1.setVisible(false);
                LoadTable2.setVisible(false);
                LoadOperator1.setVisible(false);
                LoadOperator2.setVisible(false);
                LoadTable.setVisible(true);
                LoadOperator.setVisible(true);
                SetNewAnswers.setVisible(true);
                GenerateAnswers.setVisible(true);
                GenerateAnswers.setText("Result");
                GenerateSubresult.setVisible(false);
                ErrorPane.getChildren().clear();
            }
            if(sm.getSelectedItem().equals("Exercice 2")){
                screen1=0;
                screen2=1;
                screen3=0;
                screen4=0;
                imageView.setVisible(false);
                question1.setVisible(false);
                Question3.setVisible(false);
                Question2.setVisible(true);
                Question4.setVisible(false);
                fileQ2.setVisible(false);
                constraintQ2.setVisible(false);
                LoadTable1.setVisible(true);
                LoadTable2.setVisible(true);
                LoadTable2.setText("Load Table 2");
                LoadTable.setVisible(false);
                LoadOperator1.setVisible(false);
                LoadOperator2.setVisible(false);
                LoadOperator.setVisible(true);
                SetNewAnswers.setVisible(true);
                GenerateAnswers.setVisible(true);
                GenerateAnswers.setText("Result");
                GenerateSubresult.setVisible(false);
                ErrorPane.getChildren().clear();
            }
            if(sm.getSelectedItem().equals("Exercice 3")){
                screen1=0;
                screen2=0;
                screen3=1;
                screen4=0;
                imageView.setVisible(false);
                question1.setVisible(false);
                Question2.setVisible(false);
                Question3.setVisible(true);
                Question4.setVisible(false);
                LoadTable1.setVisible(true);
                LoadTable2.setVisible(true);
                LoadTable2.setText("Load Table 2");
                LoadTable.setVisible(false);
                LoadOperator1.setVisible(false);
                LoadOperator2.setVisible(false);
                LoadOperator.setVisible(true);
                SetNewAnswers.setVisible(true);
                GenerateAnswers.setVisible(true);
                GenerateAnswers.setText("Result");
                GenerateSubresult.setVisible(false);
                ErrorPane.getChildren().clear();
            }
            if(sm.getSelectedItem().equals("Exercice 4")){
                screen1=0;
                screen2=0;
                screen3=0;
                screen4=1;
                imageView.setVisible(false);
                question1.setVisible(false);
                Question2.setVisible(false);
                Question3.setVisible(false);
                Question4.setVisible(true);
                LoadTable1.setVisible(true);
                LoadTable2.setVisible(true);
                LoadTable2.setText("Load Table 3");
                LoadTable.setVisible(false);
                LoadOperator1.setVisible(true);
                LoadOperator2.setVisible(true);
                LoadOperator.setVisible(false);
                SetNewAnswers.setVisible(true);
                GenerateSubresult.setVisible(true);
                GenerateAnswers.setVisible(true);
                GenerateAnswers.setText("End Result");
                ErrorPane.getChildren().clear();
            }
        }
    }

    public void LoadTableBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        try {
            SelectionModel<String> sm = RelationList.getSelectionModel();
            RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            if (actionEvent.getSource() == LoadTable) {
                for(int i=0;i<RelationName.size();i++){
                    if(sm.getSelectedItem().equals(RelationName.get(i))){
                        ErrorPane.getChildren().clear();
                        count1=1;
                        Table=RelationName.get(i);
                        TableView tab = Werkzeug.DisplayTable("*",RelationName.get(i),"");
                        tab.setMaxHeight(153);
                        tab.setMaxWidth(200);
                        anchorMainTable1.getChildren().add(tab);

                        ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ RelationName.get(i) );
                        for(int k=1;k<=rs.getMetaData().getColumnCount();k++){ //result.get(6)    Header1=//result.get(6)
                            ConstraintList.getItems().add(rs.getMetaData().getColumnName(k));
                        }
                    }
                }
            }
        }catch (NullPointerException  e) {
            ErrorPane.getChildren().clear();
            Label label = new Label("Please select a relation first");
            ErrorPane.getChildren().add(label);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            ErrorPane.getChildren().clear();
            Label label = new Label("Maybe the relation doesn't exist");
            ErrorPane.getChildren().add(label);
            e.printStackTrace();
        }
    }


    public void LoadOperatorBtn(ActionEvent actionEvent) {
        SelectionModel<String> sm = OperatorList.getSelectionModel();
        OperatorList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if (actionEvent.getSource() == LoadOperator) {
            for(int i=0;i<OperatorList.getItems().size();i++) {
                try {
                if (sm.getSelectedItem().equals(OperatorList.getItems().get(i))) {
                    ErrorPane.getChildren().clear();
                    if ((OperatorList.getItems().get(i)) == ("Selection (σ)")) {
                        ErrorPane.getChildren().clear();
                        op = 1;
                        if (screen1 == 1) {
                            labelOperator.setText("σ");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(true);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label = new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label = new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if ((OperatorList.getItems().get(i)) == ("Projection (π)")) {
                        ErrorPane.getChildren().clear();
                        op = 2;
                        if (screen1 == 1) {
                            labelOperator.setText("π");
                            FilQuestion1.setVisible(true);
                            ConstraintList.setVisible(true);
                            TextInputQuestion1.setVisible(true);
                            TextInputQuestion1.setVisible(false);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label = new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label = new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if ((OperatorList.getItems().get(i)) == ("Union (∪)")) {
                        ErrorPane.getChildren().clear();
                        op = 3;
                        if (screen1 == 1) {
                            labelOperator.setText("Error");

                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }

                        if (screen2 == 1) {
                            LabOperator.setText("∪");

                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("∪");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }

                    }
                    if ((OperatorList.getItems().get(i)) == ("Intersection (⋂)")) {
                        ErrorPane.getChildren().clear();
                        op = 4;
                        if (screen1 == 1) {
                            labelOperator.setText("∩");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("∩");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("∩");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                    }
                    if ((OperatorList.getItems().get(i)) == ("Difference (-)")) {
                        ErrorPane.getChildren().clear();
                        op = 5;
                        if (screen1 == 1) {
                            labelOperator.setText("-");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("-");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("-");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }

                    }
                    if ((OperatorList.getItems().get(i)) == ("Division (÷)")) {
                        ErrorPane.getChildren().clear();
                        op = 6;
                        if (screen1 == 1) {
                            labelOperator.setText("÷");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("÷");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("÷");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }

                    }
                    if ((OperatorList.getItems().get(i)) == ("Cartesian Product (X)")) {
                        ErrorPane.getChildren().clear();
                        op = 7;
                        if (screen1 == 1) {
                            labelOperator.setText("X");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("X");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("X");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                    }

                    if ((OperatorList.getItems().get(i)) == ("Natural Join (⋈)")) {
                        ErrorPane.getChildren().clear();
                        op = 8;
                        if (screen1 == 1) {
                            labelOperator.setText("⋈");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("⋈");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("⋈");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                    }
                    if ((OperatorList.getItems().get(i)) == ("Left Outer Join (⟕) ")) {
                        ErrorPane.getChildren().clear();
                        op = 9;
                        if (screen1 == 1) {
                            labelOperator.setText("⟕");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            LabOperator.setText("⟕");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("⟕");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                    }
                    if ((OperatorList.getItems().get(i)) == ("Right Outer Join (⟖)")) {
                        ErrorPane.getChildren().clear();
                        op = 10;
                        if (screen1 == 1) {
                            labelOperator.setText("⟖");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label = new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if (screen2 == 1) {
                            ErrorPane.getChildren().clear();
                            LabOperator.setText("⟖");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if (screen3 == 1) {
                            LabelOperatorQ3.setText("⟖");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                    }


                }
                }catch (NullPointerException e){
                    ErrorPane.getChildren().clear();
                    Label label = new Label("Please select an operator first");
                    ErrorPane.getChildren().add(label);
                }
    }
    }
    }

    public void HelpBtn(ActionEvent actionEvent) throws IOException {
        ErrorPane.getChildren().clear();
        Label label=new Label("this button show nothing unless you open one Exercice");
        ErrorPane.getChildren().add(label);

        if(screen1==1) {
            Stage stage = new Stage();
            stage.setTitle("Help");
            AnchorPane root2 = FXMLLoader.load(getClass().getResource("/Views/HelpQuestion1.fxml"));
            Scene scene2 = new Scene(root2);
            stage.setScene(scene2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        if(screen2==1) {
            Stage stage = new Stage();
            stage.setTitle("Help");
            AnchorPane root2 = FXMLLoader.load(getClass().getResource("/Views/HelpQuestion2.fxml"));
            Scene scene2 = new Scene(root2);
            stage.setScene(scene2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        if(screen3==1) {
            Stage stage = new Stage();
            stage.setTitle("Help");
            AnchorPane root2 = FXMLLoader.load(getClass().getResource("/Views/HelpQuestion3.fxml"));
            Scene scene2 = new Scene(root2);
            stage.setScene(scene2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        if(screen4==1) {
            Stage stage = new Stage();
            stage.setTitle("Help");
            AnchorPane root2 = FXMLLoader.load(getClass().getResource("/Views/HelpQuestion4.fxml"));
            Scene scene2 = new Scene(root2);
            stage.setScene(scene2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }


    public void LoadTable1Btn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        if(screen2==1) {
            try {
                SelectionModel<String> sm = RelationList.getSelectionModel();
                RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                if (actionEvent.getSource() == LoadTable1) {
                    for (int i = 0; i < RelationName.size(); i++){
                        if(sm.getSelectedItem().equals(RelationName.get(i))){
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab1.getChildren().add(tab);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(screen3==1){
            try {
                SelectionModel<String> sm = RelationList.getSelectionModel();
                RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                if (actionEvent.getSource() == LoadTable1) {
                    for (int i = 0; i < RelationName.size(); i++){
                        if(sm.getSelectedItem().equals(RelationName.get(i))){
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab1Q3.getChildren().add(tab);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(screen4==1){
            try {
                SelectionModel<String> sm = RelationList.getSelectionModel();
                RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                if (actionEvent.getSource() == LoadTable1) {
                    for (int i = 0; i < RelationName.size(); i++){
                        if(sm.getSelectedItem().equals(RelationName.get(i))){
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab1Q4.getChildren().add(tab);
                            ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ RelationName.get(i) );
                            for(int k=1;k<=rs.getMetaData().getColumnCount();k++){ //result.get(6)    Header1=//result.get(6)
                                ConstraintListOperator1Q4.getItems().add(rs.getMetaData().getColumnName(k));
                            }
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void LoadTable2Btn(ActionEvent actionEvent) {
        if(screen2==1){
            SelectionModel<String> sm = RelationList.getSelectionModel();
            RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            try {
                if (actionEvent.getSource() == LoadTable2) {
                    for (int i = 0; i < RelationName.size(); i++) {
                        if (sm.getSelectedItem().equals(RelationName.get(i))) {
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab2.getChildren().add(tab);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(screen3==1){
            SelectionModel<String> sm = RelationList.getSelectionModel();
            RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            try {
                if (actionEvent.getSource() == LoadTable2) {
                    for (int i = 0; i < RelationName.size(); i++) {
                        if (sm.getSelectedItem().equals(RelationName.get(i))) {
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab2Q3.getChildren().add(tab);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(screen4==1){
            SelectionModel<String> sm = RelationList.getSelectionModel();
            RelationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            try {
                if (actionEvent.getSource() == LoadTable2) {
                    for (int i = 0; i < RelationName.size(); i++) {
                        if (sm.getSelectedItem().equals(RelationName.get(i))){
                            TabList.add(RelationName.get(i));
                            TableView tab = Werkzeug.DisplayTable("*", RelationName.get(i), "");
                            tab.setMaxHeight(153);
                            tab.setMaxWidth(200);
                            PaneTab3Q4.getChildren().add(tab);
                        }
                    }
                }
            } catch (NullPointerException e) {
                Label label = new Label("You have to select a relation first");
                ErrorPane.getChildren().add(label);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void LoadOperator1Btn(ActionEvent actionEvent) {
        SelectionModel<String> sm = OperatorList.getSelectionModel();
        OperatorList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if (actionEvent.getSource() == LoadOperator1) {
            for(int i=0;i<OperatorList.getItems().size();i++){
                if(sm.getSelectedItem().equals(OperatorList.getItems().get(i))) {
                    if((OperatorList.getItems().get(i))==("Selection (σ)")){
                        ErrorPane.getChildren().clear();
                        op=1;
                        if(screen1==1){
                            labelOperator.setText("σ");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(true);
                        }
                        if(screen2==1){

                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen3==1){
                            LabelOperatorQ3.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen4==1){
                            LabelOperator1Q4.setText("σ");
                            ConstraintListOperator1Q4.setVisible(false);
                            ConstraintOperator1Q4.setVisible(true);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Projection (π)")){
                        ErrorPane.getChildren().clear();
                        op=2;
                        if(screen1==1){
                            labelOperator.setText("π");
                            FilQuestion1.setVisible(true);
                            ConstraintList.setVisible(true);
                            TextInputQuestion1.setVisible(true);
                            TextInputQuestion1.setVisible(false);
                        }
                        if(screen2==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen3==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen4==1){
                            LabelOperator1Q4.setText("π");
                            ConstraintListOperator1Q4.setVisible(false);
                            ConstraintOperator1Q4.setVisible(true);
                            ConstraintListOperator1Q4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                        }
                    }
                    /////
                    if((OperatorList.getItems().get(i))==("Union (∪)")){
                        ErrorPane.getChildren().clear();
                        op=3;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }

                    }
                    if((OperatorList.getItems().get(i))==("Intersection (∩)")){
                        ErrorPane.getChildren().clear();
                        op=4;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Difference (-)")){
                        ErrorPane.getChildren().clear();
                        op=5;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }

                    }
                    if((OperatorList.getItems().get(i))==("Division (÷)")){
                        ErrorPane.getChildren().clear();
                        op=6;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Cartesian Product (X)")){
                        ErrorPane.getChildren().clear();
                        op=7;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Natural Join (⋈)")){
                        ErrorPane.getChildren().clear();
                        op=8;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Left Outer Join (⟕) ")){
                        ErrorPane.getChildren().clear();
                        op=9;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Right Outer Join (⟖)")){
                        ErrorPane.getChildren().clear();
                        op=10;
                        if(screen4==1){
                            LabelOperator1Q4.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("a Unary operator is expect here!");
                            ErrorPane.getChildren().add(label);
                        }
                    }

                }
            }
        }
    }

    public void LoadOperator2Btn(ActionEvent actionEvent) {
        SelectionModel<String> sm = OperatorList.getSelectionModel();
        OperatorList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        if (actionEvent.getSource() == LoadOperator2) {
            for(int i=0;i<OperatorList.getItems().size();i++){
                if(sm.getSelectedItem().equals(OperatorList.getItems().get(i))) {
                    if((OperatorList.getItems().get(i))==("Selection (σ)")){
                        ErrorPane.getChildren().clear();
                        op=1;
                        if(screen1==1){
                            labelOperator.setText("σ");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(true);
                        }
                        if(screen2==1){

                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen3==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen4==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Projection (π)")){
                        ErrorPane.getChildren().clear();
                        op=2;
                        if(screen1==1){
                            labelOperator.setText("π");
                            FilQuestion1.setVisible(true);
                            ConstraintList.setVisible(true);
                            TextInputQuestion1.setVisible(true);
                            TextInputQuestion1.setVisible(false);
                        }
                        if(screen2==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen3==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Projection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen4==1){
                            LabOperator.setText("Error");
                            ErrorPane.getChildren().clear();
                            Label label=new Label("Selection is a unary operator and can't be apply in this exercice.See exercice 1 for unary operator");
                            ErrorPane.getChildren().add(label);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Union (∪)")){
                        ErrorPane.getChildren().clear();
                        op=3;
                        if(screen1==1){
                            labelOperator.setText("∪");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }

                        if(screen2==1){
                            LabOperator.setText("∪");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if(screen3==1){
                            LabOperator.setText("∪");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){

                            LabelOperator2Q4.setText("∪");
                            FileQ4.setVisible(false);
                            ConstraintOperator2Q4.setVisible(false);
                        }

                    }
                    if((OperatorList.getItems().get(i))==("Intersection (∩)")){
                        ErrorPane.getChildren().clear();
                        op=4;
                        if(screen1==1){
                            labelOperator.setText("∩");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("∩");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if(screen3==1){
                            LabOperator.setText("∩");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("∩");
                            FileQ4.setVisible(false);
                            ConstraintOperator2Q4.setVisible(false);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Difference (-)")){
                        ErrorPane.getChildren().clear();
                        op=5;
                        if(screen1==1){
                            labelOperator.setText("-");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("-");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if(screen3==1){
                            LabOperator.setText("-");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("-");
                            FileQ4.setVisible(false);
                            ConstraintOperator2Q4.setVisible(false);
                        }


                    }
                    if((OperatorList.getItems().get(i))==("Division (÷)")){
                        ErrorPane.getChildren().clear();
                        op=6;
                        if(screen1==1){
                            labelOperator.setText("÷");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("÷");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if(screen3==1){
                            LabOperator.setText("÷");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("÷");
                            FileQ4.setVisible(false);
                            ConstraintOperator2Q4.setVisible(false);
                        }

                    }
                    if((OperatorList.getItems().get(i))==("Cartesian Product (X)")){
                        ErrorPane.getChildren().clear();
                        op=7;
                        if(screen1==1){
                            labelOperator.setText("X");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("X");
                            fileQ2.setVisible(false);
                            constraintQ2.setVisible(false);
                        }
                        if(screen3==1){
                            LabOperator.setText("X");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("X");
                            FileQ4.setVisible(false);
                            ConstraintOperator2Q4.setVisible(false);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Natural Join (⋈)")){
                        ErrorPane.getChildren().clear();
                        op=8;
                        if(screen1==1){
                            labelOperator.setText("⋈");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("⋈");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if(screen3==1){
                            LabOperator.setText("⋈");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("⋈");
                            FileQ4.setVisible(true);
                            ConstraintOperator2Q4.setVisible(true);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Left Outer Join (⟕) ")){
                        ErrorPane.getChildren().clear();
                        op=9;
                        if(screen1==1){
                            labelOperator.setText("⟕");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            LabOperator.setText("⟕");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if(screen3==1){
                            LabOperator.setText("⟕");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("⟕");
                            FileQ4.setVisible(true);
                            ConstraintOperator2Q4.setVisible(true);
                        }
                    }
                    if((OperatorList.getItems().get(i))==("Right Outer Join (⟖)")){
                        ErrorPane.getChildren().clear();
                        op=10;
                        if(screen1==1){
                            labelOperator.setText("⟖");
                            FilQuestion1.setVisible(false);
                            ConstraintList.setVisible(false);
                            TextInputQuestion1.setVisible(false);
                            ErrorPane.getChildren().clear();
                            Label label=new Label("impossible to perform a binary Operation just with one table");
                            ErrorPane.getChildren().add(label);
                        }
                        if(screen2==1){
                            ErrorPane.getChildren().clear();
                            LabOperator.setText("⟖");
                            fileQ2.setVisible(true);
                            constraintQ2.setVisible(true);
                        }
                        if(screen3==1){
                            LabOperator.setText("⟖");
                            fileQ3.setVisible(false);
                            ConstraintQ3.setVisible(false);
                        }
                        if(screen4==1){
                            LabelOperator2Q4.setText("⟖");
                            FileQ4.setVisible(true);
                            ConstraintOperator2Q4.setVisible(true);
                        }
                    }
                }
            }
        }
    }

    public void GenerateSubresultBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        RandomClass obj=new RandomClass();

        if(op==1) {
            op1=1;
        List<String>val=new ArrayList<>();
            if(screen4==1){

                try {
                    ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ TabList.get(0));
                    String ColumnName=rs.getMetaData().getColumnName(obj.getrandomColumn(rs.getMetaData().getColumnCount()));
                    rs= connection.createStatement().executeQuery("select" +" "+ColumnName+" "+ "from"+" "+ TabList.get(0));
                    while ( rs.next()){
                        val.add(rs.getString(obj.getrandomColumn(rs.getMetaData().getColumnCount())));
                    }
                    connection.close();

                    TableView tab = Werkzeug.DisplayTable("*", TabList.get(0), "where" + " " + ConstraintOperator1Q4.getText());
                    tab.setMaxWidth(147);
                    tab.setMaxHeight(153);
                    SubResultTab1Q4.getChildren().add(tab);
                    TableView tab1 = Werkzeug.DisplayTable("*", TabList.get(0), "where" + " " + ConstraintOperator1Q4.getText());
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(153);
                    PaneTab2Q4.getChildren().add(tab1);
                }catch (SQLException | ClassNotFoundException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
        }
        if(op==2) {
            op1=2;
            if(screen4==1){
                try {
                    System.out.println("The constraint is:"+constraint);
                    TableView tab1 = Werkzeug.DisplayTable(ConstraintOperator1Q4.getText(), TabList.get(0), "");
                    tab1.setMaxWidth(200);
                    tab1.setMaxHeight(153);
                    PaneTab2Q4.getChildren().add(tab1);
                    TableView tab = Werkzeug.DisplayTable(ConstraintOperator1Q4.getText(), TabList.get(0), "");
                    tab.setMaxWidth(147);
                    tab.setMaxHeight(153);
                    SubResultTab1Q4.getChildren().add(tab);

                }catch (SQLException e){
                    ErrorPane.getChildren().clear();
                    Label label=new Label("there ist a syntax Fehler bei der abfrage der Datenbank und genau: "+e.getMessage());
                    ErrorPane.getChildren().add(label);
                }
            }
        }
    }

    public void RefreshBtn(ActionEvent actionEvent) {
        if(screen1==1){
            anchorMainTable1.getChildren().clear();
            labelOperator.setText("Operator");
            anchorRelation1.getChildren().clear();
            anchorRelation2.getChildren().clear();
            anchorRelation3.getChildren().clear();
            anchorRelation4.getChildren().clear();
            ConstraintList.getItems().clear();
            Question.setText("Enter the Question here!");
            Answer1.setText("Answer1");
            Answer2.setText("Answer2");
            Answer3.setText("Answer3");
            Answer4.setText("Answer4");

        }
        if(screen2==1){
            PaneTab1.getChildren().clear();
            PaneTab2.getChildren().clear();
            LabOperator.setText("Operator");
            PaneRessultTab1.getChildren().clear();
            PaneRessultTab2.getChildren().clear();
            Question.setText("Enter the Question here!");
            Answer1.setText("Answer1");
            Answer2.setText("Answer2");
            Answer3.setText("Answer3");
            Answer4.setText("Answer4");
        }
        if(screen3==1){
            PaneTab1Q3.getChildren().clear();
            PaneTab2Q3.getChildren().clear();
            LabelOperatorQ3.setText("Operator");
            PaneResultQ3.getChildren().clear();
            Question.setText("Enter the Question here!");
            Answer1.setText("Answer1");
            Answer2.setText("Answer2");
            Answer3.setText("Answer3");
            Answer4.setText("Answer4");

        }
        if(screen4==1){
            PaneTab1Q4.getChildren().clear();
            PaneTab2Q4.getChildren().clear();
            PaneTab3Q4.getChildren().clear();
            ResultTab4Q4.getChildren().clear();
            ResultTab3Q4.getChildren().clear();
            ResultTab2Q4.getChildren().clear();
            SubResultTab1Q4.getChildren().clear();
            LabelOperator1Q4.setText("Operator");
            LabelOperator2Q4.setText("Operator");
            Question.setText("Enter the Question here!");
            Answer1.setText("Answer1");
            Answer2.setText("Answer2");
            Answer3.setText("Answer3");
            Answer4.setText("Answer4");


        }
    }

    public void refresh(MouseDragEvent mouseDragEvent) {
    }
}








