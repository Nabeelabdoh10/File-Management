package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    public static void deleteFile(String fileName , int version ) throws IOException {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM files WHERE name = ? and Version =?";
        try {
            pstmt = ConnectDB.conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }

        try {
            pstmt.setString(1, FileEncryptor.encrypt(fileName));
            pstmt.setInt(2, version);
        } catch (SQLException e) {
            System.out.println("Error setting parameter: " + e.getMessage());
        }

        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

    }
}
