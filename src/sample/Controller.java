package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    @FXML
    private TextField currentTimeField;
    @FXML
    private TextField timerValueField;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label timerLabel;

    private long sliderValue;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable runnable = new CurrentTime(currentTimeField);
        Thread thread = new Thread(runnable);
        thread.start();
        sliderValueSetter();
    }

    public void sliderValueSetter() {
        sliderValue = (long) timeSlider.getValue();
        timerValueField.setText(String.valueOf(sliderValue + " hour"));
        timerLabel.setText(String.valueOf(sliderValue + " hour"));
    }

    public void setShutdown() {
        Shutdown shutdown = new Shutdown();
        shutdown.setShutdown(sliderValue);
        Timer timer = new Timer(timerLabel);
        timer.setTimer(sliderValue);
        Runnable runnable1 = timer;
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        timeSlider.setDisable(true);
    }

    public void cancelShutdown() {
        timeSlider.setDisable(false);

        Shutdown shutdown = new Shutdown();
        shutdown.cancelShutdown();
    }
}
