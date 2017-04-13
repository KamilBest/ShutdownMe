package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

/**
 * Created by kamil on 4/13/17.
 */
public class Timer implements Runnable {
    long currentTime;
    long shutdownTime;
    @FXML
    private Label timerLabel;

    public Timer(Label timerLabel) {
        this.timerLabel = timerLabel;
    }

    public void setTimer(long value) {
        currentTime = System.currentTimeMillis();

        shutdownTime = currentTime + (value * 3600 * 1000);
        SimpleDateFormat timerFormat = new SimpleDateFormat("HH:mm:ss");

        System.out.println("Current time");
        System.out.println(currentTime);
        System.out.println(timerFormat.format(currentTime));

        System.out.println("Shutdown time");
        System.out.println(shutdownTime);
        System.out.println(timerFormat.format(shutdownTime));

        System.out.println("Difference");
        System.out.println(calculateDifference());
        System.out.println(timerFormat.format(calculateDifference()));
        System.out.println(timerFormat.format(shutdownTime));
    }

    private long calculateDifference() {
        currentTime = System.currentTimeMillis();
        return shutdownTime - currentTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SimpleDateFormat timerFormat = new SimpleDateFormat("HH:mm:ss");

                Platform.runLater(() -> timerLabel.setText(timerFormat.format(calculateDifference())));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}

