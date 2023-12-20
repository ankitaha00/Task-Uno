package com.Controller;

import com.Dao.Dao;
import com.Entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginservlet", value = "/loginservlet")
public class loginservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        System.out.println("Name received: " + name + "(Inside Login Servlet)");
//
//        String city = request.getParameter("city");
//        System.out.println("City received: " + city + "(Inside Login Servlet)");

        String email = request.getParameter("email");
        System.out.println("Email received: " + email + " (Inside Login Servlet)");

        String password = request.getParameter("password");
        System.out.println("Password received: " + password + " (Inside Login Servlet)");

        String hashedPasswordFromDB = Dao.getPasswordFromDB(email);
        System.out.println("Hashed password is : " + hashedPasswordFromDB + " (Inside Login Servlet)");

        User user = Dao.getUserDetailsByEmail(email);
//       System.out.println("User's Details are : " + name + city + "(Inside Login Servlet)");


//          if (password.equals(hashedPasswordFromDB) && Dao.check(user))
//          if (hashedPasswordFromDB != null && PasswordEncryptionUtil.checkPassword(password, hashedPasswordFromDB))
        if (hashedPasswordFromDB != null && Dao.check(user)) {
            System.out.println("Comparing the password (Inside Login Servlet)");
            System.out.println("Check method used (Inside Login Servlet)");
            System.out.println("Redirecting to Success page (Inside Login Servlet)");

            request.getSession().setAttribute("user", user);
            request.setAttribute("user", user);

//            session.setAttribute("message", new message("Login Sussessful !!", "success"));
            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("Wrong Password (Inside Login Servlet) ");
            System.out.println("Redirecting to Login page (Inside Login Servlet)");
//          session.setAttribute("message", new message("Something Went Wrong !!", "danger"));
            response.sendRedirect("login.jsp?loginFailed=true");
        }
    }
}