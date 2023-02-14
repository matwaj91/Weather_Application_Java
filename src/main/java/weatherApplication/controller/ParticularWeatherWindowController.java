package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import weatherApplication.model.WeatherParameters;
import java.util.List;

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

    public ParticularWeatherWindowController() {}

    void fillWeatherWindow(List<WeatherParameters> weatherData, int i) {
        this.day.setText(weatherData.get(i).getDay());
        this.image.setImage(new Image(String.valueOf(getClass().getResource("/image/" + weatherData.get(i).getIcon() + ".png"))));
        this.temperature.setText(weatherData.get(i).getTemperature());
        this.pressure.setText(weatherData.get(i).getPressure());
        this.wind.setText(weatherData.get(i).getWindSpeed());
    }
}
