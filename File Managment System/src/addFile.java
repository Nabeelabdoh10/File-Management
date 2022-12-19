import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class addFile {
    static void writeFile(int version, String fileName, Path path){

        try {

            File file = new File("src/added file");

            if (!file.exists())
                file.createNewFile();
            String str = "File name " ;
            String a=" ";
            a+= String.valueOf(file);
            str +=fileName;
            str+=" version(";
            str+=version;
            str+=") path ";
            str+=a;

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void readable(int version , String fileName , Path path ){
        try {
            int counter=0;
            File myObj = new File("src/added file");
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
                            fileName=encryption(fileName);
                            writeFile(version,fileName,path);

                            version ++;
                            writeFile(version,fileName,path);
                            break;
                        }
                        else {

                            System.out.println("Enter name for file");
                            fileName= myObj1.next();
                            System.out.println("file name :: " + fileName);
                            fileName=encryption(fileName);
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
    public static final String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(2);
    }

    public static final String  encryption(String fileName) {
        return capitalize(fileName);
    }

    public static void main(String[] args)
    {
        int version=0;
        String fileName="mosaabtwo";
        Path path = Paths.get("src/added file");

        readable(version,fileName,path);
    }
}
