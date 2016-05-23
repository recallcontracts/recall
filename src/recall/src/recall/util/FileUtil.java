/*
 * 
 */
package recall.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author wellington
 */
public class FileUtil {

    public static boolean writeToFile(String filename, String... lines) {
        Path p = Paths.get(filename);
        try (BufferedWriter bw = new BufferedWriter(Files.newBufferedWriter(p))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
