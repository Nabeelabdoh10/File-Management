import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

public class UserServices {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf('.');
        if (index > 0) {
            extension = path.substring(index + 1);
        }
        return extension;
    }
    public static void adminServices() throws IOException, SQLException {

        File newFile;
        String services =
                "Enter\n"
                        + "~ export <filename>                  :  to create new file\n"
                        + "~ delf <filename>                     :  to delete this file\n"
                        + "~ import <filename>                     :  to read the whole file\n"
                        + "~ writef  <filename>                   :  to append into the existing file\n"
                        + "~ list                                 :  to display the detailed list of all files in system\n"
                        + "~ classification                       :  to classification the files in system\n"
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
                    System.out.println("File with name \"" + s[1] + "\" already exists. Try again with different name...");
                    continue;
                }
                newFile.createNewFile();
                //FileManagmentSystem.add(newFile);
                String type = extension(newFile.getAbsolutePath());
                //String path =newFile.getAbsolutePath();
                long size = newFile.length();
                FileToDatabase.exportFile(s[1], type, size);
                System.out.println("New File created successfully...");
            } else if (s[0].equals("list")) {
                System.out.println();
                FileToDatabase.getFileInfo();
            } else if (s[0].equals("delf")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {

                    FileToDatabase.deleteFile(s[1]);
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
                        writeFile.writer(s[1], sb);
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                }
            } else if (s[0].equals("readf")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (FileToDatabase.containingDB(s[1])) {
                    FileToDatabase.importFile(s[1]);
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
    public static void staffServices() throws IOException, SQLException {
        File newFile;
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
                    System.out.println("File with name \"" + s[1] + "\" already exists. Try again with different name...");
                    continue;
                }
                newFile.createNewFile();
                //FileManagmentSystem.add(newFile);
                String type = extension(newFile.getAbsolutePath());
                //String path =newFile.getAbsolutePath();
                long size = newFile.length();
                FileToDatabase.exportFile(s[1], type, size);
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
                        writeFile.writer(s[1], sb);
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
                    FileToDatabase.importFile(s[1]);
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
                    FileToDatabase.importFile(s[1]);
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

    public static void main(String[] args) throws SQLException, IOException {
        readerServices();
    }
    }

