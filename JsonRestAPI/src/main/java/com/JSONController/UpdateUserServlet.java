package com.JSONController;


import com.Dao.Dao;
import com.Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet", value = "/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        String jsonInput = stringBuilder.toString();
        System.out.println("Received JSON: " + jsonInput);

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonInput, User.class);
        System.out.println("User received: " + user);

        boolean updated = Dao.updateUser(user);

        response.setContentType("application/json");
        System.out.println("Java Object converted into JSON object");

        try {
            if (updated) {
                response.setStatus(HttpServletResponse.SC_OK);
                String successMessage = "{\"message\": \"User Updated Successfully\"}";
                response.getWriter().write(successMessage);
                objectMapper.writeValue(response.getWriter(), user);
                System.out.println("User Updated Successfully");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                objectMapper.writeValue(response.getWriter(), "Failed to update user.");
                System.out.println("Unable To Update User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



//
//package com.JSONController;
//
//import com.Dao.Dao;
//import com.Entity.User;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//@WebServlet(name = "UpdateUserServlet", value = "/UpdateUser")
//public class UpdateUserServlet extends HttpServlet {
//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = request.getReader();
//        String line;
//
//        while ((line = bufferedReader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        String jsonInput = stringBuilder.toString();
//        System.out.println("Received JSON: " + jsonInput);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        User user = objectMapper.readValue(jsonInput, User.class);
//        System.out.println("User received: " + user);
//
//        boolean updated = Dao.updateUser(user);
//
//        response.setContentType("application/json");
//        System.out.println("Java Object converted into JSON object");
//
//        try {
//            if (updated) {
//                response.setStatus(HttpServletResponse.SC_OK);
//                objectMapper.writeValue(response.getWriter(), user);
//                System.out.println("User Updated Successfully");
//            } else {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                objectMapper.writeValue(response.getWriter(), "Failed to update user.");
//                System.out.println("Unable To Update User");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
