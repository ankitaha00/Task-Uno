package com.dao;

import com.entity.User;
import jakarta.servlet.RequestDispatcher;

import java.sql.*;

public class dao {

    private static final String Url ="jdbc:mariadb://localhost:3306/UserData";
    private static final String Username ="root";
    private static final String Password ="root";

    static{
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean check(User user){
        try {
            Connection con = DriverManager.getConnection(Url,Username,Password);
            System.out.println("Connection created for login servlet");

            String q = "Select * from User where email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            System.out.println("Query executed for login servelt");
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Connection never created for login servlet");
            throw new RuntimeException(e);
        }
    }

    public static boolean save(User user){
        try {
            Connection con = DriverManager.getConnection(Url,Username,Password);
            System.out.println("Connection created for signup servlet");
            String q = "Insert into User (name, contact, email, password) values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, user.getName());
            ps.setString(2, user.getContact());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            int i = ps.executeUpdate();
            System.out.println("Query updated for signup servelt");
            return i>0;
        } catch (SQLException e) {
            System.out.println("Connection never created for signup servlet");
            throw new RuntimeException(e);
        }

    }

}




