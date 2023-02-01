package weatherApplication.model;

public class WeatherParameters {

    private final String icon;
    private final String temperature;
    private final String pressure;
    private final String windSpeed;

    public WeatherParameters(String icon, String temperature, String pressure, String windSpeed) {
        this.icon = icon;
        this.temperature = temperature;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
    }

    public String getIcon() {
        return icon;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
