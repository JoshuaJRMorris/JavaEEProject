/*
 * Josh Morris 
 * course: GEX
 * This assignment represents my own work and is in accordance with the College Academic Policy
 */
package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jash1
 */

/**
 * Class to return a sql connection
 * @author jash1
 */
public class DatabaseConnection {
    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException{
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        
        String dbName = "ShanMorr";
        String dbUsername = "root";
        String dbPassword = "dev1234";
        
        Class.forName(dbDriver);
        Connection conn = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        
        return conn;
    }
}
