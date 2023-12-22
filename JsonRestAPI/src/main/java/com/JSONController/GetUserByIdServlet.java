package com.JSONController;


import com.Dao.Dao;
import com.Entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "GetUserByIdServlet", value = "/GetUserById")
public class GetUserByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        int id = Integer.parseInt(userId);
        System.out.println("Id received : "+ id);

        User user = Dao.getUserById(id);
        System.out.println("Id received : "+ user + "(Inside GetUserById)");

        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json");

        try {
            if (user != null) {
                response.setStatus(HttpServletResponse.SC_OK);
                objectMapper.writeValue(response.getWriter(), user);
                System.out.println("User Fetched Successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                objectMapper.writeValue(response.getWriter(), "User not found.");
                System.out.println("User Not Found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
