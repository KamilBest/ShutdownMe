package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.concurrent.TimeUnit;

/**
 * RemainingTime
 * Class responsible for display remaining time to shutdown after "run" button click.
 * <p>
 * Created by kamil on 4/13/17.
 */
public class RemainingTime implements Runnable {
    /**
     * Contains system current time.
     */
    private long currentTime;

    /**
     * Contains the time after the addition hours to shutdown system.
     */
    private long shutdownTime;

    @FXML
    private Label remainingTimeLabel;

    public RemainingTime(Label remainingTimeLabel) {
        this.remainingTimeLabel = remainingTimeLabel;
    }

    /**
     * Sets beginning time from which countdown is starting.
     *
     * @param value - selected time in hours to shutdown (value from slider).
     */
    public void setTimer(long value) {
        currentTime = System.currentTimeMillis();
        shutdownTime = currentTime + (value * 3600 * 1000);
    }

    /**
     * Calculates remaining time.
     * Substracting planned shutdown time and current time.
     *
     * @return remaining time in ms.
     */
    private long calculateRemainingTime() {
        currentTime = System.currentTimeMillis();
        return shutdownTime - currentTime;

    }

    /**
     * Formats time in milliseconds to hours, minutes and seconds (HH:mm:ss)
     *
     * @param millis - takes time in ms (in this situation it taking calculated value from method above)
     * @return - formatted time in String
     */
    private String formatTime(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        return hms;
    }

    /**
     * Implemented method from Runnable class
     * It displays remaining time in label in new Thread. Refreshes value every 1s.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Platform.runLater(() -> remainingTimeLabel.setText(formatTime(calculateRemainingTime())));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}

