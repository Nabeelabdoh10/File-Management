package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContainingDB {
    public static boolean containingDB(String fileName) throws IOException, SQLException {

        Statement stmt = ConnectDB.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM files WHERE name = '" + FileEncryptor.encrypt(fileName) + "'");
        if (!rs.next()) {
            rs.close();
            return false;
        }
        return true;
    }
}
