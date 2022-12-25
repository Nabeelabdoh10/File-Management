import java.io.*;
import java.sql.*;
public class FileToDatabase {


    public static Connection connection() throws IOException {
        // Step 1: Connect to the database
        String url = "jdbc:mysql://127.0.0.1:3306/Files";
        String username = "root";
        String password = "12345";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url + "?useSSL=false", username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

        return conn;
    }

    public static void importFile(String pathFileName , int version) throws SQLException, IOException { //not complete
        Connection conn = null;
        conn = connection();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            // Step 3: Execute the SELECT query
            stmt = conn.createStatement();
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
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exportFile(String fileName,String fileType , long fileSize , int version) throws IOException, SQLException {
        Connection conn = null;
        conn = connection();
        // Step 2: Create a statement object
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO files (name, data , type , size , version) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
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

    public static void deleteFile(String fileName , int version ) throws IOException {
        Connection conn = null;
        conn = connection();
        // Step 2: Delete
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM files WHERE name = ? and Version =?";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }

        // Step 3: Set the value of the parameter
        try {
            //pstmt.setInt(1, fileId);
            pstmt.setString(1, FileEncryptor.encrypt(fileName));
            pstmt.setInt(2, version);
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

    public static void getFileInfo() throws IOException {

        Connection conn = null;
        conn = connection();
        ResultSet rs = null;
        Statement stmt = null;

        try {
            // Create a statement
            stmt = conn.createStatement();

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
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean containingDB(String fileName) throws IOException, SQLException {
        Connection conn = null;
        conn = connection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM files WHERE name = '" + FileEncryptor.encrypt(fileName) + "'");
        if (!rs.next()) {
            rs.close();
            return false;
        }
        return true;
    }

    

}
