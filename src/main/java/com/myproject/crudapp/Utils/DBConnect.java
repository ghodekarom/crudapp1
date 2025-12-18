package com.myproject.crudapp.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String dburl = "jdbc:postgresql://localhost:5432/postgres";
    private static final String dbuser = "postgres";
    private static final String dbpass = "password";

    static{

        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException cnfe){
            System.err.println("postgresql driver class not found"+cnfe.getMessage());
        }
    }

    public static Connection fetchConnection(){

        Connection c = null;
        try{
            c = DriverManager.getConnection(dburl,dbuser,dbpass);
        }catch(SQLException se){
            System.err.println("connection failed!");
        }
        return c;
    }
}
