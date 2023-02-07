package weatherApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private List<WeatherParameters> weatherParameters;

    private List<WeatherForecastParameters> weatherForecastParameters;

    public Weather(List<WeatherParameters> weatherParameters, List<WeatherForecastParameters> weatherForecastParameters) {
        this.weatherParameters = weatherParameters;
        this.weatherForecastParameters = weatherForecastParameters;
    }

    public List<WeatherForecastParameters> getWeatherForecastParameters() {
        return weatherForecastParameters;
    }

    public List<WeatherParameters> getWeatherParameters() {
        return weatherParameters;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherParameters=" + weatherParameters +
                ", weatherForecastParameters=" + weatherForecastParameters +
                '}';
    }
}
