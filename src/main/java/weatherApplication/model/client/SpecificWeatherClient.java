package weatherApplication.model.client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import weatherApplication.model.Config;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherForecastParameters;
import weatherApplication.model.WeatherParameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class SpecificWeatherClient implements WeatherClient {



    private final static List<WeatherForecastParameters> forecastWeather = new ArrayList<>();

    @Override
    public Weather getWeather(String cityName) {

        WeatherParameters currentWeather = getCurrentWeather(cityName);

        List<WeatherForecastParameters> forecastWeather = getForecastWeather(cityName);

        return new Weather(currentWeather, forecastWeather);
    }

    public static List<WeatherForecastParameters> getForecastWeather(String cityName) {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q="
                    + cityName + "&units=metric&appid=" + Config.API_KEY + "&lang=eng&units=metric");

            int responseCode = getResponse(url);

            if (responseCode != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                //Get the required object from the above created object
                JSONObject object;
                JSONArray array;
                String alreadyLoadedDay = "";

                array = (JSONArray) jsonObject.get("list");

                for (int i = 0; i < array.size(); i++) {

                    JSONObject newObject = (JSONObject) array.get(i);

                    String date = newObject.get("dt_txt").toString();
                    String weekDay = getNameDay(date);
                    String formatYYYYMMDD = getYYYYMMDDFormat(date);

                    if (!formatYYYYMMDD.equals(alreadyLoadedDay)) {

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

                        WeatherForecastParameters weatherForecastParameters =
                                new WeatherForecastParameters(weekDay, icon, temperature, pressure, windSpeed);
                        forecastWeather.add(weatherForecastParameters);
                        //System.out.println(forecastWeather);
                    }
                }
            }
            return forecastWeather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static String getYYYYMMDDFormat(String date) {
        String dateInRightFormat = date.replace("-", "");
        dateInRightFormat = dateInRightFormat.substring(0, 8);
        return dateInRightFormat;
    }

    public static String getNameDay(String date) {
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

    public static WeatherParameters getCurrentWeather(String cityName) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
                    + cityName + "&units=metric&appid=" + Config.API_KEY + "&lang=eng&units=metric");

            int responseCode = getResponse(url);

            if (responseCode != 200) {
                throw new RuntimeException();
            } else {
                String inline = writeAllJsonDataToString(url);

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                //Get the required object from the above created object
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

                return new WeatherParameters(day, icon, temperature, pressure, windSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getResponse(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        return responseCode;
    }

    private static String writeAllJsonDataToString(URL url) throws IOException {
        String inline = "";
        Scanner scanner = new Scanner(url.openStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline += scanner.nextLine();
        }

        scanner.close();

        return inline;
    }
}
