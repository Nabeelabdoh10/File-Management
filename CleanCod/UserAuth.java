package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuth {

    public static String getUser(String username) throws IOException, SQLException {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        String returnType = " ";
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            stmt = ConnectDB.conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
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
            if (ConnectDB.conn != null) {
                ConnectDB.conn.close();
            }
        }
        return returnType;
    }
    public static void setUser(String userName) throws IOException, SQLException {
        try {
        String sql = "INSERT INTO users (username, usertype) VALUES (?, ?)";
        PreparedStatement stmt = ConnectDB.conn.prepareStatement(sql);
        stmt.setString(1, userName);
        stmt.setString(2, "R");
        int rowsAffected = stmt.executeUpdate();
        stmt.close();
        ConnectDB.conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }


}
