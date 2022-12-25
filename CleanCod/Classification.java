package CleanCod;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Classification {


    public static void Classificat(String query) throws IOException {


        Statement statement = null;
        try {
            statement = ConnectDB.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    }


}

