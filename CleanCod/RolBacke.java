package CleanCod;

import java.io.IOException;
import java.sql.SQLException;

public class RolBacke {

    public static void rollBack(String fileName,int version) throws IOException, SQLException {
        Delete.deleteFile(fileName , version);
        System.out.println("previous version for '"+fileName+ "' is : V("+--version+")");
        Import.importFile(fileName,version);
    }

}
