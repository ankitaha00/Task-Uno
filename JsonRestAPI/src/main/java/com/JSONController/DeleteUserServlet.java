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

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonInput = stringBuilder.toString();
        System.out.println("Received JSON: " + jsonInput);

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        try {
            user = objectMapper.readValue(jsonInput, User.class);
            System.out.println("User received: " + user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        boolean deleted = Dao.deleteUser(user.getId());
        response.setContentType("application/json");
        System.out.println("Java Object converted into JSON object");

        try {
            if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    objectMapper.writeValue(response.getWriter(), "User deleted successfully!");
                     System.out.println("User Deleted Successfully");
            } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    objectMapper.writeValue(response.getWriter(), "Failed to delete user.");
                    System.out.println("Unable To Delete User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
