<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User Data</title>
</head>
<body>
    <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Contact</th>
                <th>Email</th>
            </tr>

            <%
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Jsp", "root", "root");
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from data");
                    while (rs.next()) {
            %>
                        <tr>
                            <td><%= rs.getInt("id") %></td>
                            <td><%= rs.getString("name") %></td>
                            <td><%= rs.getString("contact") %></td>
                            <td><%= rs.getString("email") %></td>
                        </tr>
            <%
                    }
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            %>
       </table>
</body>
</html>
