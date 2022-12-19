import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class deepthenk {
   /* public static void add2(Path path ) throws IOException {
        File yourFile = new File(path);
        yourFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(yourFile, false);
    }*/
    static void writeFile1(int version, String fileName, String path){

        try {

            File file = new File(path);

            if (!file.exists())
                System.out.println("file.createNewFile();");

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
    static void writeFile(int version, String fileName, Path path){

        try {

            File file = new File("path.toUri()");

            if (!file.exists())
                System.out.println("file.createNewFile();");
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
    static void readable(int version , String fileName , String path ){
        try {
            int counter=0;
            File myObj = new File("src/.exefiles/Dectionary");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] arrOfStr = data.split(" ");
                Scanner myObj1 = new Scanner(System.in);
                for (String a : arrOfStr){
                    if (a.equals(fileName)){
                        counter++;
                        System.out.println("replace the name to add! or i can add the exist name plus(1)");
                        System.out.println("enter your choice 1 :for default else you fall it");
                        int ch =0 ;
                        ch= myObj1.nextInt();
                        if (ch==1){
                            fileName+="("+counter+")";
                            fileName=encryption(fileName);
                            File yourFile = new File(path);
                            yourFile.createNewFile(); // if file already exists will do nothing
                            FileOutputStream oFile = new FileOutputStream(yourFile, false);

                            version ++;
                            path= ("src/.exefiles/Dectionary");
                            writeFile1(version,fileName,path);
                            break;
                        }
                        else {

                            System.out.println("Enter name for file");
                            fileName= myObj1.next();
                            System.out.println("file name :: " + fileName);
                            fileName=encryption(fileName);
                            writeFile1(version,fileName,path);
                        }
                    }
                    if (a.equals("version(0)")){
                        System.out.println("the file is exist version ");
                        version ++;
                        writeFile1(version,fileName,path);
                        break;
                    }

                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

static String read(String path) {
    try {
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            return data;
        }
        myReader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
    return null;
}

    public static final String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(2);
    }

    public static final String  encryption(String fileName) {
        return capitalize(fileName);
    }

 /*   public static void main(String[] args)
    {
        int version=0;
        String fileName="mosaabtwo";
        Path path = Paths.get("src/added file");

        readable(version,fileName,path);
    }*/

    public static void main(String[] args) {

    try {

        Path path1 = Paths.get("src/.exefiles");

        //java.nio.file.Files;
        Files.createDirectories(path1);

        System.out.println("Directory is created!");

        File yourFile = new File("src/.exefiles/Dectionary");
        yourFile.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(yourFile, true);
        int version=0;
        String fileName="mosaabtwo";


         Path path = Paths.get("src/added file");
      String CAstString= String.valueOf(path);

      String data;
       data=read(CAstString);

        CAstString  =pathgenaretor(fileName,path1);

        File yourFile1 = new File(CAstString);
        yourFile1.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile1 = new FileOutputStream(yourFile1, true);

       Write(data, CAstString);
      readable(0,fileName,"src/.exefiles/Dectionary");


    } catch (IOException e) {

        System.err.println("Failed to create directory!" + e.getMessage());

    }
}

    private static String pathgenaretor(String fileName, Path path1) {
        return path1+"/"+fileName;
    }

    private static void Write(String data, String path) {
        try {
            String content =data;

            File file = new File(path);


            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
