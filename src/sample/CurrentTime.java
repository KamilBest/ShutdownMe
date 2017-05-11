package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CurrentTime
 * Class responsible for display current time in textField.
 * It works on new Thread which refresh the time every 1s.
 * <p>
 * Created by kamil on 4/12/17.
 */
public class CurrentTime implements Runnable {
    @FXML
    private Label currentTimeLabel;

    public CurrentTime(Label currentTimeLabel) {
        this.currentTimeLabel = currentTimeLabel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String dateString = simpleDateFormat.format(new Date());
                Platform.runLater(() -> currentTimeLabel.setText(dateString));
                Thread.sleep(1000);
            }
        } catch (Exception e) {
        }
    }
}
