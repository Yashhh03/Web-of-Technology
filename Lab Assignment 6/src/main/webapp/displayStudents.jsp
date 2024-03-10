<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #333;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        p.error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <h2>Students Information</h2>

    <% 
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            String url = "jdbc:mysql://localhost:3306/university";
            String username = "root";
            String password = "yash";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Execute a SQL query
            String sql = "SELECT * FROM students_info";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
    %>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Class</th>
            <th>Division</th>
            <th>City</th>
        </tr>
        <% 
            while (resultSet.next()) {
        %>
        <tr>
            <td><%= resultSet.getInt("stud_id") %></td>
            <td><%= resultSet.getString("stud_name") %></td>
            <td><%= resultSet.getString("class") %></td>
            <td><%= resultSet.getString("division") %></td>
            <td><%= resultSet.getString("city") %></td>
        </tr>
        <%
            }
        %>
    </table>

    <%
            // Clean up
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
    %>

    <p class="error">Error: <%= e.getMessage() %></p>

    <%
        }
    %>

</body>
</html>