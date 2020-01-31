package debug;

import mysql.*;

import java.sql.*;

public class MySQLSelect extends MySQLBase {
    public static void main(String[] args) {
//        selectMembers();
        selectMails();
    }

    public static void selectMembers() {
        String query = MySQLQueries.selectFromQuery("members");
        ResultSet resultSet = new MySQLBase().executeQuery(query);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String full_name = resultSet.getString("full_name");
                stringBuilder.append(id).append(" ").append(full_name).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(stringBuilder.toString());
    }

    public static void selectMails() {
        String query = MySQLQueries.selectFromQuery("mails");
        ResultSet resultSet = new MySQLBase().executeQuery(query);

        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String subject = resultSet.getString("subject");
                String email = resultSet.getString("email");
                String body = resultSet.getString("body");
                stringBuilder.append(id).append("\n").append(subject).append("\n")
                        .append(email).append("\n").append(body).append("\n").append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(stringBuilder.toString());
    }
}