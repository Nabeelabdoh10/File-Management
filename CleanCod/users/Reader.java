package CleanCod.users;

import CleanCod.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public  class Reader {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<String, Integer> map = new HashMap<>();
    public static void readerServices() throws IOException, SQLException {

        File newFile;
        int version=0;
        String services =
                "Enter\n"
                        + "~ import <filename>                     :  to read file\n"
                        + "~ list                                 :  to display the detailed list of all files in system\n"
                        + "~ classification                       :  to classification the files in system\n"
                        + "~ exit                                :  to exit the system\n";
        System.out.println(services);
        while (true) {
            System.out.print("\n>>>   ");
            String[] s = br.readLine().split(" ");
            if (s[0].equals("import")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (ContainingDB.containingDB(s[1])) {
                    version=map.get(FileEncryptor.encrypt(s[1]));
                    Import.importFile(s[1],version);
                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("list")) {
                System.out.println();
                GetDBFileInfo.getFileInfo();
            }   else if (s[0].equals("classification")) {
                System.out.println("Classification about : ");
                Scanner myObj = new Scanner(System.in);
                String query = myObj.nextLine();  // Read user input
                Classification.Classificat(query);

            } else if (s[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }

        }

    }

}
