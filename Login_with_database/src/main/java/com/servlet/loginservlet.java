package com.servlet;


import com.dao.dao;
import com.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "loginservlet", value = "/loginservlet")
public class loginservlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        System.out.println(email);

        String password = request.getParameter("password");
        System.out.println(password);

        User user = new User(email,password);
        System.out.println("User created for check method");
        if (dao.check(user)){
            System.out.println("Check method used");
            System.out.println("Redirecting to Success page");
            response.sendRedirect("success.jsp");
        }
        else {
            System.out.println("Check never method used");
            System.out.println("Redirecting to Login page");
            response.sendRedirect("index.jsp");
        }

    }

}



