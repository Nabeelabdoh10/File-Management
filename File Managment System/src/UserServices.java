import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

public class UserServices {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<String, Integer> map = new HashMap<>();

    public static void adminServices() throws IOException, SQLException {

        File newFile;
        int version=0;
        String services =
                "Enter\n"
                        + "~ export <filename>                    :  to create new file\n"
                        + "~ delf <filename>                      :  to delete this file\n"
                        + "~ import <filename>                    :  to read the whole file\n"
                        + "~ writef  <filename>                   :  to append into the existing file\n"
                        + "~ rollback  <filename>                 :  to replace current version with a previous version.\n"
                        + "~ list                                 :  to display the detailed list of all files in system\n"
                        + "~ classification                       :  to classification the files in system\n"
                        + "~ exit                                 :  to exit the system\n";
        System.out.println(services);
        while (true) {
            System.out.print("\n>>>   ");
            String[] s = br.readLine().split(" ");
            if (s[0].equals("export")) {
                newFile = new File(s[1]);
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    String name =newFile.getName();
                    String type = extension(newFile.getAbsolutePath());
                    //changeVersion(newFile,1);
                    if (map.containsKey(FileEncryptor.encrypt(name))) {
                        System.out.println("File with name \"" + s[1] + "\" already exists. Try again with different name...");
                        System.out.println("Enter :\n 1 - for new name.\n 2 - for new version");
                        int ch=scanner.nextInt();
                        if (ch==1){
                            System.out.println("Enter new file name :");
                            name=scanner.next();
                            map.put(name, 0);
                            FileToDatabase.exportFile(name,type,newFile.length(),0);

                        }
                        else if (ch==2){
                            version=map.get(FileEncryptor.encrypt(name));
                            version++;
                            map.put(FileEncryptor.encrypt(name),version);
                            FileToDatabase.exportFile(name,type,newFile.length(),version);
                            System.out.println(map);
                            continue;
                        }
                    }
                    else {

                        map.put(FileEncryptor.encrypt(s[1]),0);
                        FileToDatabase.exportFile(name,type,newFile.length(),version);
                        System.out.println(map);
                        continue;

                    }
                    System.out.println(map);
                    continue;
                }
                newFile.createNewFile();
                //FileManagmentSystem.add(newFile);
                String type = extension(newFile.getAbsolutePath());
                //String path =newFile.getAbsolutePath();
                long size = newFile.length();
                FileToDatabase.exportFile(s[1], type, size,version);
                map.put(FileEncryptor.encrypt(s[1]),0);
                System.out.println(map);
                System.out.println("New File created successfully...");
            } else if (s[0].equals("list")) {
                System.out.println();
                FileToDatabase.getFileInfo();
            } else if (s[0].equals("delf")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {

                    version= map.get(FileEncryptor.encrypt(s[1]));

                    FileToDatabase.deleteFile(s[1],version);
                    version--;
                    map.replace(FileEncryptor.encrypt(s[1]),version);
                    System.out.println("File with name \"" + s[1] + "\" deleted successfully!");
                    continue;
                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("writef")) {
                if (s.length < 2) {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    try {
                        FileWriter myWriter = new FileWriter(s[1], true);
                        System.out.print("\nWrite in  " + s[1] + " file, please enter \"<exit>\" in new line To end writing\n" + "  >>>   ");
                        StringBuilder sb = new StringBuilder();
                        String writeInFile = br.readLine();
                        while (!writeInFile.equals("<exit>")) {
                            sb.append(writeInFile + "\n");
                            writeInFile = br.readLine();
                        }
                        version= map.get(FileEncryptor.encrypt(s[1]));
                        System.out.println(version);
                        map.put(FileEncryptor.encrypt(s[1]),version+1);
                        writeFile.writer(s[1], sb,++version);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("import")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    version=map.get(FileEncryptor.encrypt(s[1]));
                    FileToDatabase.importFile(s[1],version);
                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("classification")) {

                System.out.println("Classification about : ");
                Scanner myObj = new Scanner(System.in);


                String query = myObj.nextLine();  // Read user input
                Classification.exteniton(query);

            }else if (s[0].equals("rollback")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                }
                else {
                    version= map.get(FileEncryptor.encrypt(s[1]));
                    RolBacke.rollBack(s[1],version);
                }
            }
            else if (s[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }

        }
    }
    public static void staffServices() throws IOException, SQLException {
        File newFile;
        int version=0;
        String services =
                "Enter\n"
                        + "~ export <filename>                   :  to create new file\n"
                        + "~ import <filename>                   :  to read the whole file\n"
                        + "~ writef  <filename>                  :  to append into the existing file\n"
                        + "~ list                                :  to display the detailed list of all files in system\n"
                        + "~ classification                      :  to classification the files in system\n"
                        + "~ exit                                :  to exit the system\n";
        System.out.println(services);
        while (true) {
            System.out.print("\n>>>   ");
            String[] s = br.readLine().split(" ");
            if (s[0].equals("export")) {
                newFile = new File(s[1]);
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    String name =newFile.getName();
                    String type = extension(newFile.getAbsolutePath());
                    //changeVersion(newFile,1);
                    if (map.containsKey(FileEncryptor.encrypt(name))) {
                        System.out.println("File with name \"" + s[1] + "\" already exists. Try again with different name...");
                        System.out.println("Enter :\n 1 - for new name.\n 2 - for new version");
                        int ch=scanner.nextInt();
                        if (ch==1){
                            System.out.println("Enter new file name :");
                            name=scanner.next();
                            map.put(name, 0);
                            FileToDatabase.exportFile(name,type,newFile.length(),0);

                        }
                        else if (ch==2){
                            version=map.get(FileEncryptor.encrypt(name));
                            version++;
                            map.put(FileEncryptor.encrypt(name),version);
                            FileToDatabase.exportFile(name,type,newFile.length(),version);
                            System.out.println(map);
                            continue;
                        }
                    }
                    else {

                        map.put(FileEncryptor.encrypt(s[1]),0);
                        FileToDatabase.exportFile(name,type,newFile.length(),version);
                        System.out.println(map);
                        continue;

                    }
                    System.out.println(map);
                    continue;
                }
                newFile.createNewFile();
                //FileManagmentSystem.add(newFile);
                String type = extension(newFile.getAbsolutePath());
                //String path =newFile.getAbsolutePath();
                long size = newFile.length();
                FileToDatabase.exportFile(s[1], type, size,version);
                map.put(FileEncryptor.encrypt(s[1]),0);
                System.out.println(map);
                System.out.println("New File created successfully...");
            } else if (s[0].equals("list")) {
                System.out.println();
                FileToDatabase.getFileInfo();
            }  else if (s[0].equals("writef")) {
                if (s.length < 2) {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    try {
                        FileWriter myWriter = new FileWriter(s[1], true);
                        System.out.print("\nWrite in  " + s[1] + " file, please enter \"<exit>\" in new line To end writing\n" + "  >>>   ");
                        StringBuilder sb = new StringBuilder();
                        String writeInFile = br.readLine();
                        while (!writeInFile.equals("<exit>")) {
                            sb.append(writeInFile + "\n");
                            writeInFile = br.readLine();
                        }
                        version= map.get(FileEncryptor.encrypt(s[1]));
                        System.out.println(version);
                        map.put(FileEncryptor.encrypt(s[1]),version+1);
                        writeFile.writer(s[1], sb,++version);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            }  else if (s[0].equals("import")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    version=map.get(FileEncryptor.encrypt(s[1]));
                    FileToDatabase.importFile(s[1],version);
                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("classification")) {

                System.out.println("Classification about : ");
                Scanner myObj = new Scanner(System.in);
                String query = myObj.nextLine();  // Read user input
                Classification.exteniton(query);

            } else if (s[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }

        }

    }
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
                } else if (FileToDatabase.containingDB(s[1])) {
                    version=map.get(FileEncryptor.encrypt(s[1]));
                    FileToDatabase.importFile(s[1],version);
                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("list")) {
                System.out.println();
                FileToDatabase.getFileInfo();
            }   else if (s[0].equals("classification")) {
                System.out.println("Classification about : ");
                Scanner myObj = new Scanner(System.in);
                String query = myObj.nextLine();  // Read user input
                Classification.exteniton(query);

            } else if (s[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }

        }

    }
    static {

        Connection conn = null;
        try {
            conn = FileToDatabase.connection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT name, version FROM files");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while(true)

        {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String name = null;
            try {
                name = rs.getString("name");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int version = 0;
            try {
                version = rs.getInt("version");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            map.put(name, version);
        }

    }
    public static   Scanner scanner = new Scanner(System.in);

    static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf('.');
        if (index > 0) {
            extension = path.substring(index + 1);
        }
        return extension;
    }


    }
