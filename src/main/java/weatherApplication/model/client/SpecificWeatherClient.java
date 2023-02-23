package weatherApplication.model.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

import static weatherApplication.AuxiliaryMethods.getNameDay;

public class SpecificWeatherClient implements WeatherClient {

    private final List<WeatherParameters> weatherData = new ArrayList<>();
    private static final String BEGINNING_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String BEGINNING_CURRENT_URL =  "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String UNITS = "&units=metric&appid=";
    private static final String LANGUAGE = "&lang=eng&units=metric";

    @Override
    public Weather getWeather(String cityName) {
        getCurrentWeather(cityName);
        getForecastWeather(cityName);
        return new Weather(weatherData);
    }

    private void getForecastWeather(String cityName) {
        URL url;

        try {
            url = getUrl(BEGINNING_FORECAST_URL, cityName);
            if (getResponse(url) != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);
                JSONObject jsonObject = parseStringIntoJsonObject(inline);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCurrentWeather(String cityName) {
        URL url;

        try {
            url = getUrl(BEGINNING_CURRENT_URL, cityName);
            if (getResponse(url) != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);
                JSONObject jsonObject = parseStringIntoJsonObject(inline);
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URL getUrl(String beginningUrl, String cityName) throws MalformedURLException {
        return new URL(beginningUrl + cityName + UNITS + Config.API_KEY + LANGUAGE);
    }

    private int getResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }

    private String writeAllJsonDataToString(URL url) throws IOException {
        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();
        return inline.toString();
    }

    private JSONObject parseStringIntoJsonObject(String inline) throws ParseException {
        JSONParser parse = new JSONParser();
        return (JSONObject) parse.parse(inline);
    }
}
