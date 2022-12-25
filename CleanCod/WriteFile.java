package CleanCod;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
    public class WriteFile {

        public static void writer(String fileName,StringBuilder Data,int version) throws SQLException, IOException {
            File file = new File(fileName);
            ResultSet rs = null;
            Statement stmt = null;
            try {
                FileReader reader = new FileReader(file);
                stmt = ConnectDB.conn.createStatement();
                rs = stmt.executeQuery("SELECT  data  FROM files WHERE name = '" + FileEncryptor.encrypt(fileName) + "'");
                byte[] data = new byte[0];
                if (rs.next()) {
                    Blob fileData = rs.getBlob("data");
                    data = fileData.getBytes(1, (int) fileData.length());
                    // System.out.println("File data: \n" + new String(data));
                }
                int c;
                String oldData=new String(data);
                if (oldData.toString().equals("")) {
                    AppendAndWrite.appendAndWriteToFile(file,Data,version);
                } else {
                    AppendAndWrite.appendAndWriteToFile(file,Data,version);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
