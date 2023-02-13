package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import weatherApplication.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController extends BaseController implements Initializable {

    @FXML
    VBoxController leftController;

    @FXML
    VBoxController rightController;

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
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    @FXML
    void currentLocationTextFieldAction() {
    }

    @FXML
    void destinationTextFieldAction() {
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void currentLocationButtonAction()  {
        String currentLocation = currentLocationTextField.getText();
        currentLocation = setFirstCapitalLetter(currentLocation);

        try {
            currentLocationTextField.clear();
            leftController.showCurrentAndForecastWeather(currentLocation);
            leftAnchorPane.setVisible(true);
            errorLabel.setText("");
            currentLocationLabel.setText(currentLocation);
        } catch(IllegalArgumentException | NullPointerException e) {
            if(currentLocation.equals("")) {
                errorLabel.setText("Please provide your current location!");
            } else {
                errorLabel.setText("Wrong city name or connection has been interrupted!" );
                leftAnchorPane.setVisible(false);
                currentLocationLabel.setText("Current Location");
            }
        }
    }

    @FXML
    void destinationButtonAction() throws IOException {
        String destination = destinationTextField.getText();
        destination = setFirstCapitalLetter(destination);

        try {
            destinationTextField.clear();
            rightController.showCurrentAndForecastWeather(destination);
            rightAnchorPane.setVisible(true);
            errorLabel.setText("");
            destinationLabel.setText(destination);
        } catch(IllegalArgumentException | NullPointerException e) {
            if(destination.equals("")) {
                errorLabel.setText("Please provide your destination!");
            } else {
                errorLabel.setText("Wrong city name or connection has been interrupted!" );
                rightAnchorPane.setVisible(false);
                destinationLabel.setText("Destination");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftAnchorPane.setVisible(false);
        rightAnchorPane.setVisible(false);
    }

    public String setFirstCapitalLetter(String cityName) {
        String firstUpperCase = "";
        if (!cityName.equals("")) {
            firstUpperCase = cityName.substring(0, 1).toUpperCase()
                    + cityName.substring(1).toLowerCase();
        }
        return firstUpperCase;
    }
}

