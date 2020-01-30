package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/" + fileName + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
