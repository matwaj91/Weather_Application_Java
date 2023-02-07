package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import weatherApplication.model.WeatherParameters;
import weatherApplication.view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ParticularForecastWindowController extends BaseController implements Initializable {

    WeatherParameters weatherParameters;

    @FXML
    private Label day;

    @FXML
    private ImageView image;

    @FXML
    private Label particularForecastLabel;

    @FXML
    private Label pressure;

    @FXML
    private Label temperature;

    @FXML
    private Label wind;

    public void setWeatherParameters(WeatherParameters weatherParameters) {
        this.weatherParameters = weatherParameters;
    }

    public ParticularForecastWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
