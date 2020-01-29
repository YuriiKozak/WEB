package mysql;

import utils.Utils;

import java.util.Arrays;

public class MySQLInsert extends MySQLBase {
    public static void main(String[] args) {
        String query = MySQLQueries.insertIntoQueryLLV("members", Arrays.asList("full_name"),
                Arrays.asList(Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName),
                        Arrays.asList(new Utils().randomFullName)));
        new MySQLBase().executeUpdate(query);
    }
}