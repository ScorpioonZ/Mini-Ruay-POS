package RuayRuayMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CSMariaDBConn implements AutoCloseable {
    private DBConnectionData mariaDB_Sczfile = new DBConnectionData(
            "jdbc:mariadb://sczfile.online:3306/ruayruay_java?useUnicode=true&characterEncoding=UTF-8", 
            "ruayruay", 
            "ruay1234");

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(
                        mariaDB_Sczfile.serverPath,
                        mariaDB_Sczfile.username,
                        mariaDB_Sczfile.password);
                System.out.println("You made it, take control of your database now!");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL DB JDBC Driver?");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
