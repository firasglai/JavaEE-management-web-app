package com.example.crudscolarite.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8";
    private static final String dbUname = "root";
    private static  final String dbPassword = "root123";
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private  static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Class.forName(dbDriver);
                connection = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
                System.out.println("connection succes");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
    }
