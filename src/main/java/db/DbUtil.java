package db;

import java.sql.*;

public class DbUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
    }
}
