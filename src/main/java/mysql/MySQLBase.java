package mysql;

import utils.*;

import java.sql.*;
import java.util.Base64;
import java.util.Properties;

public class MySQLBase {
    Properties properties = PropertiesLoader.loadProperties("config");

    private Connection getConnection() throws Exception {
        Class.forName(properties.getProperty("jdbcDriver"));
        return DriverManager.getConnection(properties.getProperty("dbUrl"), properties.getProperty("user"),
                new String(Base64.getDecoder().decode(properties.getProperty("password"))));
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
