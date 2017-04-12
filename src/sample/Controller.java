package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{


    @FXML
    private TextField currentTimeField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Runnable runnable = new CurrentTime(currentTimeField);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
