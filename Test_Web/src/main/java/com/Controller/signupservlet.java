package com.Controller;

import com.Dao.Dao;
import com.Dao.PasswordEncryptionUtil;
import com.Entity.Message;
import com.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "signupservlet", value = "/signupservlet")
public class signupservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        System.out.println("Name received: " + name + "(Inside Signup Servlet)");

        String city = request.getParameter("city");
        System.out.println("City received: " + city + "(Inside Signup Servlet)");

        String email = request.getParameter("email");
        System.out.println("Email received: " + email + "(Inside Signup Servlet)");

        String password = request.getParameter("password");
        System.out.println("Plain Password received: " + password + "(Inside Signup Servlet)");
//        String hashedPassword = PasswordEncryptionUtil.encryptPassword(password); //Password is Encryped here
//        System.out.println("Hashed Password generated: " + hashedPassword + "(Inside Signup Servlet)");


        User user = new User(name,city,email,password);
        System.out.println("User created for save method (Inside Signup Servlet)");
        if (Dao.save(user)){
            System.out.println("Save method used (Inside Signup Servlet)");
            System.out.println("Redirecting to Login page (Inside Signup Servlet)");
            request.setAttribute("user",user);
//            request.session.setAttribute("message", new Message("Login Sussessful !!", "success"));
            response.sendRedirect("login.jsp");
//            RequestDispatcher rd = request.getRequestDispatcher("loginservlet");
//            rd.forward(request, response);
        }
        else {
            System.out.println("Something went wrong (Inside Signup Servlet)");
            System.out.println("Save never method used (Inside Signup Servlet)");
            System.out.println("Redirecting to Signup page (Inside Signup Servlet)");
//            session.setAttribute("message", new message("Login Sussessful !!", "success"));

            response.sendRedirect("signup.jsp?signupFailed=true");}

    }
}