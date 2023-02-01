package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import weatherApplication.view.ViewFactory;

public class WindowController extends BaseController{

    @FXML
    private Button currentLocationButton;

    @FXML
    private TextField currentLocationTextField;

    @FXML
    private Button destinationButton;

    @FXML
    private TextField destinationTextField;

    public WindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void currentLocationButtonAction() {
        System.out.println(currentLocationTextField.getText());
        destinationTextField.clear();
    }

    @FXML
    void currentLocationTextFieldAction() {

    }

    @FXML
    void destinationButtonAction() {
        System.out.println(destinationTextField.getText());
        destinationTextField.clear();
    }

    @FXML
    void destinationTextFieldAction() {

    }
}
