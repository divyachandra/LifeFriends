package com.csuf.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {
    private static DbConnectionManager instance = new DbConnectionManager();
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private DbConnectionManager() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(DbConfig config) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getURL(config), config.username, config.password);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    private String getURL(DbConfig config) {
        StringBuilder sb = new StringBuilder("jdbc:mysql://");
        sb.append(config.host).append("/").append(config.database);
        return sb.toString();
    }

    public static Connection getConnection(DbConfig config) {
        return instance.createConnection(config);
    }
}
