package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    Connection connection;
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String DbName="bachelorarbeit";
        String UserName="root";
        String Password="";
       // String JDBC_URL="jdbc:derby:bachelorarbeit;create=true";
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        //Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection("jdbc:derby:lib/bachelorarbeit;create=true");

        //Connection connection= DriverManager.getConnection("http://localhost/phpmyadmin/db_structure.php?server=1&db=bachelorarbeit&table=EndTable1" );
        return connection;
    }
}
