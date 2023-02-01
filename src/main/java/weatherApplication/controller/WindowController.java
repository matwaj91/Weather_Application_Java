package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import weatherApplication.view.ViewFactory;

public class WindowController extends BaseController{

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
        String currentLocation = currentLocationTextField.getText();
        currentLocationTextField.clear();
        currentLocationLabel.setText(currentLocation);

    }

    @FXML
    void currentLocationTextFieldAction() {}

    @FXML
    void destinationButtonAction() {
        String destination = destinationTextField.getText();
        destinationTextField.clear();
        destinationLabel.setText(destination);
    }

    @FXML
    void destinationTextFieldAction() {}
}
