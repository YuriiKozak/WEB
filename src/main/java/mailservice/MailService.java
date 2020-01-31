package mailservice;

import com.google.gson.Gson;
import mysql.*;

import javax.servlet.http.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MailService extends HttpServlet {
    MySQLBase mySQLBase = new MySQLBase();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = MySQLQueries.selectFromQuery("mails");
        ResultSet resultSet = mySQLBase.executeQuery(query);

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
        PrintWriter out = response.getWriter();
        out.print(stringBuilder.toString());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        Mail mail = gson.fromJson(json, Mail.class);
        String subject = mail.getSubject();
        String email = mail.getEmail();
        String body = mail.getBody();
        String query = MySQLQueries.insertIntoQueryLV("mails",
                Arrays.asList("subject", "email", "body"), Arrays.asList(subject, email, body));
        mySQLBase.executeUpdate(query);
        PrintWriter out = response.getWriter();
        out.print(subject);
        out.print(email);
        out.print(body);
    }
}