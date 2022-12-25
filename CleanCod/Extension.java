package CleanCod;

public class Extension {
    public static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf('.');
        if (index > 0) {
            extension = path.substring(index + 1);
        }
        return extension;
    }
}
