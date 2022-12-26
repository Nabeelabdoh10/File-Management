import java.io.*;
import java.util.ArrayList;
public class LoggerOne {

    private static LoggerOne instance;
    static ArrayList<String> logsArray = new ArrayList<String>(5);

    private LoggerOne() {}

    public static LoggerOne getInstance() {

        if (instance == null) {
            instance = new LoggerOne();
        }
        return instance;
    }

    public static void logInfo(String message) throws IOException {
        FileWriter out = new FileWriter("logs.txt", true);
        out.write(java.time.LocalDateTime.now() + " [INFO] " + message);
        out.close();

    }

    public static void logDebug(String message) throws IOException {
        FileWriter out = new FileWriter("logs.txt", true);
        out.write(java.time.LocalDateTime.now() + " [Debug] " + message);
        out.close();

    }

    public static void logWarning(String message) throws IOException {
        FileWriter out = new FileWriter("logs.txt", true);
        out.write(java.time.LocalDateTime.now() + " [Warn] " + message);
        out.close();
    }

    public static void logError(String message) throws IOException {
        FileWriter out = new FileWriter("logs.txt", true);
        out.write(java.time.LocalDateTime.now() + " [Error] " + message);
        out.close();
    }

}