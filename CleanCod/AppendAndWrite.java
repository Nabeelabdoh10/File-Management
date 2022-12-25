package CleanCod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class AppendAndWrite {

        public static void appendAndWriteToFile(File fileName, StringBuilder data, int version) {

            try {
                // Create a FileWriter and a BufferedWriter
                FileWriter writer = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(String.valueOf(data));
                // Close the writers
                bufferedWriter.close();
                writer.close();
                String type = Extension.extension(fileName.getAbsolutePath());
                Export.exportFile(fileName.getName(), type, fileName.length(), version);
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }

}