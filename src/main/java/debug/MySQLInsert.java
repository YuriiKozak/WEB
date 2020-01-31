package debug;

import mysql.*;
import utils.Utils;

import java.util.Arrays;

public class MySQLInsert extends MySQLBase {
    public static void main(String[] args) {
//        insertMembers();
        insertMails();
    }

    public static void insertMembers() {
        String query = MySQLQueries.insertIntoQueryLLV("members", Arrays.asList("full_name"),
                Arrays.asList(Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName)));
        new MySQLBase().executeUpdate(query);
    }

    public static void insertMails() {
        String query = MySQLQueries.insertIntoQueryLV("mails", Arrays.asList("subject", "email", "body"),
                Arrays.asList(new Utils().randomSubject, new Utils().randomEmail, new Utils().randomBody));
        new MySQLBase().executeUpdate(query);
    }
}