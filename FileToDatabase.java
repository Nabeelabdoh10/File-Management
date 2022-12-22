import java.io.*;
import java.sql.*;
import java.util.Properties;

public class FileToDatabase {

    public static Connection connection() throws IOException {
        // Step 1: Connect to the database
        String url = "jdbc:mysql://127.0.0.1:3306/Files";
        String username = "root";
        String password = "12345";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url+ "?useSSL=false", username, password);
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

        return conn;
    }

    public static void importFile( String pathFileName) throws SQLException, IOException { //not complete
        Connection conn = null;
        conn=connection();

        try {
            // Step 3: Execute the SELECT query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM files WHERE name = '" + pathFileName + "'");

            // Step 4: Iterate through the result set and retrieve the column data
            while (rs.next()) {
                String fileName = rs.getString("name");
                String Data = rs.getString("data");

                // Step 5: Print the column data
                System.out.println("name: " + fileName );
                System.out.println("Data: " + Data);
            }
        }
        catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Step 6: Close the connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    }

    public static void exportFile( String pathFileName) throws IOException {
        Connection conn = null;

        conn=connection();
        // Step 2: Create a statement object
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO files (name, data) VALUES (?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }

// Step 3: Set the values of the parameters
        try {
            pstmt.setString(1, pathFileName);
        } catch (SQLException e) {
            System.out.println("Error setting parameter 1: " + e.getMessage());
        }
        File file = new File(pathFileName);
        file.createNewFile();
        try {
            FileInputStream input = new FileInputStream(file);
            int i=input.read();
            pstmt.setBlob(2, input);
        } catch (SQLException | FileNotFoundException e) {
            System.out.println("Error setting parameter 2: " + e.getMessage());
        }
        // Step 4: Execute the query
        try {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void deleteFile( String pathFileName) throws IOException {
        Connection conn = null;
        conn=connection();
        // Step 2: Delete
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM files WHERE name=?";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error creating statement object: " + e.getMessage());
        }

        // Step 3: Set the value of the parameter
        try {
            //pstmt.setInt(1, fileId);
            pstmt.setString(1, pathFileName);
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
    public static void main(String[] args) throws Exception {

        // Step 1: Connect to the database

        exportFile("Test.txt");
        deleteFile( "Test.txt");
        importFile( "Test.txt");





    }

}