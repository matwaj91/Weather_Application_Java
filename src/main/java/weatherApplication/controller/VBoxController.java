package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;
import weatherApplication.model.WeatherService;
import weatherApplication.model.client.SpecificWeatherClient;

import java.util.List;

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

    private List<WeatherParameters> weatherData;

    public VBoxController() {}

    @FXML
    void showCurrentAndForecastWeather(String cityName) {
        WeatherService weatherService = new WeatherService(new SpecificWeatherClient());
        Weather weather;

        try {
            weather = weatherService.getWeather(cityName);
            weatherData = weather.getWeatherData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        firstWindowController.fillWeatherWindow(weatherData, 0);
        secondWindowController.fillWeatherWindow(weatherData, 1);
        thirdWindowController.fillWeatherWindow(weatherData, 2);
        fourthWindowController.fillWeatherWindow(weatherData, 3);
        fifthWindowController.fillWeatherWindow(weatherData, 4);
        weatherData.clear();
    }
}
