package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamil on 4/13/17.
 */
public class Timer implements Runnable {
    long currentTime;
    long shutdownTime;
    long addedTime;
    @FXML
    private Label timerLabel;

    public Timer(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public void setTimer(long value) {
        currentTime = System.currentTimeMillis();

        addedTime = value * 3600 * 1000;
        shutdownTime = currentTime + addedTime;
        SimpleDateFormat timerFormat = new SimpleDateFormat("HH:mm:ss");


        System.out.println("Current time");
        System.out.println(currentTime);
        System.out.println(timerFormat.format(currentTime));

        System.out.println("Shutdown time");
        System.out.println(addedTime);
        System.out.println(timerFormat.format(addedTime));

        System.out.println("Difference");
        System.out.println(calculateDifference());
        System.out.println(formatTime(calculateDifference()));
        System.out.println(timerFormat.format(shutdownTime));
    }

    private long calculateDifference() {
        currentTime = System.currentTimeMillis();
        return shutdownTime - currentTime;

    }

    private String formatTime(long millis) {
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
        return hms;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Platform.runLater(() -> timerLabel.setText(formatTime(calculateDifference())));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}

