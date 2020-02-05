package debug;

import mysql.*;

public class MySQLDelete extends MySQLBase {
    public static void main(String[] args) {
//        deleteMembers();
        deleteMails();
    }

    public static void deleteMembers() {
        String id = "1";
        String query = MySQLQueries.deleteQuery("members", "id", id);
        new MySQLBase().executeUpdate(query);
    }

    public static void deleteMails() {
        String id = "1";
        String subjectQuery = MySQLQueries.deleteQuery("mails", "id", id);
        new MySQLBase().executeUpdate(subjectQuery);
    }
}