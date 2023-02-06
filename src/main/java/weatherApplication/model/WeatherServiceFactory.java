package weatherApplication.model;

import weatherApplication.model.client.SpecificWeatherClient;
import weatherApplication.model.client.WeatherClient;

public class WeatherServiceFactory {

    public static WeatherService createWeatherService() {
        return new WeatherService(createWeatherClient());
    }

    private static WeatherClient createWeatherClient() {
        return new SpecificWeatherClient();
    }
}
