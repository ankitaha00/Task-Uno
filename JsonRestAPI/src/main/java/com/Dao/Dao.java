package com.Dao;

import com.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private static final String Url = "jdbc:mariadb://localhost:3306/RestAPI";
    private static final String Username ="root";
    private static final String Password ="root";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(Url, Username, Password);
            System.out.println("Connection created (Inside Dao's getConnection method)");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        try {
            con = Dao.getConnection();
            String q = "Select * from user";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            System.out.println("Query executed for get all users (Inside Dao's getAllUsers method)");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String city = rs.getString("city");
                String contact = rs.getString("contact");

                User user = new User(id, name, city, contact);
                users.add(user);
                System.out.println("User are : " + name + " " + city + " " + contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User getUserById(int id){
        User user = null;
        Connection con = null;
        try {
            con = Dao.getConnection();
            String q = ("Select * from user where id = ?");
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            System.out.println("Query executed for get user by id (Inside Dao's getUserById method)");

            if (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                String contact = rs.getString("contact");

                user = new User(id, name, city, contact);
                System.out.println("User by id retrived from database (Inside Dao's getUserById method)");
                System.out.println("User by id " + id + " : " + name + " " + city + " " + contact +" (Inside Dao's getUserById method)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static boolean createUser(User user){
        boolean created = false;
        Connection con = null;
        try{
            con = Dao.getConnection();
            String q = "Insert into user (name, city, contact) values (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,user.getName());
            ps.setString(2, user.getCity());
            ps.setString(3, user.getContact());
                int rowsAffected = ps.executeUpdate();
                created = (rowsAffected > 0);
            System.out.println("Query executed for create a new user (Inside Dao's createUser method)");
            System.out.println("User : " + user.getName() + " " + user.getCity() +" "+ user.getContact());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return created;
    }

    public static boolean updateUser(User user) {
        boolean updated = false;
        Connection con = null;
            try {
                con = Dao.getConnection();
                String q = "Update user set name=?, city=?, contact=? Where id=?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setString(1, user.getName());
                ps.setString(2, user.getCity());
                ps.setString(3, user.getContact());
                ps.setInt(4, user.getId());

                int rowsAffected = ps.executeUpdate();
                updated = (rowsAffected > 0);
                System.out.println("Query executed for updating an existing user (Inside Dao's updateUser method)");
                System.out.println("User updated (Inside Dao's updateUser method)");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return updated;
    }

    public static boolean deleteUser(int id){
        boolean deleted = false;
        Connection con = null;
        try{
            con = Dao.getConnection();
            String q = "Delete from user Where id=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            deleted = (rowsAffected > 0);
            System.out.println("Query executed for deleting an existing user (Inside Dao's deleteUser method)");
            System.out.println("User deleted");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return deleted;
    }

}





