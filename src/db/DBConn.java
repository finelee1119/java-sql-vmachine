package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
    private static Connection dbConn;

    public static Connection getConnection() {
        if (dbConn == null) {
            try {
                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbUrl = "jdbc:mysql://localhost:3306/vm3";
                String dbUser = "vuser";
                String dbPassword = "1111";
                Class.forName(dbDriver);
                dbConn = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return dbConn;
    }

    public static void close() {
        if (dbConn != null) {
            try {
                if (!dbConn.isClosed()) {
                    dbConn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dbConn = null;
        }
    }
}
