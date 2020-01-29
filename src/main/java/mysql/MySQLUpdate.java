package mysql;

import utils.Utils;

public class MySQLUpdate extends MySQLBase {
    public static void main(String[] args) {
        String fullName = new Utils().randomFullName;
        String query = MySQLQueries.updateQuery("members",
                "full_name", fullName, "id", "2");
        new MySQLBase().executeUpdate(query);
    }
}