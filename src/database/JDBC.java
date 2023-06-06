package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is the JDBC (Java Database Connectivity) class.
 * This class is for connecting from the IDE to the database.
 */
public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection = null;  // Connection Interface
    private static PreparedStatement preparedStatement;

    /**
     * This is the make connection method. This makes a connection between the IDE and the database.
     */
    public static void makeConnection() {
        try {
          Class.forName(driver); // Locate Driver
          //password = Details.getPassword(); // Assign password
          connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
          System.out.println("Connection successful!");
        }
        catch(ClassNotFoundException e) {
          System.out.println("Error:" + e.getMessage());
        }
        catch(SQLException e) {
          System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * This is the get connection method.
     * @return the connection to the database
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * This is the close connection method. This closes a connection between the IDE and the database.
     */
    public static void closeConnection() {
        try {
             connection.close();
             System.out.println("Connection closed!");
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
    }

    /**
     * This is make prepared statement method. This makes a prepared statement when the database is connected.
     * @param sqlStatement the sql statement to be queried
     * @param conn the connection to the database
     * @throws SQLException if the database not found
     */
    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
       if (conn != null)
           preparedStatement = conn.prepareStatement(sqlStatement);
       else
           System.out.println("Prepared Statement Creation Failed!");
    }

    /**
     * This is get prepared statement method.
     * @return the prepared statement if not null
     * @throws SQLException if the database not found
     */
    public static PreparedStatement getPreparedStatement() throws SQLException {
       if (preparedStatement != null)
           return preparedStatement;
       else System.out.println("Null reference to Prepared Statement");
       return null;
    }
}