package weatherApplication.model;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import weatherApplication.model.client.WeatherClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class WeatherServiceTest {

    @Mock
    private WeatherClient weatherClient = mock(WeatherClient.class);

    @InjectMocks
    private WeatherService weatherService = new WeatherService(weatherClient);

    Weather expectedWeather = new Weather(prepareWeatherData());

    @Test
    void shouldReturnWeather() throws IOException {
        //given
        given(weatherClient.getWeather("Berlin")).willReturn(expectedWeather);

        //when
        Weather result = weatherService.getWeather("Berlin");

        //then
        assertThat(result, equalTo(expectedWeather));
    }

    @Test
    void shouldNotReturnWeatherIfTwoDifferentCities() throws IOException {
        //given
        given(weatherClient.getWeather("Berlin")).willReturn(expectedWeather);

        //when
        Weather result = weatherService.getWeather("Cracow");

        //then
        assertThat(result, not(equalTo(expectedWeather)));
    }

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
}