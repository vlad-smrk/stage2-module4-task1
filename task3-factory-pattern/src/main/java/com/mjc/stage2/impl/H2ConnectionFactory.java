package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream config = H2ConnectionFactory.class.getClassLoader().getResourceAsStream("h2database.properties")) {
            Properties properties = new Properties();
            properties.load(config);
            driver = properties.getProperty("jdbc_driver");
            url = properties.getProperty("db_url");
            username = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

