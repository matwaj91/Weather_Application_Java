package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import weatherApplication.view.ViewFactory;

public class WindowController extends BaseController{

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

    public WindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void currentLocationButtonAction() {
        if(currentLocationTextField.getText().equals("")) {
            currentLocationLabel.setText("Current Location");
            errorLabel.setText("Please provide your current location!");
        } else {
            String currentLocation = currentLocationTextField.getText();
            currentLocationTextField.clear();
            currentLocationLabel.setText(currentLocation);
            errorLabel.setText("");
        }
    }

    @FXML
    void currentLocationTextFieldAction() {}

    @FXML
    void destinationButtonAction() {
        if(destinationTextField.getText().equals("")) {
            destinationLabel.setText("Destination");
            errorLabel.setText("Please provide your destination!");
        } else {
            String currentLocation = destinationTextField.getText();
            destinationTextField.clear();
            destinationLabel.setText(currentLocation);
            errorLabel.setText("");
        }
    }

    @FXML
    void destinationTextFieldAction() {}
}
