package weatherApplication.model.client;

import weatherApplication.model.WeatherForecast;

import java.io.IOException;

public interface WeatherClient {

    WeatherForecast getWeather(String cityName) throws IOException;
}
