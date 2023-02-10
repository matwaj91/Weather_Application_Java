package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
            leftController.showCurrentAndForecastWeather(currentLocation);
            leftAnchorPane.setVisible(true);
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
        leftAnchorPane.setVisible(false);
        rightAnchorPane.setVisible(false);
    }
}

