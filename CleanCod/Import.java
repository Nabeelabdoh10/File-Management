package CleanCod;

import java.io.IOException;
import java.sql.*;

public class Import {

    public static void importFile(String pathFileName , int version) throws SQLException, IOException {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            // Step 3: Execute the SELECT query
            stmt = ConnectDB.conn.createStatement();
            rs = stmt.executeQuery("SELECT name , data  FROM files WHERE name = '" + FileEncryptor.encrypt(pathFileName)+"'AND Version = '"+version+"'");

            // Check if the file exists in the database
            if (rs.next()) {
                String fileName = rs.getString("name");
                Blob fileData = rs.getBlob("data");
                byte[] data = fileData.getBytes(1, (int) fileData.length());
                System.out.println("File name: " + FileEncryptor.decrypt(fileName));
                System.out.println("File data: \n" + new String(data));

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close the ResultSet, Statement, and Connection objects
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (ConnectDB.conn != null) {
                    ConnectDB.conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
