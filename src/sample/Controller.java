package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    RemainingTime remainingTime;
    @FXML
    private Label currentTimeLabel;
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
        Runnable currentTime = new CurrentTime(currentTimeLabel);
        Thread currentTimeThread = new Thread(currentTime);
        currentTimeThread.start();
        sliderValueSetter();
        remainingTime = new RemainingTime(remainingTimeLabel);
    }

    /**
     * Takes value from slider and put the value to label and textfield.
     */
    public void sliderValueSetter() {
        sliderValue = (long) timeSlider.getValue();
        remainingTimeLabel.setText(String.valueOf(sliderValue + " hour"));
    }

    /**
     * Sets shutdown time after click "run" button.
     * Runs thread to display remaining time.
     * Disables slider.
     */
    public void setOperation() {

        remainingTimeLabel.setStyle("-fx-font-family: System;\n" +
                "    -fx-font-size: 45px;\n" +
                "    -fx-text-fill: #62D000;\n" +
                "    -fx-font-weight: bold;");

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
        remainingTimeLabel.setText("Timer turned off.");
        remainingTime.resetTimer();
        remainingTimeLabel.setStyle("-fx-font-family: System;\n" +
                "    -fx-font-size: 45px;\n" +
                "    -fx-text-fill: #fff;\n" +
                "    -fx-font-weight: bold;");
    }


    /**
     * Close application button
     *
     * @param event-button click
     */
    public void handleCloseButtonAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

    }

    /**
     * Minimize application button
     *
     * @param event-button click
     */
    public void handleMinimizeButton(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).setIconified(true);
    }

}
