package myPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            String url = "jdbc:mysql://localhost:3306/ebookshop";
            String username = "root";
            String password = "yash";
            Connection connection = DriverManager.getConnection(url, username, password);

            // Execute a SQL query
            String sql = "SELECT * FROM ebookshop";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

         

         // Display the table content with added styling
         out.println("<html><head><style>"
                 + "table {border-collapse: collapse; width: 100%;}"
                 + "th, td {border: 1px solid #dddddd; text-align: left; padding: 8px;}"
                 + "th {background-color: #f2f2f2;}"
                 + "</style></head><body>"
                 + "<h2>Book List</h2><table>"
                 + "<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th><th>Quantity</th></tr>");

         while (resultSet.next()) {
             out.println("<tr><td>" + resultSet.getInt("book_id") + "</td><td>"
                     + resultSet.getString("book_title") + "</td><td>"
                     + resultSet.getString("book_author") + "</td><td>Rs "
                     + resultSet.getDouble("book_price") + "</td><td>"
                     + resultSet.getInt("quantity") + "</td></tr>");
         }

         out.println("</table></body></html>");

        


            // Clean up
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}