package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kamil on 4/12/17.
 */
public class CurrentTime implements Runnable {
    @FXML
    private TextField currentTimeField;
    public CurrentTime(TextField currentTimeField)
    {
        this.currentTimeField=currentTimeField;
    }

    @Override
        public void run() {
            try {
                while(true){
                    SimpleDateFormat sdate = new SimpleDateFormat("HH:mm:ss");
                    String dateString = sdate.format(new Date());
                    Platform.runLater(() -> currentTimeField.setText(dateString));
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
            }
        }
}
