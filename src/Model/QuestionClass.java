package Model;

import Controllers.RandomClass;
import connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionClass {
    public List<String> TableForTwoOperator =new ArrayList<>();
    String TabForOneOperator;
    public List<String> inthalt =new ArrayList<>();
    public List<String> inthalt1 =new ArrayList<>();
    public List<String> inthalt2 =new ArrayList<>();
    public List<String> TableName =new ArrayList<>();
    public List<String> selection = new ArrayList<>();
    public List<String> selectionA = new ArrayList<>();
    public List<String> union1 = new ArrayList<>();
    public List<String> union2 = new ArrayList<>();
    public List<String> union3 = new ArrayList<>();
    public List<String> intersection1 = new ArrayList<>();
    public List<String> intersection2 = new ArrayList<>();
    public List<String> intersection3 = new ArrayList<>();
    public List<String> Differenz1 = new ArrayList<>();
    public List<String> Differenz2 = new ArrayList<>();
    public List<String> Differenz3 = new ArrayList<>();
    public List<String> Divion1 = new ArrayList<>();
    public List<String> Divion2 = new ArrayList<>();
    public List<String> Divion3 = new ArrayList<>();
    public List<String> KartesicheProdukt1 = new ArrayList<>();
    public List<String> KartesicheProdukt2 = new ArrayList<>();
    public List<String> KartesicheProdukt3 = new ArrayList<>();
    public List<String> NaturalJoin1 = new ArrayList<>();
    public List<String> NaturalJoin2 = new ArrayList<>();
    public List<String> NaturalJoin3 = new ArrayList<>();
    public List<String> LeftOutherJoin1 = new ArrayList<>();
    public List<String> LeftOutherJoin2 = new ArrayList<>();
    public List<String> LeftOutherJoin3 = new ArrayList<>();
    public List<String> RightOutherJoin1 = new ArrayList<>();
    public List<String> RightOutherJoin2 = new ArrayList<>();
    public List<String> RightOutherJoin3 = new ArrayList<>();
    public List<String> projection = new ArrayList<>();
    public List<String> projectionA = new ArrayList<>();


  /*
    public String  getQuestion(String question) throws SQLException, ClassNotFoundException {
       RandomClass obj=new RandomClass();
       EinOperatorQuestion();
       ZweiOperatorQuestion();

        if(question=="1"){
            return obj.getRandomElement(selection);
        }
        if(question=="2"){
            return obj.getRandomElement(projection);
        }
        if(question=="3"){
            return obj.getRandomElement(union);

        }
        if(question=="4"){
            return obj.getRandomElement(intersection);

        }
        if(question=="5"){
            return obj.getRandomElement(Differenz);

        }
        if(question=="6"){
            return obj.getRandomElement(Divion);

        }
        if(question=="7"){
            return obj.getRandomElement(KartesicheProdukt);

        }
        if(question=="8"){
            return obj.getRandomElement(NaturalJoin);

        }
        if(question=="9"){
            return obj.getRandomElement(LeftOutherJoin);

        }
        if(question=="10"){
            return obj.getRandomElement(RightOutherJoin);

        }
        return question;

    }
*/
    public void getTable() throws SQLException, ClassNotFoundException {
        RandomClass obj=new RandomClass();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        DatabaseMetaData md= connection.getMetaData();
        ResultSet rs1= md.getTables("bachelorarbeit",null,"%",null);
        while (rs1.next()){
            TableName.add(rs1.getString(3));
        }
        if(TableName.size()==1){
        TabForOneOperator=TableName.get(0);}
        if(TableName.size()>1){
        TabForOneOperator=obj.getRandomElement(TableName);
        String tb=obj.getRandomElement(TableName);
        TableForTwoOperator.add(tb);
        TableName.remove(tb);
        TableForTwoOperator.add(obj.getRandomElement(TableName));}


    }
    public void EinOperatorQuestion() throws SQLException,ClassNotFoundException {
        getTable();
        RandomClass obj=new RandomClass();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ TabForOneOperator);
        while ( rs.next()){
            inthalt.add(rs.getString(obj.getrandomColumn(rs.getMetaData().getColumnCount())));
        }
        String ColName=rs.getMetaData().getColumnName(obj.getrandomColumn(rs.getMetaData().getColumnCount()));
        selection.add("Fürhren sie eine Selection auf"+" "+ TabForOneOperator+" "+"mit der Bedingung:"+ColName +"="+ obj.getRandomElement(inthalt));
        selection.add("Das Ergebnis bezieht sich auf Zeile");
        selection.add("Das Ergebnis bezieht sich auf Spalte");
        selection.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
        selection.add("Das Ergebnis bezieht sich auf Zeile wo" + " " + ColName + "=" + obj.getRandomElement(inthalt));

        if(rs.getMetaData().getColumnCount()!=1){
            projection.add("Führen sie eine Projektion auf"+" "+ TabForOneOperator +" "+" nach"+" "+ ColName);
            projection.add("Das Ergebnis bezieht sich auf Zeile");
            projection.add("Das Ergebnis bezieht sich auf Spalte");
            projection.add("Das Ergebnis bezieht sich auf Zeile und Spalte");
            projection.add("Das Ergebnis bezieht sich auf Spalte mit Header Name" + " " + ColName  );
        }

    }
    public void ZweiOperatorQuestion() throws SQLException,ClassNotFoundException{
        getTable();
        RandomClass obj=new RandomClass();
        ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=connectionClass.getConnection();
        ResultSet rs= connection.createStatement().executeQuery("select * from"+" "+ TableForTwoOperator.get(0) );
        ResultSet rs1= connection.createStatement().executeQuery("select * from"+" "+ TableForTwoOperator.get(1) );
        int val1=obj.getrandomColumn(rs.getMetaData().getColumnCount());
        int val2=obj.getrandomColumn(rs1.getMetaData().getColumnCount());
        union1.add("Führen sie eine Union zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        union1.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit beinhaltung der doppelte Datensätze");
        union1.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit beinhaltung der doppelte Datensätze");
        union1.add("Die Tabelle Lösung wäre die Summe alle Zeile der Beide Tabelle mit Entfernung der doppelte Datensätze");
        union1.add("Die Tabelle Lösung wäre die Summe alle Spalte der Beide Tabelle mit Entfernung der doppelte Datensätze");

        union2.add("Wie viel Spalten hat das Ergebnis der Union auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        union2.add("die Richtige Lösung ist:"+rs.getMetaData().getColumnCount());
        union2.add("die Richtige Lösung ist:"+rs.getMetaData().getColumnCount()+1);
        union2.add("die Richtige Lösung ist:"+(rs.getMetaData().getColumnCount()-1));
        union2.add("die Richtige Lösung ist:"+rs1.getMetaData().getColumnCount()+1);
        union3.add("Wie viel Zeile hat das Ergebnis der Union auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));

        intersection1.add("Führen sie eine Intersektion zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        intersection1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        intersection1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        intersection1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        intersection1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        intersection2.add("Wie viel Spalte hat das Ergebnis der durchschnitt zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        intersection3.add("Wie viel Zeile hat das Ergebnis der durchschnitt zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));

        Differenz1.add("Führen sie eine Differenz zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        Differenz1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        Differenz1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        Differenz1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        Differenz1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        Differenz2.add("Wie viel Zeile hat das Ergebnis der Differenz zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        Differenz3.add("Wie viel Spalte hat das Ergebnis der Differenz zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));

        Divion1.add("Führen sie eine Division zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        Divion1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        Divion1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        Divion1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        Divion1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        Divion2.add("Wie viel Spalte hat das Ergebnis der Division zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        Divion3.add("Wie viel Zeile hat das Ergebnis der Division zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));

        KartesicheProdukt1.add("Führen sie eine Kartesiche Produkt zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        KartesicheProdukt1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        KartesicheProdukt1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        KartesicheProdukt1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        KartesicheProdukt1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        KartesicheProdukt2.add("Wie viel Zeile hat das Ergebnis der Kartesische Produkt zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        KartesicheProdukt3.add("Wie viel Spalte hat das Ergebnis der Kartesische Produkt zwischen:"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));

        NaturalJoin1.add("Führen sie eine Natürlicher Verbund zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        NaturalJoin1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        NaturalJoin1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        NaturalJoin1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        NaturalJoin1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        NaturalJoin2.add("Wie viel Spalte hat das Ergebnis der  natürliche Verbund auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));
        NaturalJoin3.add("Wie viel Zeile hat das Ergebnis der  natürliche Verbund auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1));


        LeftOutherJoin1.add("Führen sie eine Left outher join zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));
        LeftOutherJoin1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        LeftOutherJoin1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        LeftOutherJoin1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        LeftOutherJoin1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        LeftOutherJoin2.add("Wie viel Spalte hat das Ergebnis der Left outher Join auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));
        LeftOutherJoin3.add("Wie viel Zeile hat das Ergebnis der Left outher Join auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));

        RightOutherJoin1.add("Führen sie eine right outher join zwischen"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));
        RightOutherJoin1.add("Die Tabelle lösung bezieht sich nur auf Spalten");
        RightOutherJoin1.add("Die Tabelle lösung bezieht sich nur auf Zeilen");
        RightOutherJoin1.add("Die Tabelle lösung bezieht sich Spalten Zeilen");
        RightOutherJoin1.add("Die Tabelle lösung bezieht sich Spalten1 Zeilen");
        RightOutherJoin2.add("Wie viel Spalte hat das Ergebnis der Right outher Join auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));
        RightOutherJoin3.add("Wie viel Zeile hat das Ergebnis der Right outher Join auf"+" "+TableForTwoOperator.get(0)+" "+"und"+" "+TableForTwoOperator.get(1)+" "+"Mit der Bedingung"+" "+rs.getMetaData().getColumnName(val1)+" "+"="+" "+rs1.getMetaData().getColumnName(val2));



    }
    public List<String>getList(String operator) throws SQLException, ClassNotFoundException {
        RandomClass obj=new RandomClass();
        EinOperatorQuestion();
        ZweiOperatorQuestion();
        if(operator=="1"){
            return selection;
        }
        if(operator=="2"){
            return projection;
        }
        if(operator=="3"){
            int i=obj.getrandomColumn(2);
            return union1;
        }
        if(operator=="4"){
            int i=obj.getrandomColumn(2);
            return intersection1;
        }
        if(operator=="5"){
            int i=obj.getrandomColumn(2);
            return Differenz1;
        }
        if(operator=="6"){
            int i=obj.getrandomColumn(2);
            return Divion1;
        }
        if(operator=="7"){
            int i=obj.getrandomColumn(2);
            return KartesicheProdukt1;
        }
        if(operator=="8"){
            int i=obj.getrandomColumn(2);
            return NaturalJoin1;
        }
        if(operator=="9"){
            int i=obj.getrandomColumn(2);
            return LeftOutherJoin1;
        }
        if(operator=="10"){
            int i=obj.getrandomColumn(2);
            return RightOutherJoin1;
        }
        return null;
    }

}
