package weatherApplication.model.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import weatherApplication.model.Weather;
import weatherApplication.model.WeatherParameters;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class SpecificWeatherClientTest {

    @Mock
    private WeatherClient weatherClient = mock(WeatherClient.class);


    Weather expectedWeather = new Weather(prepareWeatherData());

    private List<WeatherParameters> prepareWeatherData() {
        List<WeatherParameters> weatherData = new ArrayList<>();

        weatherData.add(new WeatherParameters("Today", "02d", "15°C", "1015hPa", "3m/s" ));
        WeatherParameters weatherParameters =
                new WeatherParameters("Tuesday", "04d", "10°C", "1013hPa", "2m/s" );
        for (int i = 0; i < 4; i++) {
            weatherData.add(weatherParameters);
        }
        return weatherData;
    }

    @Test
    void shouldNotReturnTheSameWeatherFromAnotherClient() throws IOException {
        //given
        WeatherClient anotherWeatherClient = new SpecificWeatherClient();
        given(weatherClient.getWeather("Berlin")).willReturn(expectedWeather);

        //when
        Weather result = anotherWeatherClient.getWeather("Berlin");

        //then
        assertThat(result, not(equalTo(expectedWeather)));
    }

    @Test
    void shouldReturnWeather() throws IOException {
        //given
        given(weatherClient.getWeather("Berlin")).willReturn(expectedWeather);

        //when
        Weather result = weatherClient.getWeather("Berlin");

        //then
        assertThat(result, equalTo(expectedWeather));
    }
}