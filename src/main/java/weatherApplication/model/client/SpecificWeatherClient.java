package weatherApplication.model.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;

import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static weatherApplication.AuxiliaryMethods.getNameDay;

public class SpecificWeatherClient implements WeatherClient {

    private static final String BEGINNING_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String BEGINNING_CURRENT_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String UNITS = "&units=metric&appid=";
    private static final String LANGUAGE = "&lang=eng&units=metric";
    private final RestTemplate restTemplate;
    private List<WeatherParameters> weatherData = new ArrayList<>();
    private String response;

    public SpecificWeatherClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getWeather(String cityName) throws ParseException {
        weatherData = getCurrentWeather(cityName);
        weatherData = getForecastWeather(cityName);
        return new Weather(weatherData);
    }

    public List<WeatherParameters> getForecastWeather(String cityName) throws ParseException {
        response = getResponse(BEGINNING_FORECAST_URL, cityName);

        JSONObject jsonObject = parseStringIntoJsonObject(response);
        JSONObject object;
        JSONArray array;
        String dayAlreadyLoaded = "";
        array = (JSONArray) jsonObject.get("list");

        for (int i = 0; i < array.size(); i++) {

            JSONObject newObject = (JSONObject) array.get(i);
            String dateAndTime = newObject.get("dt_txt").toString();
            String day = getNameDay(dateAndTime);
            String date = dateAndTime.substring(0, 10);
            LocalDate localDate = LocalDate.now();
            String today = localDate.toString();

            if (!date.equals(dayAlreadyLoaded) && dateAndTime.contains("12:00:00") && (!date.equals(today))) {

                dayAlreadyLoaded = date;

                object = (JSONObject) newObject.get("main");
                String stringTemperature = object.get("temp").toString();
                int integerTemperature = (int) Math.round(Double.parseDouble(stringTemperature));
                String temperature = integerTemperature + "°C";

                String pressure = object.get("pressure").toString() + "hPa";

                object = (JSONObject) newObject.get("wind");
                String windSpeed = object.get("speed").toString() + "m/s";

                JSONArray newArray = (JSONArray) newObject.get("weather");
                JSONObject objectFromArray = (JSONObject) newArray.get(0);
                String icon = objectFromArray.get("icon").toString();

                WeatherParameters weatherParameters =
                        new WeatherParameters(day, icon, temperature, pressure, windSpeed);
                weatherData.add(weatherParameters);
            }
        }
        return weatherData;
    }

    public List<WeatherParameters> getCurrentWeather(String cityName) throws ParseException {
        response = getResponse(BEGINNING_CURRENT_URL, cityName);

        JSONObject jsonObject = parseStringIntoJsonObject(response);
        JSONObject object;
        JSONArray array;

        String day = "Today";

        object = (JSONObject) jsonObject.get("main");

        String stringTemperature = object.get("temp").toString();
        int integerTemperature = (int) Math.round(Double.parseDouble(stringTemperature));
        String temperature = integerTemperature + "°C";

        String pressure = object.get("pressure").toString() + "hPa";

        object = (JSONObject) jsonObject.get("wind");
        String windSpeed = object.get("speed").toString() + "m/s";

        array = (JSONArray) jsonObject.get("weather");
        JSONObject objectFromArray = (JSONObject) array.get(0);
        String icon = objectFromArray.get("icon").toString();

        WeatherParameters weatherParameters =
                new WeatherParameters(day, icon, temperature, pressure, windSpeed);
        weatherData.add(weatherParameters);

        return weatherData;
    }

    private String getResponse(String beginningUrl, String cityName) {
        response = restTemplate.getForObject(beginningUrl + cityName + UNITS + Config.API_KEY
                + LANGUAGE, String.class);
        if(response == null) {
            throw new NullPointerException();
        } else {
            return response;
        }
    }

    private JSONObject parseStringIntoJsonObject(String inline) throws ParseException {
        JSONParser parse = new JSONParser();
        return (JSONObject) parse.parse(inline);
    }
}
