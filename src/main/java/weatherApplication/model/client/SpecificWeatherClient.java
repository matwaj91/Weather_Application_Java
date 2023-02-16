package weatherApplication.model.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import weatherApplication.model.Config;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class SpecificWeatherClient implements WeatherClient {

    private final List<WeatherParameters> weatherData = new ArrayList<>();

    @Override
    public Weather getWeather(String cityName) {
        getCurrentWeather(cityName);
        getForecastWeather(cityName);
        return new Weather(weatherData);
    }

    private void getForecastWeather(String cityName) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q="
                    + cityName + "&units=metric&appid=" + Config.API_KEY + "&lang=eng&units=metric");

            int responseCode = getResponse(url);

            if (responseCode != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);

                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                JSONObject object;
                JSONArray array;
                String alreadyLoadedDay = "";

                array = (JSONArray) jsonObject.get("list");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject newObject = (JSONObject) array.get(i);
                    String date = newObject.get("dt_txt").toString();
                    String day = getNameDay(date);
                    String formatYYYYMMDD = getYYYYMMDDFormat(date);
                    LocalDate localDate = LocalDate.now();
                    String today = localDate.toString().replace("-", "");

                    if (!formatYYYYMMDD.equals(alreadyLoadedDay) && date.contains("12:00:00") && (!formatYYYYMMDD.equals(today))) {
                        alreadyLoadedDay = formatYYYYMMDD;
                        object = (JSONObject) newObject.get("main");
                        String stringTemperature = object.get("temp").toString();
                        double doubleTemperature = Double.parseDouble(stringTemperature);
                        int integerTemperature = (int) Math.round(doubleTemperature);
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

    private String getYYYYMMDDFormat(String date) {
        String dateInRightFormat = date.replace("-", "");
        dateInRightFormat = dateInRightFormat.substring(0, 8);
        return dateInRightFormat;
    }

    private String getNameDay(String date) {
        date = date.replace("-", "");
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));

        LocalDate localDate = LocalDate.of(year, month, day);
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String stringDayOfWeek = dayOfWeek.toString();
        String firstUpperCase = stringDayOfWeek.substring(0, 1).toUpperCase()
                + stringDayOfWeek.substring(1).toLowerCase();
        return firstUpperCase;
    }

    private void getCurrentWeather(String cityName) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
                    + cityName + "&units=metric&appid=" + Config.API_KEY + "&lang=eng&units=metric");

            int responseCode = getResponse(url);

            if (responseCode != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);

                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                JSONObject object;
                JSONArray array;

                String day = "Today";

                object = (JSONObject) jsonObject.get("main");
                String stringTemperature = object.get("temp").toString();
                double doubleTemperature = Double.parseDouble(stringTemperature);
                int integerTemperature = (int) Math.round(doubleTemperature);
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

    private int getResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        return responseCode;
    }

    private String writeAllJsonDataToString(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }
        scanner.close();

        return inline;
    }
}
