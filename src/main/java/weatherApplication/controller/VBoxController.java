package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;
import weatherApplication.model.WeatherService;
import weatherApplication.model.client.SpecificWeatherClient;
import weatherApplication.view.ViewFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VBoxController extends BaseController {

    @FXML
    private VBox vBox;

    @FXML
    private ParticularWeatherWindowController firstWindowController;

    @FXML
    private ParticularWeatherWindowController secondWindowController;

    @FXML
    private ParticularWeatherWindowController thirdWindowController;

    @FXML
    private ParticularWeatherWindowController fourthWindowController;

    @FXML
    private ParticularWeatherWindowController fifthWindowController;


    private WeatherParameters currentWeather;
    private List<WeatherParameters> weatherForecast;


    public VBoxController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public VBoxController() {
    }

    @FXML
    void showCurrentAndForecastWeather(String cityName) {
        WeatherService weatherService = new WeatherService(new SpecificWeatherClient());
        Weather weather;

        try {
            weather = weatherService.getWeather(cityName);
            currentWeather = weather.getCurrentWeather();
            weatherForecast = weather.getWeatherForecast();
        } catch (Exception e) {
            System.out.println("Error");
        }

        firstWindowController.fillCurrentWeatherWindow(currentWeather);
        secondWindowController.fillWeatherForecastWindow(weatherForecast, 1);
        thirdWindowController.fillWeatherForecastWindow(weatherForecast, 2);
        fourthWindowController.fillWeatherForecastWindow(weatherForecast, 3);
        fifthWindowController.fillWeatherForecastWindow(weatherForecast, 4);
    }
}
