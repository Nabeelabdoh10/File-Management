package CleanCod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Export {

    public static void exportFile(String fileName,String fileType , long fileSize , int version) throws SQLException, IOException {
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO files (name, data , type , size , version) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = ConnectDB.conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }

        // Step 3: Set the values of the parameters
        try {
            pstmt.setString(1, FileEncryptor.encrypt(fileName));
        } catch (SQLException e) {
            System.out.println("Error setting parameter 1: " + e.getMessage());
        }
        File file = new File(fileName);
        file.createNewFile();
        try {
            FileInputStream input = new FileInputStream(file);
            int i = input.read();
            pstmt.setBlob(2, input);
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error setting parameter 2: " + e.getMessage());
        }
        //-----+
        // pstmt.setString(3, filePath);
        pstmt.setString(3, fileType);
        pstmt.setLong(4, fileSize);
        pstmt.setInt(5,version);
        //-----+
        // Step 4: Execute the query
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    }
