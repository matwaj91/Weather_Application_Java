package weatherApplication.model.client;

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

public class SpecificWeatherClient implements WeatherClient {

    @Override
    public Weather getWeather(String cityName) throws IOException {

        List<WeatherParameters> currentWeather = getCurrentWeather(cityName);

        List<WeatherForecastParameters> forecastWeather = getForecastWeather(cityName);

        return new Weather(currentWeather, forecastWeather);
    }

    private ArrayList<WeatherForecastParameters> getForecastWeather(String cityName) {

        return null;

    }

    public static List<WeatherParameters> getCurrentWeather(String cityName) {

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="
                            + cityName + "&units=metric&appid=" + Config.API_KEY + "&lang=eng&units=metric");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            //Getting the response code
            int responseCode = connection.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while(scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject jsonObject = (JSONObject) parse.parse(inline);

                //Get the required object from the above created object
                JSONObject object;

                object = (JSONObject) jsonObject.get("main") ;
                String stringTemperature = object.get("temp").toString();
                double doubleTemperature = Double.parseDouble(stringTemperature);
                int integerTemperature = (int) Math.round(doubleTemperature);
                String temperature = String.valueOf(integerTemperature) + "°C";
                //System.out.println(stringTemperature);

                object = (JSONObject) jsonObject.get("main") ;
                String pressure = object.get("pressure").toString() + "hPa";
                //System.out.println(pressure);

                object = (JSONObject) jsonObject.get("wind") ;
                String stringWindSpeed = object.get("speed").toString();
                double speedInKilometersPerHour = (Double.parseDouble(stringWindSpeed)) * 3.6;
                String WindSpeed = String.valueOf(speedInKilometersPerHour) + "km/h";
                //System.out.println(stringWindSpeed);







            }



        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
