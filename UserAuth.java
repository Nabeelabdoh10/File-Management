import java.io.*;
import java.sql.*;
public class UserAuth {
    public static String getUser(String username) throws IOException, SQLException {
        Connection conn = FileToDatabase.connection();
        //Statement stmt = conn.createStatement();
        //String sql = "INSERT INTO users (username, usertype) VALUES (?, ?)";

        ResultSet rs = null;
        PreparedStatement stmt = null;
        String returnType = " ";
        try {

            // Create a SQL statement to select the user
            String sql = "SELECT * FROM users WHERE username = ?";

            // Create a prepared statement using the SQL statement and the connection
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            // Execute the prepared statement and retrieve the result set
            rs = stmt.executeQuery();


            // Check if the result set is empty
            if (rs.next()) {
                String userType = rs.getString("usertype");
                returnType=userType;
            }


        } catch (SQLException e) {
            // Handle any errors that may have occurred
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return returnType;
    }
    public static void setUser(String userName) throws IOException, SQLException {
        Connection conn = FileToDatabase.connection();
        try {
        String sql = "INSERT INTO users (username, usertype) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userName);
        stmt.setString(2, "R");//new user reader

        // Execute the statement
        int rowsAffected = stmt.executeUpdate();
        // Close the statement and connection
        stmt.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public static void deleteUser(String userName) throws IOException, SQLException {
        Connection conn = null;
        conn = FileToDatabase.connection();
        // Step 2: Delete
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM users WHERE username=?";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }
        // Step 3: Set the value of the parameter
        try {
            //pstmt.setInt(1, fileId);
            pstmt.setString(1, userName);
        } catch (SQLException e) {
            System.out.println("Error setting parameter: " + e.getMessage());
        }

        // Step 4: Execute the query
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        //setUser("Admin");
        //setUser("Staff");
        getUser("Admin");



    }
}
