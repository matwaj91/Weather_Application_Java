package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import weatherApplication.model.WeatherService;
import weatherApplication.model.WeatherServiceFactory;
import weatherApplication.model.client.SpecificWeatherClient;
import weatherApplication.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController extends BaseController implements Initializable {

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

    WeatherService weatherService;

    SpecificWeatherClient particularWeatherClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }

    public MainWindowController(ViewFactory viewFactory, String fxmlName)  {
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
            SpecificWeatherClient.getCurrentWeather(currentLocation);

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
            Parent parent = viewFactory.passParticularWindow();
            vBox.getChildren().add(parent);
        }
    }
}
