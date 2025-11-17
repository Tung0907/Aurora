package com.example.aurorajewelry.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=Jewelry;encrypt=false";;
    private static final String USER = "sa";
    private static final String PASS = "123";

    private DBConnection() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot get DB connection", e);
        }
    }
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
