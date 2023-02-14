package weatherApplication.model;

import java.util.List;

public class Weather {

    private final WeatherParameters currentWeather;

    private final List<WeatherParameters> weatherForecast;

    public Weather(WeatherParameters currentWeather, List<WeatherParameters> weatherForecastParameters) {
        this.currentWeather = currentWeather;
        this.weatherForecast = weatherForecastParameters;
    }

    public WeatherParameters getCurrentWeather() {
        return currentWeather;
    }

    public List<WeatherParameters> getWeatherForecast() {
        return weatherForecast;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "currentWeather=" + currentWeather +
                ", weatherForecast=" + weatherForecast +
                '}';
    }
}
