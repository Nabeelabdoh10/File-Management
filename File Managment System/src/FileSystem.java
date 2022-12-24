import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class FileSystem {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean containsFile(LinkedList<File> files, String fileName) {
        for (File item : files) {
            if (item.getName().equals(fileName))
                return true;
        }
        return false;
    }
    
    static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf('.');
        if (index > 0) {
            extension = path.substring(index + 1);
        }
        return extension;
    }
    public static void main(String args[]) throws IOException, SQLException {
       String user =
                "To Log in Enter\n"
                        + "~ Name <username>                   :  to display the user services\n"
                        + "~ Signup <username>                 :  to sign up \n"
                        + "~ exit                                :  to exit the system\n";

        System.out.println(user);
        while (true) {
            System.out.print("\n>>>   ");
            String[] input = br.readLine().split(" ");
            if (input[0].equals("Name")) {
                if (input.length < 2) {
                    System.out.println("Enter user name. Try again ...");
                    continue;
                } else {
                    String users = UserAuth.getUser(input[1]);
                    if (users.equals(" ")) {
                        System.out.println("User not found ... Sign up ");
                        continue;
                    }
                    if (users.equals("A")) {
                        System.out.println("Admin Services \n");
                        UserServices.adminServices();
                    } else if (users.equals("S")) {
                        System.out.println("Staff Services \n");
                        UserServices.staffServices();
                    } else if (users.equals("R")) {
                        System.out.println("Reader Services \n");
                        UserServices.readerServices();
                    }

                }

            }
            else if(input[0].equals("Signup")){
                if (input.length < 2) {
                    System.out.println("Enter user name. Try again ...");
                    continue;
                }
                else {
                    UserAuth.setUser(input[1]);
                    System.out.println("Sign up successfully...");
                }
            }
            else if (input[0].equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }
        }
    }

}
