package webservices;

import com.google.gson.Gson;
import mysql.*;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MembersWebServices extends HttpServlet {
    MySQLBase mySQLBase = new MySQLBase();
    Gson gson = new Gson();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = MySQLQueries.selectFromQuery("members");
        ResultSet resultSet = mySQLBase.executeQuery(query);

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
        PrintWriter out = response.getWriter();
        out.print(stringBuilder.toString());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Member member = gson.fromJson(request.getQueryString(), Member.class);
        String fullName = member.getFullName();
        String query = MySQLQueries.insertIntoQueryV("members", "full_name", fullName);
        mySQLBase.executeUpdate(query);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        Member member = gson.fromJson(request.getQueryString(), Member.class);
        String id = member.getId();
        String fullName = member.getFullName();
        String query = MySQLQueries.updateQuery("members",
                "full_name", fullName, "id", id);
        mySQLBase.executeUpdate(query);
    }
}