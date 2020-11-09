package Model;

import Controllers.RandomClass;
import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AnswerClass {
    QuestionClass fragen=new QuestionClass();
    List<String> TableName=new ArrayList<>();
    List<String> selec =new ArrayList<>();
    List<String> selection2=new ArrayList<>();
    public List<String> selection = new ArrayList<>();
    public List<String> union = new ArrayList<>();
    public List<String> intersection = new ArrayList<>();
    public List<String> Differenz = new ArrayList<>();
    public List<String> Divion = new ArrayList<>();
    public List<String> KartesicheProdukt = new ArrayList<>();
    public List<String> NaturalJoin = new ArrayList<>();
    public List<String> LeftOutherJoin = new ArrayList<>();
    public List<String> RightOutherJoin = new ArrayList<>();
    public List<String> projection = new ArrayList<>();
    public List<String> resultat = new ArrayList<>();
    public List<String> box = new ArrayList<>();

    public List<String> getAnswer(String ques) throws SQLException, ClassNotFoundException {
        QuestionClass question=new QuestionClass();
        RandomClass obj=new RandomClass();
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
            if(rs.getMetaData().getColumnCount()==1){
                ObservableList<Tabelle1> Donne = FXCollections.observableArrayList();

                List<String> InhaltTab1=new ArrayList<>();
                while ( rs.next()){

                    InhaltTab1.add(rs.getString(1));

                }
                String Bedingung=obj.getRandomElement(InhaltTab1);
                String ColName=rs.getMetaData().getColumnName(1);

                if(ques== "Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung) {
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;
                }

                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==2){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==3){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==4){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==5){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==6){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==7){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }
            if(rs.getMetaData().getColumnCount()==8){
                ObservableList<Tabelle2> Donne = FXCollections.observableArrayList();
                List<String> InhaltTab=new ArrayList<>();
                int val2=obj.getrandomColumn(2);
                while ( rs.next()){
                    Donne.add(new Tabelle2(rs.getString(1),rs.getString(2)));
                    System.out.println("the header Name1 of the table are:"+rs.getString(1));
                    System.out.println("the header Name2 of the table are:"+rs.getString(2));
                    InhaltTab.add(rs.getString(val2));
                }
                String Bedingung=obj.getRandomElement(InhaltTab);
                String ColName=rs.getMetaData().getColumnName(val2);
                String project="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName;

                if(ques=="Fürhren sie eine Selection auf"+" "+ TableName.get(j)+" "+"mit der Bedingung:"+ColName +"="+ Bedingung){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + Bedingung);
                    return list;

                }
                if(ques=="Führen sie eine Projektion auf"+" "+ TableName.get(j) +" "+" nach"+" "+ ColName){
                    List<String> list=new ArrayList<>();
                    list.add("Das Ergebnis bezieht sich auf Zeile");
                    list.add("Das Ergebnis bezieht sich auf Spalte");
                    list.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
                    list.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
                    return list;

                }
                //    Question.setText(ques);

            }

        }
        String Selection1=obj.getRandomElement(TableName);
        String Selection2=obj.getRandomElement(TableName);

        ResultSet rs2= connection.createStatement().executeQuery("select * from"+" "+ Selection1 );
        ResultSet rs3= connection.createStatement().executeQuery("select * from"+" "+ Selection2 );
        int val1=obj.getrandomColumn(rs2.getMetaData().getColumnCount());
        int val2=obj.getrandomColumn(rs3.getMetaData().getColumnCount());
        if(ques=="Führen sie eine Union zwischen"+" "+Selection1+" "+"und"+" "+Selection2){
            List <String> list=new ArrayList<>();
           // list.add("Die Tabelle Lösung wäre die Summe alle Spalten der Beide Tabelle");
            list.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit beinhaltung der doppelte Datensätze");
            list.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit beinhaltung der doppelte Datensätze");
            list.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit Entfernung der doppelte Datensätze");
            list.add("Die Tabelle Lösung wäre die Summe alle Spalte der Beide Tabelle mit Entfernung der doppelte Datensätze");
            return list;
        }
        if(ques=="Wie viel Spalten hat das Ergebnis der Union auf"+" "+Selection1+" "+"und"+" "+Selection2){
            List <String> list=new ArrayList<>();
            list.add("die Richtige Lösung ist:"+rs2.getMetaData().getColumnCount());
            list.add("die Richtige Lösung ist:"+rs2.getMetaData().getColumnCount()+1);
            list.add("die Richtige Lösung ist:"+(rs2.getMetaData().getColumnCount()-1));
            list.add("die Richtige Lösung ist:"+rs3.getMetaData().getColumnCount()+1);
            return list;
        }
        if(ques=="Wie viel Zeile hat das Ergebnis der Union auf"+" "+Selection1+" "+"und"+" "+Selection2){
            List <String> list=new ArrayList<>();
            int count=0;
            while (rs2.getString(1)!=null){
                count++;
            }
            list.add("die Richtige Lösung ist:"+count+1);
            list.add("die Richtige Lösung ist:"+" "+count);
            list.add("die Richtige Lösung ist:"+" " +(count-1));
            list.add("die Richtige Lösung ist:"+rs3.getMetaData().getColumnCount()+1);
            list.add("die Richtige Lösung ist:"+" " +(count+2));
            return list;

        }
        if(ques=="Führen sie eine Intersektion zwischen"+" "+Selection1+" "+"und"+" "+Selection2){
            List <String> list=new ArrayList<>();
            list.add("Die Tabelle lösung bezieht sich nur auf Spalten");
            list.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
            list.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
            list.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
            return list;

        }
        return selec;
    }



}
