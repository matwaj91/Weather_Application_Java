package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import weatherApplication.model.WeatherParameters;
import weatherApplication.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class MainWindowController extends BaseController implements Initializable{

    @FXML
    ParticularWeatherWindowController leftController;

    @FXML
    private Label errorLabel;

    @FXML
    private Button currentLocationButton;

    @FXML
    private TextField currentLocationTextField;

    @FXML
    private Label currentLocationLabel;

    @FXML
    private Button destinationButton;

    @FXML
    private TextField destinationTextField;

    @FXML
    private Label destinationLabel;

    @FXML
    private VBox leftVBox;

    @FXML
    private VBox rightVBox;

    private List<WeatherParameters> weatherForecast;


    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void currentLocationTextFieldAction() {
    }

    @FXML
    void destinationTextFieldAction() {
    }

    @FXML
    void currentLocationButtonAction() throws IOException {
        if (currentLocationTextField.getText().equals("")) {
            errorLabel.setText("Please provide your current location!");
        } else {
            String currentLocation = currentLocationTextField.getText();
            currentLocationTextField.clear();
            currentLocationLabel.setText(currentLocation);
            errorLabel.setText("");
            leftVBox.setVisible(true);
            weatherForecast = leftController.getWeatherFromClient(currentLocation);
            for (int i = 0; i < 5; i++) {
                leftVBox.getChildren().add(new AnchorPane(leftController.fillWeatherForecastWindow(weatherForecast)));
            }
        }
    }

    @FXML
    void destinationButtonAction() throws IOException {
        if (destinationTextField.getText().equals("")) {
            errorLabel.setText("Please provide your destination!");
        } else {
            String destination = destinationTextField.getText();
            destinationTextField.clear();
            destinationLabel.setText(destination);
            errorLabel.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       leftVBox.setVisible(false);
    }
}

