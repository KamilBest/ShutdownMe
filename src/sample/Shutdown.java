package sample;

import java.io.IOException;

/**
 * Shutdown class
 * Responsible for setting and cancelling shutdown.
 * <p>
 * Created by kamil on 4/12/17.
 */
public class Shutdown {

    /**
     * Sets shutdown
     *
     * @param value - value in hours from slider
     */
    public void setShutdown(double value) {
        try {
            Process processShutdown = Runtime.getRuntime().exec("shutdown -s -t " + (value * 3600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels shutdown
     */
    public void cancelShutdown() {
        try {
            Process processCancel = Runtime.getRuntime().exec("shutdown -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
