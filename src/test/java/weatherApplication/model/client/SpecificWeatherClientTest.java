package weatherApplication.model.client;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import weatherApplication.model.WeatherParameters;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class SpecificWeatherClientTest {

    private SpecificWeatherClient specificWeatherClient;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        specificWeatherClient = new SpecificWeatherClient(restTemplate);
    }

    @Test
    void getCurrentWeatherShouldReturnListWithOneElement () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getCurrentWeatherFromJson());

        //when
        List<WeatherParameters> currentWeather = specificWeatherClient.getCurrentWeather("Berlin");

        //then
        assertThat(currentWeather, hasSize(1));
    }

    @Test
    void getCurrentWeatherShouldReturnCorrectData () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getCurrentWeatherFromJson());

        //when
        List<WeatherParameters> currentWeather = specificWeatherClient.getCurrentWeather("Berlin");

        //then
        assertEquals(currentWeather.get(0).getDay(), "Today");
        assertEquals(currentWeather.get(0).getIcon(), "02d");
        assertEquals(currentWeather.get(0).getTemperature(), "4°C");
        assertEquals(currentWeather.get(0).getPressure(), "998hPa");
        assertEquals(currentWeather.get(0).getWindSpeed(), "1.97m/s");
    }

    @Test
    void getCurrentWeatherShouldNotBeNull () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getCurrentWeatherFromJson());

        //when
        List<WeatherParameters> currentWeather = specificWeatherClient.getCurrentWeather("Berlin");

        //then
        assertThat(currentWeather, is(notNullValue()));
    }

    @Test
    void shouldThrownExceptionWhenCannotGetCurrentWeatherForUnknownCity() {
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(null);

        //then
        assertThrows(NullPointerException.class, () -> specificWeatherClient.getCurrentWeather("ABCD"));
    }

    @Test
    void getForecastWeatherShouldReturnListWithFivesElement () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getForecastWeatherFromJson());

        //when
        List<WeatherParameters> forecastWeather = specificWeatherClient.getForecastWeather("Berlin");

        //then
        assertThat(forecastWeather, hasSize(5));
    }

    @Test
    void getForecastWeatherShouldReturnCorrectData () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getForecastWeatherFromJson());

        //when
        List<WeatherParameters> forecastWeather = specificWeatherClient.getForecastWeather("Berlin");

        //then
        assertEquals(forecastWeather.get(1).getDay(), "Thursday");
        assertEquals(forecastWeather.get(1).getIcon(), "02n");
        assertEquals(forecastWeather.get(1).getTemperature(), "-1°C");
        assertEquals(forecastWeather.get(1).getPressure(), "1020hPa");
        assertEquals(forecastWeather.get(1).getWindSpeed(), "0.9m/s");
    }

    @Test
    void getForecastWeatherShouldNotBeNull () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getForecastWeatherFromJson());

        //when
        List<WeatherParameters> forecastWeather = specificWeatherClient.getForecastWeather("Berlin");

        //then
        assertThat(forecastWeather, is(notNullValue()));
    }

    @Test
    void shouldThrownExceptionWhenCannotGetForecastWeatherForUnknownCity() {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(null);

        //then
        assertThrows(NullPointerException.class, () -> specificWeatherClient.getForecastWeather("ABCD"));
    }

    @Test
    void forecastWeatherListShouldNotContainDataWithAnotherTimeThanMidday () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getForecastWeatherFromJson());

        //when
        List<WeatherParameters> forecastWeather = specificWeatherClient.getForecastWeather("Berlin");

        //then
        assertThat(forecastWeather.get(0).getIcon(), is(not("05n")));
        assertThat(forecastWeather.get(0).getTemperature(), is(not("2°C")));
        assertThat(forecastWeather.get(0).getPressure(), is(not("1035hPa")));
        assertThat(forecastWeather.get(0).getWindSpeed(), is(not("1.20hPa")));
    }

    @Test
    void forecastWeatherListShouldNotContainTheSameDay () throws IOException, ParseException {
        //given
        given(restTemplate.getForObject(anyString(), ArgumentMatchers.eq(String.class)))
                .willReturn(getForecastWeatherFromJson());

        //when
        List<WeatherParameters> forecastWeather = specificWeatherClient.getForecastWeather("Berlin");

        //then
        assertThat(forecastWeather.get(1).getDay(), is(not("Wednesday")));
    }

    private String getCurrentWeatherFromJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/json/currentWeather.json")));
    }

    private String getForecastWeatherFromJson() throws IOException {
        return new String(Files.readAllBytes(Path.of("src/test/resources/json/forecastWeather.json")));
    }
}