<%@ include file="navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <title>Our Users</title>
  </head>
  <body>
<div class="text-center mt-3">
<h3>These are all our users</h3>
</div>
     <div class="container">
        <table class="table" >
           <thead class="thead-dark">
             <tr>
               <th scope="col">Name</th>
               <th scope="col">City</th>
               <th scope="col">Email</th>
             </tr>
           </thead>

            <tbody>
                <%@ page import="java.sql.*" %>
                <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String url = "jdbc:mariadb://localhost:3306/UserData";
                String user = "root";
                String pass = "root";
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    System.out.print("Driver Loaded");
                    conn = DriverManager.getConnection(url, user, pass);
                    System.out.println("Connection created");

                    if (conn != null) {
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery("Select * from users");
                        System.out.println("Query run");

                        while (rs.next()) {
                            System.out.println("Inside the while loop");
                %>
                            <tr>
                                  <th scope="row"><%= rs.getString("name") %></th>
                                  <td><%= rs.getString("city") %></td>
                                  <td><%= rs.getString("email") %></td>
                                </tr>
                <%
                        }
                        System.out.println("Closing Connection");
                        rs.close();
                        stmt.close();
                        conn.close();
                    }
                } catch (Exception e) {
                    System.out.println("Something Went Wrong !!");
                    e.printStackTrace();
                }
                %>
            </tbody>
       </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

  </body>
</html>





















