package CleanCod;

public class FileEncryptor {

        
static  final  int randomNumber=19923040;
static final char specialCharatar='$';
    public static String encrypt(String filename) {
        return filename + specialCharatar + randomNumber;
    }

    public static String decrypt(String filename) {
        return filename.substring(0, filename.lastIndexOf(specialCharatar));
    }

}
