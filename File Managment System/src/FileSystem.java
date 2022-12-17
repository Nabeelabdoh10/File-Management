import java.io.*;
import java.util.*;
import java.util.Date;
import java.util.LinkedList;

//import org.apache.commons.io.FilenameUtils;
public class FileSystem {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    static boolean containsFile(LinkedList<File> files, String fileName) {
        for (File item : files) {
            if (item.getName().equals(fileName))
                return true;
        }
        return false;
    }

    /* static void changeVersion(File file,int change){
         int version = file.getVersion() + change;
         file.setVersion(version);

     }*/
    static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf('.');
        if (index > 0) {
            extension = path.substring(index + 1);
        }
        return extension;
    }

    public static void main(String args[]) throws IOException {
        File newFile;


        LinkedList<File> FileManagmentSystem = new LinkedList<File>();
        String services =
                "Enter\n"
                        + "~ createf <filename>                  :  to create new file\n"
                        + "~ delf <filename>                     :  to delete this file\n"
                        + "~ readf <filename>                     :  to read the whole file\n"
                        + "~ writef  <filename>                   :  to append into the existing file\n"
                        + "~ list                                :  to display the list the names of all files in system\n"
                        + "~ list -l                             :  to display the detailed list of all files in system\n"
                        + "~ exit                                :  to exit the system\n";
        System.out.println(services);
        while (true) {
            System.out.print("\n>>>   ");
            String[] s = br.readLine().split(" ");
            if (s[0].equals("createf")) {
                newFile = new File(s[1]);
                newFile.createNewFile();
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (containsFile(FileManagmentSystem, s[1])) {
                    //changeVersion(newFile,1);
                    System.out.println("File with name \"" + s[1] + "\" already exists. Try again with different name...");
                    continue;
                }
                FileManagmentSystem.add(newFile);
                System.out.println("New File created successfully...");
            } else if (s[0].equals("list")) {

                if (s.length > 1) {
                    String header = "Name Of File        Size        Type        Version";
                    System.out.println(header);
                    System.out.println();
                    if (s[1].equals("-l")) {
                        for (File item : FileManagmentSystem) {
                            //+ "        "+item.getVersion()
                            System.out.println(item.getName() + "            " + item.length() + "           " + extension(item.getAbsolutePath()));
                        }
                    }

                } else {
                    for (File item : FileManagmentSystem) {
                        System.out.println(item.getName());
                    }
                }
            } else if (s[0].equals("delf")) {
                if (s.length < 2) {
                    System.out.println("Not enough argument. Try again ...");
                    continue;
                } else if (containsFile(FileManagmentSystem, s[1])) {
                    for (File item : FileManagmentSystem) {
                        if (item.getName().equals(s[1])) {

                            FileManagmentSystem.remove(item);
                            item.delete();
                            System.out.println("File with name \"" + s[1] + "\" deleted successfully!");
                            continue;
                            /* if(item.getVersion()>0){
                                changeVersion(item,-1);

                            System.out.println("File with name \"" + s[1] + "\" Version has been modified successfully!");
                            FileManagmentSystem.remove(item);
                            item.delete();
                            continue;
                        }
                        else {
                            FileManagmentSystem.remove(item);
                            item.delete();
                            System.out.println("File with name \"" + s[1] + "\" deleted successfully!");
                            continue;
                            }
                        }*/
                        }
                    }
                }
                    else {
                            System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                        }



            }
                else if (s[0].equals("writef")) {
                    if (s.length < 2) {
                        System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                        continue;
                    } else if (containsFile(FileManagmentSystem, s[1])) {
                        for (File item : FileManagmentSystem) {
                            if (item.getName().equals(s[1])) {
                                try {
                                    FileWriter myWriter = new FileWriter(item.getName());
                                    System.out.print("\nWrite in file " + item.getName() + " >>>   ");
                                    String writeInFile = br.readLine();
                                    myWriter.write(writeInFile);
                                    myWriter.close();
                                    System.out.println("Successfully wrote to the file.");
                                } catch (IOException e) {
                                    System.out.println("An error occurred.");
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                    }
                } else if (s[0].equals("readf")) {
                    if (s.length < 2) {
                        System.out.println("Not enough argument. Try again ...");
                        continue;
                    } else if (containsFile(FileManagmentSystem, s[1])) {
                        for (File item : FileManagmentSystem) {
                            if (item.getName().equals(s[1])) {
                                try {
                                    File myObj = new File(item.getName());
                                    Scanner myReader = new Scanner(myObj);
                                    while (myReader.hasNextLine()) {
                                        String data = myReader.nextLine();
                                        System.out.print("\nWrite in file " + item.getName() + " >>>   \n" + data);
                                    }
                                    myReader.close();
                                } catch (FileNotFoundException e) {
                                    System.out.println("An error occurred.");
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        System.out.println("Sorry! File with this name doesn't exist! Try Again . . .");
                    }
                } else if (s[0].equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println("Invalid command");
                }
            }


        }

    }
