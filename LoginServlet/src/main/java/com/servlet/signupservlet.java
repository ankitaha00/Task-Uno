package com.servlet;

import com.dao.dao;
import com.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signupservlet", value = "/signupservlet")
public class signupservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        System.out.println(name);

        String contact = request.getParameter("contact");
        System.out.println(contact);

        String email = request.getParameter("email");
        System.out.println(email);

        String password = request.getParameter("password");
        System.out.println(password);

        User user = new User(name,contact,email,password);
        System.out.println("User created for save method");
        if (dao.save(user)){
            System.out.println("Save method used");
            System.out.println("Redirecting to Login page");
            request.setAttribute("user",user);
            RequestDispatcher rd = request.getRequestDispatcher("loginservlet");
            rd.forward(request, response);
            response.sendRedirect("index.jsp");
        }
        else {
            System.out.println("Something went wrong");
            System.out.println("Save never method used");
            System.out.println("Redirecting to Signup page");
            response.sendRedirect("signup.jsp");}

    }
}