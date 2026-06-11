package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Өгөгдлийн сангийн мэдээлэл
    private static final String URL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1504";

    // Гаднаас дуудаж шинээр үүсгэхийг хориглоно
    private DBConnection() {
    }

    // Дуудах бүрт бааз руу найдвартай холболт үүсгэж буцаана.
    // Энэ нь MainController дээрх try-with-resources бүтэцтэй төгс зохицно.
    public static Connection getConnection() throws SQLException {
        try {
            // MySQL драйверийг ачаалах
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL driver not found! Please check your pom.xml.");
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        }
    }
}