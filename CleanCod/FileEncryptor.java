package CleanCod;

public class FileEncryptor {

        public static String encrypt(String filename) {
            return filename + "$" + 19923040;
        }

        public static String decrypt(String filename) {
            return filename.substring(0, filename.lastIndexOf("$"));
        }


}