package mysql;

import java.sql.*;

public class MySQLBase {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/epam?autoReconnect=true&useSSL=false";
    public static final String USER = "Iurii_Kozak";
    public static final String PASS = "MySQL#2020";

    private Connection getConnection() throws Exception {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private PreparedStatement prepareStatement(String query) throws Exception {
        return getConnection().prepareStatement(query);
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = prepareStatement(query).executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void executeUpdate(String query) {
        try {
            prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
