package com.Dao;

import com.Entity.User;
import org.mindrot.jbcrypt.BCrypt;import java.sql.*;

public class Dao {

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
            System.out.println("Connection created for login servlet (Inside Dao's check method)");

            String q = "Select password from Users where email=?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            System.out.println("Query executed for login servlet (Inside Dao' check method)");
            if (rs.next()) {
                String hashedPasswordFromDB = rs.getString("password");
                System.out.println("Hashed Password : "+ hashedPasswordFromDB + "(Inside Dao's check method)");

                String userEnteredPassword = user.getPassword().trim();
                System.out.println( hashedPasswordFromDB + "  " + userEnteredPassword);
                System.out.println("Comparing Plain Password & Hashed Password (Inside Dao's check method)");
                if(hashedPasswordFromDB.equals(userEnteredPassword)){
                    System.out.println("Equal (Inside Dao's check method)");
                    return true;
                }else{
                    System.out.println("Unequal (Inside Dao's check method)");
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Connection never created for login servlet (Inside Dao's check method)");
            throw new RuntimeException(e);
        }
    }
    public static boolean save(User user){
        try {
            Connection con = DriverManager.getConnection(Url,Username,Password);
            System.out.println("Connection created for signup servlet (Inside Dao's save method)");

            String q = "Insert into Users (name, city, email, password) values (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, user.getName());
            ps.setString(2, user.getCity());
            ps.setString(3, user.getEmail());

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            int i = ps.executeUpdate();
            System.out.println("User saved successfully into Database : " + user.getName() + " " + user.getCity() + " "  + user.getEmail() + " "  + hashedPassword + "(Inside Dao's save method)");
            return i>0;
        } catch (SQLException e) {
            System.out.println("Connection never created for signup servlet (Inside Dao's save method)");
            throw new RuntimeException(e);
        }
    }

    public static String getPasswordFromDB(String email) {
        String hashedPassword = null;
        try {
            Connection connection = DriverManager.getConnection(Url, Username, Password);
            System.out.println("Connection created for Retriving hashed password from database (Inside Dao's getPasswordFromDB method)");

            String q = "select password from users where email = ?";
            PreparedStatement ps = connection.prepareStatement(q);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                hashedPassword = rs.getString("password");
            }
            System.out.println("Hashed password : " + hashedPassword + "(Inside Dao's getPasswordFromDB method)");
            System.out.println("Hashed password has been retrived from database (Inside Dao's getPasswordFromDB method)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    public static User getUserDetailsByEmail( String email) {
        User Userdetail = null;
        try {
            Connection connection = DriverManager.getConnection(Url, Username, Password);
            System.out.println("Connection created for Retriving User's data from database (Inside Dao's getUserDetailsByEmail method)");

            String q = "select name, city, email, password from users where email = ?";
            PreparedStatement statement = connection.prepareStatement(q);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String city = rs.getString("city");
                String userEmail = rs.getString("email");
                String password = rs.getString("password");

                Userdetail = new User(name, city, userEmail, password);
                System.out.println("User's Details are : " + name + " " + city + " "  + email + " " +  password + " " + "(Inside Dao's getUserDetailsByEmail method)");

            }
            System.out.println("User's Details has been retrived from database (Inside Dao's getUserDetailsByEmail method)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Userdetail;
    }


}




