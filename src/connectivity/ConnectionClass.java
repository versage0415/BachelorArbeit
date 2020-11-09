package connectivity;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String DbName="bachelorarbeit";
        String UserName="root";
        String Password="";


//
//        //Connection connection= DriverManager.getConnection("http://localhost/phpmyadmin/db_structure.php?server=1&db=bachelorarbeit&table=EndTable1" );
        Class.forName("org.h2.Driver");
        //jdbc:h2:tcp://<server>[:<port>]/[<path>]<databaseName>
         //connection= DriverManager.getConnection("jdbc:h2:./lib/bachelorarbeit;create=true");
        connection= DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Bachelorarbeit");
        JOptionPane.showMessageDialog(null,"Connection Established");

        return connection;
    }
}
