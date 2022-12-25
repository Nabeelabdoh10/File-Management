import java.io.*;
import java.io.File;
import java.sql.*;


public class writeFile {
    public static void appendAndWriteToFile(File fileName,StringBuilder data,int version) {

        try {
            // Create a FileWriter and a BufferedWriter
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(String.valueOf(data));
            // Close the writers
            bufferedWriter.close();
            writer.close();
            String type= FileSystem.extension(fileName.getAbsolutePath());
            FileToDatabase.exportFile(fileName.getName(),type,fileName.length(),version);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void writer(String fileName,StringBuilder Data,int version) throws SQLException, IOException {
        // Create a File object for the file you want to read
        File file = new File(fileName);

        Connection conn = null;
        conn = FileToDatabase.connection();
        ResultSet rs = null;
        Statement stmt = null;
        // Read the contents of the file
        try {
            FileReader reader = new FileReader(file);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT  data  FROM files WHERE name = '" + FileEncryptor.encrypt(fileName) + "'");
            byte[] data = new byte[0];
            if (rs.next()) {
                Blob fileData = rs.getBlob("data");
                data = fileData.getBytes(1, (int) fileData.length());
               // System.out.println("File data: \n" + new String(data));
            }
            int c;
            String oldData=new String(data);

            // Check if the data is equal to null
            if (oldData.toString().equals("")) {
                //createAndWriteToFile(file,Data);
                appendAndWriteToFile(file,Data,version);
            } else {
                appendAndWriteToFile(file,Data,version);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    
}
