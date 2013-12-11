package com.sun.manager.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.util.Properties;

public class SqlServer {

    private static final  String DB_CONFIG   = "src/main/resources/db_config.properties";
    private static final  String DB_DRIVER   = "db_driver";
    private static final  String DB_URL      = "db_url";
    private static final  String DB_LOGIN    = "db_login";
    private static final  String DB_PASSWORD = "db_password";

    public static void init() {
        Properties prop = new Properties();
        try {
            prop.load(readDbConfigStream());

            Class.forName(prop.getProperty(DB_DRIVER));
            DriverManager.getConnection(prop.getProperty(DB_URL), prop.getProperty(DB_LOGIN), prop.getProperty(DB_PASSWORD));
        } catch (Exception e) {
            System.out.println("Connection Failed. Cause " + e.getMessage());
        }

    }

    private static FileInputStream readDbConfigStream() throws FileNotFoundException {
        return new FileInputStream(DB_CONFIG);
    }
}
