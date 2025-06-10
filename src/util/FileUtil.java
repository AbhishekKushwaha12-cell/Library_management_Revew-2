package util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    private static final String FILE_NAME = "books.dat";

    public static void initializeFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create data file: " + e.getMessage());
            }
        }
    }
}