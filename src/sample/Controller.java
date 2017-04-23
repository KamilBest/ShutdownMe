package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField currentTimeTextField;
    @FXML
    private TextField timerValueTextField;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label remainingTimeLabel;

    /**
     * contains selected time in hour to shutdown from slider
     */
    private long sliderValue;

    /**
     * Initialize method. Starts thread to display currentTime.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable currentTime = new CurrentTime(currentTimeTextField);
        Thread currentTimeThread = new Thread(currentTime);
        currentTimeThread.start();
        sliderValueSetter();
    }

    /**
     * Takes value from slider and put the value to label and textfield.
     */
    public void sliderValueSetter() {
        sliderValue = (long) timeSlider.getValue();
        timerValueTextField.setText(String.valueOf(sliderValue + " hour"));
        remainingTimeLabel.setText(String.valueOf(sliderValue + " hour"));
    }

    /**
     * Sets shutdown time after click "run" button.
     * Runs thread to display remaining time.
     * Disables slider.
     */
    public void setOperation() {


        RemainingTime remainingTime = new RemainingTime(remainingTimeLabel);
        remainingTime.setTimer(sliderValue);
        Runnable remainingTimeRunnable = remainingTime;
        Thread remainingTimeThread = new Thread(remainingTimeRunnable);
        remainingTimeThread.start();
        SystemOperation systemOperation = new SystemOperation();
        systemOperation.setShutdown(sliderValue);

        timeSlider.setDisable(true);
    }

    /**
     * Cancels shutdown.
     * Enables slider.
     */
    public void cancelShutdown() {
        timeSlider.setDisable(false);
        SystemOperation systemOperation = new SystemOperation();
        systemOperation.cancelOperation();
    }
}
