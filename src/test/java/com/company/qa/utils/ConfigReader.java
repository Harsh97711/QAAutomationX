package com.company.qa.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();

    static {
        try {
            System.out.println(">>> LOADING CONFIG.PROPERTIES <<<");

            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ config.properties NOT FOUND");
            }

            prop.load(input);

            System.out.println(">>> CONFIG VALUES <<<");
            prop.forEach((k, v) -> System.out.println(k + " = " + v));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FAILED TO LOAD CONFIG");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
