package weatherApplication.model;

import java.util.ArrayList;

public class Weather {

    private WeatherParameters weatherParameters;
    private ArrayList<WeatherForecastParameters> weatherForecastParameters;

    public Weather(WeatherParameters weatherParameters, ArrayList<WeatherForecastParameters> weatherForecastParameters) {
        this.weatherParameters = weatherParameters;
        this.weatherForecastParameters = weatherForecastParameters;
    }

    public WeatherParameters getWeatherParameters() {
        return weatherParameters;
    }

    public ArrayList<WeatherForecastParameters> getWeatherForecastParameters() {
        return weatherForecastParameters;
    }
}
