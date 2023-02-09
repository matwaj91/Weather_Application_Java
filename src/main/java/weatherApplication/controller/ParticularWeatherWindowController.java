package weatherApplication.controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;
import weatherApplication.model.WeatherService;
import weatherApplication.model.client.SpecificWeatherClient;
import weatherApplication.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ParticularWeatherWindowController extends BaseController implements Initializable {

    @FXML
    private Label day;

    @FXML
    private ImageView image;

    @FXML
    private Label pressure;

    @FXML
    private Label temperature;

    @FXML
    private Label wind;

    @FXML
    private AnchorPane particularWeatherWindow;

    private WeatherParameters currentWeather;
    private List<WeatherParameters> weatherForecast;

    public ParticularWeatherWindowController() {
    }

    public ParticularWeatherWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    @FXML
    List<WeatherParameters> getWeatherFromClient(String cityName) throws IOException {
        WeatherService weatherService = new WeatherService(new SpecificWeatherClient());
        Weather weather;

        try {
            weather = weatherService.getWeather(cityName);
            currentWeather = weather.getCurrentWeather();
            weatherForecast = weather.getWeatherForecast();
        } catch (Exception e) {
            System.out.println("Error");
        }

        fillWeatherForecastWindow(weatherForecast);

        return weatherForecast;
    }

    void fillCurrentWeatherWindow(WeatherParameters currentWeather) {
        this.day.setText(currentWeather.getDay());
        this.image.setImage(new Image(String.valueOf(getClass().getResource("/image/" + currentWeather.getIcon() + ".png"))));
        this.temperature.setText(currentWeather.getTemperature());
        this.pressure.setText(currentWeather.getPressure());
        this.wind.setText(currentWeather.getWindSpeed());
    }

    AnchorPane fillWeatherForecastWindow(List<WeatherParameters> weatherForecast) {
        this.day.setText(weatherForecast.get(0).getDay());
        this.image.setImage(new Image(String.valueOf(getClass().getResource("/image/" + weatherForecast.get(0).getIcon() + ".png"))));
        this.temperature.setText(weatherForecast.get(0).getTemperature());
        this.pressure.setText(weatherForecast.get(0).getPressure());
        this.wind.setText(weatherForecast.get(0).getWindSpeed());

        return particularWeatherWindow;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(weatherForecast);
    }
}
