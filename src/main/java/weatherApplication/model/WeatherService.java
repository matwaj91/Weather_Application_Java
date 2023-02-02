package weatherApplication.model;

import weatherApplication.model.client.WeatherClient;

import java.io.IOException;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient){
        this.weatherClient = weatherClient;
    }

    public WeatherForecast getWeather(String cityName) throws IOException {
        return weatherClient.getWeather(cityName);

    }
}
