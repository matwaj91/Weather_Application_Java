package weatherApplication.model.client;

import weatherApplication.model.Weather;
import weatherApplication.model.WeatherForecastParameters;

import java.io.IOException;

public interface WeatherClient {

    Weather getWeather(String cityName) throws IOException;
}
