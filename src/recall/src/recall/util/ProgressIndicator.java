/*
 * 
 */
package recall.util;

/**
 *
 * @author wellington
 */
public class ProgressIndicator implements Runnable {

    private final int SLEEP_INT = 100;
    private long init = 0;
    private long elapsedTime = 0;
    private boolean started;

    private String processMessage;
    private String completeMessage;

    public ProgressIndicator(String processMessage, String completeMessage) {
        this.started = false;
        this.processMessage = processMessage;
        this.completeMessage = completeMessage;
    }

    public synchronized void start() {
        setStarted(true);
        init = System.currentTimeMillis();
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public synchronized void stop() {
        setStarted(false);
        try {
            elapsedTime = System.currentTimeMillis() - init;
            Thread.sleep(SLEEP_INT);
        } catch (Exception e) {
            
        }
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getProcessMessage() {
        return processMessage;
    }

    public void setProcessMessage(String processMessage) {
        this.processMessage = processMessage;
    }

    public String getCompleteMessage() {
        return completeMessage;
    }

    public void setCompleteMessage(String completeMessage) {
        this.completeMessage = completeMessage;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
    
    @Override
    public void run() {
        int x = 0;
        while (isStarted()) {
            message("\r" + processMessage + " ");
            try {
                Thread.sleep(SLEEP_INT);
            } catch (Exception e) {
                break;
            }
            x++;
            for (int k = 0; k < x; k++) {
                System.out.print(".");
            }
            System.out.print("     ");
            if (x > 3) {
                x = 0;
            }

        }
        System.out.println(completeMessage);
    }

    private void message(String str) {
        for (int i = 0; i < processMessage.length() + 5; i++) {
            System.out.print(" ");
        }
        System.out.print(str);
    }

}
