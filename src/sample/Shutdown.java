package sample;

import java.io.IOException;

/**
 * Created by kamil on 4/12/17.
 */
public class Shutdown {

    public void setShutdown(double value) {
        try {
            Process processShutdown = Runtime.getRuntime().exec("shutdown -s -t " + (value * 3600));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelShutdown() {
        try {
            Process processCancel = Runtime.getRuntime().exec("shutdown -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
