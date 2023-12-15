package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String URL = "jdbc:mariadb://localhost:3306/UserData";
        String Username = "root";
        String Password = "root";

        Connection con ;
        RequestDispatcher rd ;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con= DriverManager.getConnection(URL,Username,Password);
            PreparedStatement ps = con.prepareStatement("Select * from user WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful!");
                response.sendRedirect("success.jsp");
            } else {
                System.out.println("Wrong Password");
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


