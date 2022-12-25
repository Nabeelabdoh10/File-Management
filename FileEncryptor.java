public class FileEncryptor {

  private static long randomNumber() {
    return (long)(Math.random() * 1000000000000000000 L);
  }

  public static String encrypt(String filename) {
    return filename + "$" + 19923040;
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
