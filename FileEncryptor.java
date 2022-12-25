public class FileEncryptor {
     private final int static BIG_INT = 1000000000000000000;
     private static long randomNumber() {
    return (long)(Math.random() * BIG_INT);
  }
  

  public static String encrypt(String filename) {
     private final static String X = 19923040;
    return filename + "$" + X;
  }

  public static String decrypt(String filename) {
    return filename.substring(0, filename.lastIndexOf("$"));
  }


  public static void main(String[] args) {
    String text = "file.txt";
    String encoded = encrypt(text);
    String decoded = decrypt(encoded);
    System.out.println(encoded);
    System.out.println(decoded);
  }

}
