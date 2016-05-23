/*
 * 
 */
package recall.util;

import com.google.common.io.Files;

/**
 *
 * @author wellington
 */
public class RunConfiguration {

    private String contractFileName;
    private String resultFileName;

    private boolean exportDecompositions;
    private boolean exportAutomaton;
    private boolean continueOnConflict;
    private boolean exportMinAutomaton;
    private boolean usePrunning;
    private String decompositionsFileName;
    private String automatonFileName;
    private LogLevel logLevel;
    private String globalLogFilename;

    public RunConfiguration() {
        exportAutomaton = exportDecompositions = false;
        logLevel = LogLevel.NORMAL;
        continueOnConflict = false;
        //useSelfActions = false;
        usePrunning = true;
    }

    //<editor-fold defaultstate="collapsed" desc="properties">
    public String getContractFileName() {
        return contractFileName;
    }

    public void setContractFileName(String contractFileName) {
        this.contractFileName = contractFileName;
    }

    public boolean isExportDecompositions() {
        return exportDecompositions;
    }

    public void setExportDecompositions(boolean exportDecompositions) {
        this.exportDecompositions = exportDecompositions;
    }

    public boolean isExportAutomaton() {
        return exportAutomaton;
    }

    public void setExportAutomaton(boolean exportAutomaton) {
        this.exportAutomaton = exportAutomaton;
    }

    public String getDecompositionsFileName() {
        return decompositionsFileName;
    }

    public void setDecompositionsFileName(String decompositionsFileName) {
        this.decompositionsFileName = decompositionsFileName;
    }

    public String getAutomatonFileName() {
        return automatonFileName;
    }

    public void setAutomatonFileName(String automatonFileName) {
        this.automatonFileName = automatonFileName;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName) {
        this.resultFileName = resultFileName;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getGlobalLogFilename() {
        return globalLogFilename;
    }

    public void setGlobalLogFilename(String globalLogFilename) {
        this.globalLogFilename = globalLogFilename;
    }

    public void setContinueOnConflict(boolean continueOnConflict) {
        this.continueOnConflict = continueOnConflict;
    }

    public boolean isContinueOnConflict() {
        return continueOnConflict;
    }

    public boolean isExportMinAutomaton() {
        return exportMinAutomaton;
    }

    public void setExportMinAutomaton(boolean exportMinAutomaton) {
        this.exportMinAutomaton = exportMinAutomaton;
    }
    
    

//    public boolean isUseSelfActions() {
//        return useSelfActions;
//    }
//
//    public void setUseSelfActions(boolean useSelfActions) {
//        this.useSelfActions = useSelfActions;
//    }
    public boolean isUsePrunning() {
        return usePrunning;
    }

    public void setUsePrunning(boolean usePrunning) {
        this.usePrunning = usePrunning;
    }

    //</editor-fold>
    @Override
    public String toString() {
        return "RunConfiguration{" + "contractFileName=" + contractFileName + ", resultFileName=" + resultFileName + ", exportDecompositions=" + exportDecompositions + ", exportAutomaton=" + exportAutomaton + ", continueOnConflict=" + continueOnConflict + ", usePrunning=" + usePrunning + ", decompositionsFileName=" + decompositionsFileName + ", automatonFileName=" + automatonFileName + ", logLevel=" + logLevel + ", globalLogFilename=" + globalLogFilename + '}';
    }

    public static RunConfiguration getConfig(String file) {
        RunConfiguration config = new RunConfiguration();
        config.setContractFileName(file);
        config.setExportAutomaton(true);
        config.setAutomatonFileName(Files.getNameWithoutExtension(config.getContractFileName()) + ".dot");
        config.setExportDecompositions(true);
        config.setDecompositionsFileName(Files.getNameWithoutExtension(config.getContractFileName()) + ".csv");
        config.setResultFileName(Files.getNameWithoutExtension(config.getContractFileName()) + ".result");
        config.setLogLevel(LogLevel.VERBOSE);
        config.setContinueOnConflict(false);
        config.setUsePrunning(true);
        config.setGlobalLogFilename("debug.log");
        return config;
    }

}
