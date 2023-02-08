package weatherApplication.model;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    private WeatherParameters weatherParameters;

    private List<WeatherForecastParameters> weatherForecastParameters;

    public Weather(WeatherParameters weatherParameters, List<WeatherForecastParameters> weatherForecastParameters) {
        this.weatherParameters = weatherParameters;
        this.weatherForecastParameters = weatherForecastParameters;
    }

    public List<WeatherForecastParameters> getWeatherForecastParameters() {
        return weatherForecastParameters;
    }

    public WeatherParameters getWeatherParameters() {
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
