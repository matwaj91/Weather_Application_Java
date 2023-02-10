package weatherApplication.controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;
import weatherApplication.model.WeatherService;
import weatherApplication.model.client.SpecificWeatherClient;
import weatherApplication.view.ViewFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ParticularWeatherWindowController extends BaseController{

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

    void fillCurrentWeatherWindow(WeatherParameters currentWeather) {
        this.day.setText(currentWeather.getDay());
        this.image.setImage(new Image(String.valueOf(getClass().getResource("/image/" + currentWeather.getIcon() + ".png"))));
        this.temperature.setText(currentWeather.getTemperature());
        this.pressure.setText(currentWeather.getPressure());
        this.wind.setText(currentWeather.getWindSpeed());
    }

    void fillWeatherForecastWindow(List<WeatherParameters> weatherForecast, int i) {
        this.day.setText(weatherForecast.get(i).getDay());
        this.image.setImage(new Image(String.valueOf(getClass().getResource("/image/" + weatherForecast.get(i).getIcon() + ".png"))));
        this.temperature.setText(weatherForecast.get(i).getTemperature());
        this.pressure.setText(weatherForecast.get(i).getPressure());
        this.wind.setText(weatherForecast.get(i).getWindSpeed());
    }
}
