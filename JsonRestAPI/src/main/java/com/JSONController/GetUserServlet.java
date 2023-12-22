package com.JSONController;


import com.Dao.Dao;
import com.Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetUserServlet", value = "/GetUser")
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = Dao.getAllUsers();

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");

        try {
            if (!users.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(response.getWriter(), users);
                System.out.println("All Users Retrieved Successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                objectMapper.writeValue(response.getWriter(), "No users found.");
                System.out.println("No Users Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



