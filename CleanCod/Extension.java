package CleanCod;

public class Extension {
    static final int beginIndex=0;
    static final char lastIndexOf ='.';
    public static String extension(String path) {
        String extension = "";

        int index = path.lastIndexOf(lastIndexOf);
        if (index > beginIndex) {
            extension = path.substring(index + 1);
        }
        return extension;
    }

}
