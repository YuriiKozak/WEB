package mailservice;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import mysql.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/mails")
public class MailService extends HttpServlet {
    MySQLBase mySQLBase = new MySQLBase();
    Gson gson = new Gson();
    String mails = "mails";
    String id = "id";
    String subject = "subject";
    String email = "email";
    String body = "body";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        synchronized (this) {
            ResultSet resultSet;
            String parameterName = null;
            String parameterValue = null;
            try {
                parameterName = request.getParameterNames().nextElement();
                parameterValue = request.getParameter(parameterName);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!Strings.isNullOrEmpty(parameterName)) {
                String query = MySQLQueries.selectFromQuery(mails, parameterName, parameterValue);
                resultSet = mySQLBase.executeQuery(query);
            } else {
                String query = MySQLQueries.selectFromQuery(mails);
                resultSet = mySQLBase.executeQuery(query);
            }

            StringBuilder stringBuilder = new StringBuilder();
            try {
                assert resultSet != null;
                while (resultSet.next()) {
                    int idValue = resultSet.getInt(id);
                    String subjectValue = resultSet.getString(subject);
                    String emailValue = resultSet.getString(email);
                    String bodyValue = resultSet.getString(body);
                    stringBuilder.append(idValue).append("\n").append(subjectValue).append("\n")
                            .append(emailValue).append("\n").append(bodyValue).append("\n").append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            PrintWriter out = response.getWriter();
            out.print(stringBuilder.toString());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        synchronized (this) {
            String json = request.getReader().lines().collect(Collectors.joining());
            Mail mail = gson.fromJson(json, Mail.class);
            String subjectValue = mail.getSubject();
            String emailValue = mail.getEmail();
            String bodyValue = mail.getBody();
            String query = MySQLQueries.insertIntoQueryLV(mails,
                    Arrays.asList(subject, email, body), Arrays.asList(subjectValue, emailValue, bodyValue));
            mySQLBase.executeUpdate(query);
            PrintWriter out = response.getWriter();
            out.print(subjectValue + " " + emailValue + " " + bodyValue);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        synchronized (this) {
            String json = request.getReader().lines().collect(Collectors.joining());
            Mail mail = gson.fromJson(json, Mail.class);
            String subjectValue = mail.getSubject();
            String emailValue = mail.getEmail();
            String query = MySQLQueries.deleteQuery(mails, subject, subjectValue, email, emailValue);
            mySQLBase.executeUpdate(query);
            PrintWriter out = response.getWriter();
            out.print(subjectValue + " " + emailValue);
        }
    }
}