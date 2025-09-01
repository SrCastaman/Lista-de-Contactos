package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:contacts.db";

    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initialize(){
        String sql = "CREATE TABLE IF NOT EXISTS contacts (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT NOT NULL," +
                     "phone TEXT NOT NULL," +
                     "email TEXT" +
                     ");";


        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Base de datos inicializada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
