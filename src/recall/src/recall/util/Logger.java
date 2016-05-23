/*
 * 
 */
package recall.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wellington
 */
public class Logger {

    private LogLevel level;
    private RunConfiguration configuration;
    private String globalLogFileName;

    //<editor-fold defaultstate="collapsed" desc="properties">
    public RunConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(RunConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getGlobalLogFileName() {
        return globalLogFileName;
    }

    public void setGlobalLogFileName(String globalLogFileName) {
        this.globalLogFileName = globalLogFileName;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    //</editor-fold>
    private static Logger instance;

    public static boolean configure(RunConfiguration configuration) {
        try {
            instance = new Logger(configuration);
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static boolean terminate() {
        try {
            instance.close();
            instance = null;
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static Logger getInstance() {
        return instance;
    }

    private BufferedWriter bwGlobal;
    private BufferedWriter bwLocal;
    private String contractName;

    public Logger(RunConfiguration configuration) throws IOException {
        this.level = configuration.getLogLevel();
        this.configuration = configuration;
        this.globalLogFileName = configuration.getGlobalLogFilename();
        initFiles();
    }

    public void logException(Exception e) {
        if (e == null) {
            return;
        }
        //log(LogType.MINIMAL, e.getCause().toString());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log(LogType.NECESSARY, sw.toString());
    }

    public void log(LogType type, String text) {
        boolean g = true;
        boolean l;
        if (level == LogLevel.NORMAL) {
            switch (type) {
                case MINIMAL: {
                    g = write(bwGlobal, String.format("%s [%s]: %s", getDateInfo(), contractName, text));
                    System.out.println(text);
                    break;
                }
                case NECESSARY: {
                    g = write(bwGlobal, String.format("%s [%s]: %s", getDateInfo(), contractName, text));
                    break;
                }
                case ADDITIONAL: {
                    break;
                }
            }
            l = write(bwLocal, String.format("%s: %s", getDateInfo(), text));
        } else {
            l = write(bwLocal, String.format("%s: %s", getDateInfo(), text));
            g = write(bwGlobal, String.format("%s [%s]: %s", getDateInfo(), contractName, text));
            System.out.println(text);
        }

        if (!l) {
            System.err.println("Failed to Write in Local Log File: " + configuration.getResultFileName());
            System.out.println(text);
        }
        if (!g) {
            System.err.println("Failed to Write in Global Log File: " + globalLogFileName);
            System.out.println(text);
        }
    }

    private boolean write(BufferedWriter bw, String... lines) {
        try {
            for (String l : lines) {
                bw.write(format(l));
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void initFiles() throws IOException {
        bwGlobal = new BufferedWriter(
                Files.newBufferedWriter(Paths.get(globalLogFileName), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND, StandardOpenOption.WRITE));
        bwLocal = new BufferedWriter(
                Files.newBufferedWriter(Paths.get(configuration.getResultFileName()),
                        StandardOpenOption.TRUNCATE_EXISTING,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE));
        contractName = com.google.common.io.Files.getNameWithoutExtension(configuration.getContractFileName());
    }

    public void close() throws IOException {
        if (bwGlobal != null) {
            bwGlobal.flush();
            bwGlobal.close();
            bwGlobal = null;
        }
        if (bwLocal != null) {
            bwLocal.flush();
            bwLocal.close();
            bwLocal = null;
        }

    }

    private String getDateInfo() {
        return new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").format(new Date());
    }

    private String format(String l) {
        return l.replaceAll("\\u001B\\[[0-9]+m", "");
    }
}
