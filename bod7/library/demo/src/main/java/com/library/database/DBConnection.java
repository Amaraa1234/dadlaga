package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_id"; //
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Өгөгдлийн сантай амжилттай холбогдлоо!");
            }
        } catch (Exception e) {
            System.err.println("Холболтын алдаа: " + e.getMessage());
        }
        return connection;
    }
}