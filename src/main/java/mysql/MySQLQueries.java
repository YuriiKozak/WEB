package mysql;

import java.util.List;

public class MySQLQueries {
    public static String selectFromQuery(String table) {
        return "SELECT * FROM " + table;
    }

    public static String insertIntoQueryV(String table, String column, String value) {
        return "INSERT INTO " + table + " (" + column + ") VALUES ('" + value + "')";
    }

    public static String insertIntoQueryLV(String table, List<String> columnsList, List<String> valuesList) {
        String columns = String.join(",", columnsList);
        String values = String.join("','", valuesList);
        return "INSERT INTO " + table + " (" + columns + ") VALUES ('" + values + "')";
    }

    public static String insertIntoQueryLLV(String table, List<String> columnsList, List<List<String>> valuesList) {
        String columns = String.join(",", columnsList);
        StringBuilder stringBuilder = new StringBuilder();
        valuesList.forEach(v ->
                stringBuilder.append("('").append(String.join("','", v)).append("'),"));
        String values = stringBuilder.toString();
        return "INSERT INTO " + table + " (" + columns + ") VALUES " +
                values.substring(0, values.length() - 1);
    }

    public static String updateQuery(String table, String setValueName, String setValue,
                                     String whereValueName, String whereValue) {
        return "UPDATE " + table + " SET " + setValueName + " = '" + setValue +
                "' WHERE " + whereValueName + " = '" + whereValue + "'";
    }

    public static String deleteQuery(String table, String whereValueName, String whereValue) {
        return "DELETE FROM " + table + " WHERE " + whereValueName + " = '" + whereValue + "'";
    }
}
