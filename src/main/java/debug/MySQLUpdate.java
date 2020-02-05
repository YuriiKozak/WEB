package debug;

import mysql.*;
import utils.Utils;

public class MySQLUpdate extends MySQLBase {
    public static void main(String[] args) {
//        updateMembers();
        updateMails();
    }

    public static void updateMembers() {
        String id = "1";
        String fullName = new Utils().randomFullName;
        String query = MySQLQueries.updateQuery("members",
                "full_name", fullName, "id", id);
        new MySQLBase().executeUpdate(query);
    }

    public static void updateMails() {
        String id = "1";
        String subject = new Utils().randomSubject;
        String email = new Utils().randomEmail;
        String body = new Utils().randomBody;
        String subjectQuery = MySQLQueries.updateQuery("mails",
                "subject", subject, "id", id);
        String emailQuery = MySQLQueries.updateQuery("mails",
                "email", email, "id", id);
        String bodyQuery = MySQLQueries.updateQuery("mails",
                "body", body, "id", id);
        new MySQLBase().executeUpdate(subjectQuery);
        new MySQLBase().executeUpdate(emailQuery);
        new MySQLBase().executeUpdate(bodyQuery);
    }
}