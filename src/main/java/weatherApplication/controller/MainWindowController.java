package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import weatherApplication.view.ViewFactory;

import java.io.IOException;

public class MainWindowController extends BaseController{

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

    @FXML
    private ParticularForecastWindowController particularForecastWindowController;

    public MainWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    void currentLocationTextFieldAction() {}

    @FXML
    void destinationTextFieldAction() {}

    @FXML
    void currentLocationButtonAction() throws IOException {
        if (currentLocationTextField.getText().equals("")) {
            errorLabel.setText("Please provide your current location!");
        } else {
            String currentLocation = currentLocationTextField.getText();
            currentLocationTextField.clear();
            currentLocationLabel.setText(currentLocation);
            errorLabel.setText("");
            fillVBox(leftVBox);
        }
    }

    @FXML
    void destinationButtonAction() throws IOException {
        if(destinationTextField.getText().equals("")) {
            errorLabel.setText("Please provide your destination!");
        } else {
            String currentLocation = destinationTextField.getText();
            destinationTextField.clear();
            destinationLabel.setText(currentLocation);
            errorLabel.setText("");
            fillVBox(rightVBox);
        }
    }

    private void fillVBox(VBox vBox) throws IOException {
        vBox.getChildren().clear();
        for(int i = 0; i < 5; i++) {
            Parent parent = viewFactory.displayWeatherForecast();
            vBox.getChildren().add(parent);
        }
    }
}
