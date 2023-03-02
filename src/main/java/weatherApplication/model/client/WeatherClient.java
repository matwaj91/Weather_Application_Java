package weatherApplication.model.client;

import org.json.simple.parser.ParseException;
import weatherApplication.model.Weather;

import java.io.IOException;

public interface WeatherClient {

    Weather getWeather(String cityName) throws IOException, ParseException;
}
