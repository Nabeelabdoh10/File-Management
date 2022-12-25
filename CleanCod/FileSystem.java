package CleanCod;

import CleanCod.users.Admin;
import CleanCod.users.Reader;
import CleanCod.users.Staff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class FileSystem {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
                        Admin.adminServices();
                    } else if (users.equals("S")) {
                        System.out.println("Staff Services \n");
                        Staff.staffServices();
                    } else if (users.equals("R")) {
                        System.out.println("Reader Services");
                        Reader.readerServices();
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
