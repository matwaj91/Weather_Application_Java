package weatherApplication.model;

import org.json.simple.parser.ParseException;
import weatherApplication.model.client.WeatherClient;

import java.io.IOException;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient){
        this.weatherClient = weatherClient;
    }

    public Weather getWeather(String cityName) throws IOException, ParseException {
        return weatherClient.getWeather(cityName);
    }
}
