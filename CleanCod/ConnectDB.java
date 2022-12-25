package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    static ConnectDB con = ConnectDB.getInstance();
    static Connection conn;

    static {
        try {
            conn = con.connection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConnectDB() {}
    private static ConnectDB connect = null;
    public static ConnectDB getInstance() {
        if (connect == null) {
            connect = new ConnectDB();
        }
        return connect;
    }

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


}
