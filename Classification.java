import java.io.IOException;
import java.sql.*;

public class Classification {


    public static void exteniton(String query) throws IOException {
        // Connect to the database
        Connection connection = null;


        connection = FileToDatabase.connection();

        // Create a statement
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Execute the query
        try {
            ResultSet resultSet = statement.executeQuery("SELECT name , version FROM files WHERE "+query);
            System.out.println("File Name          Version\n");
            while (resultSet.next()) {
                String file = resultSet.getString("name");
                String version = resultSet.getString("version");

                System.out.println(FileEncryptor.decrypt(file)+"             "+version);
            }

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
// Process the results
    }

    public static void main(String[] args) throws IOException {
        exteniton("type = 'cpp'");
    }

}

