package weatherApplication.model;

public class WeatherForecastParameters {

    private final String day;
    private final WeatherParameters weatherParameters;

    public WeatherForecastParameters(String day, WeatherParameters weatherParameters) {
        this.day = day;
        this.weatherParameters = weatherParameters;
    }

    public String getDay() {
        return day;
    }

    public WeatherParameters getWeatherParameters() {
        return weatherParameters;
    }

    @Override
    public String toString() {
        return "WeatherForecastParameters{" +
                "day='" + day + '\'' +
                ", weatherParameters=" + weatherParameters +
                '}';
    }
}
