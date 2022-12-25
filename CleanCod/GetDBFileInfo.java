package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDBFileInfo {

    public static void getFileInfo() throws IOException {
        ResultSet rs = null;
        Statement stmt = null;

        try {
            // Create a statement
            stmt = ConnectDB.conn.createStatement();

            // Execute a SELECT statement to retrieve the file information
            rs = stmt.executeQuery("SELECT * FROM files");

            // Iterate through the ResultSet object and retrieve the file information
            while (rs.next()) {

                String name = rs.getString("name");
                String version = rs.getString("version");
                String type = rs.getString("type");
                long size = rs.getLong("size");
                // Print the file information to the console
                System.out.println( " File Name: " + FileEncryptor.decrypt(name) + "    , File Version: " + version + "    , File Type: " + type +"    File Size: " + size);
                System.out.println();
            }

            // Close the connection
            ConnectDB.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
