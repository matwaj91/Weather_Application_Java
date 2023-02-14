package weatherApplication.model;

import java.util.List;

public class Weather {

    private final List<WeatherParameters> weatherData;

    public Weather(List<WeatherParameters> weatherData) {
        this.weatherData = weatherData;
    }

    public List<WeatherParameters> getWeatherData() {
        return weatherData;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherData=" + weatherData +
                '}';
    }
}
