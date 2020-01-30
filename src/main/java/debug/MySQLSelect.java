package debug;

import mysql.*;

import java.sql.*;

public class MySQLSelect extends MySQLBase {
    public static void main(String[] args) throws Exception {
        String query = MySQLQueries.selectFromQuery("members");
        ResultSet resultSet = new MySQLBase().executeQuery(query);

        StringBuilder stringBuilder = new StringBuilder();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String full_name = resultSet.getString("full_name");
            stringBuilder.append(id).append(" ").append(full_name).append("\n");
        }
        System.out.print(stringBuilder.toString());
    }
}