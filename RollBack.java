import java.io.IOException;
import java.sql.SQLException;

public class RollBack {

    public static void rollBack(String fileName,int version) throws IOException, SQLException {
        FileToDatabase.deleteFile(fileName , version);
        System.out.println("previous version for '"+fileName+ "' is : V("+--version+")");
        FileToDatabase.importFile(fileName,version);
    }

    public static void main(String[] args) throws SQLException, IOException {
        rollBack("file10.txt",1);
    }
}
