import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    static void writeFile(int version, String fileName, Path path){

        String str
                = "File name " ;
        String a=" ";
        a+= String.valueOf(path);

        str +=fileName;
        str+=" version(";
        str+=version;
        str+=") path ";
        str+=a;

        byte[] arr = str.getBytes();

        try {

            Files.write(path, arr);
        }

        catch (IOException ex) {

            System.out.print("Invalid Path");
        }
    }
    static void readable(int version , String fileName , Path path ){
        try {
            int counter=0;
            File myObj = new File(path.toUri());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] arrOfStr = data.split(" ");
                Scanner myObj1 = new Scanner(System.in);
                for (String a : arrOfStr){
                    if (a.equals(fileName)){
                        counter++;
                        System.out.println("the file is exist filename ");
                        System.out.println("replace the name to add! or i can add the exist name plus(1)");
                        System.out.println("enter your choice 1 :for default else you fall it");
                        int ch =0 ;
                        ch= myObj1.nextInt();
                        if (ch==1){
                            fileName+="("+counter+")";
                            writeFile(version,fileName,path);
                            continue;
                        }
                        else {

                            System.out.println("Enter name for file");
                            fileName= myObj1.next();
                            System.out.println("file name :: " + fileName);
                            writeFile(version,fileName,path);
                        }
                    }
                    if (a.equals("version(0)")){
                        System.out.println("the file is exist version ");
                        version ++;
                        writeFile(version,fileName,path);
                        break;
                    }

                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        int version=0;
        String fileName="test1";
        Path path = Paths.get("src/added file");

        readable(version,fileName,path);
    }
}
