package mockito;

import api.WeatherAPI;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.WeatherService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WeatherServiceTest {

    @Mock
    private WeatherAPI weatherAPI;

    @InjectMocks
    private WeatherService weatherService;

    private Location location;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        location = new Location(49.4885f, 9.7704f);
    }

    @Test
    void testGetDailyForecast() {
        DayState[] mockDayStates = new DayState[4];
        LocalDateTime[] mockDates = new LocalDateTime[4];

        for (int i = 0; i < 4; i++) {
            LocalDateTime sunriseTime = LocalDateTime.now().plusDays(i).plusHours(6);
            LocalDateTime sunfallTime = LocalDateTime.now().plusDays(i).plusHours(18);
            mockDates[i] = LocalDateTime.now().plusDays(i);
            mockDayStates[i] = new DayState(location, 20.0f + i, 5.0f, 2.0f, 80.0f, mockDates[i], sunriseTime, sunfallTime);
        }

        when(weatherAPI.getWeatherAsDay(eq(location), any(LocalDateTime.class)))
                .thenReturn(mockDayStates[0], mockDayStates[1], mockDayStates[2], mockDayStates[3]);

        DaysForecast forecast = weatherService.getDailyForecast(location);

        // Verifizieren, dass die WeatherAPI genau 4 Mal aufgerufen wurde
        verify(weatherAPI, times(4)).getWeatherAsDay(eq(location), any(LocalDateTime.class));

        // Überprüfen, dass die Vorhersage die erwarteten Werte enthält
        assertNotNull(forecast);
        assertEquals(4, forecast.getDailyForecasts().length);

        for (int i = 0; i < 4; i++) {
            DayState day = forecast.getDailyForecasts()[i];
            assertNotNull(day);
            assertEquals(20.0f + i, day.getTemperature());
            assertEquals(5.0f, day.getRainfall());
            assertEquals(2.0f, day.getWindspeed());
            assertEquals(80.0f, day.getClouds());
            assertEquals(mockDayStates[i].getSunrise(), day.getSunrise());
            assertEquals(mockDayStates[i].getSunfall(), day.getSunfall());
        }

        // Verifizieren, dass die Methode getWeatherAsDay in der richtigen Reihenfolge und mit ähnlichen Zeitpunkten aufgerufen wurde
        InOrder inOrder = inOrder(weatherAPI);
        for (int i = 0; i < 4; i++) {
            LocalDateTime expectedDate = mockDates[i];
            inOrder.verify(weatherAPI).getWeatherAsDay(eq(location), argThat(dateTime ->
                    dateTime.toLocalDate().equals(expectedDate.toLocalDate())
            ));
        }
    }

    @Test
    void testGetHourlyForecast() {
        HourState[] mockHourStates = new HourState[4];
        LocalDateTime[] mockDates = new LocalDateTime[4];

        for (int i = 0; i < 4; i++) {
            mockDates[i] = LocalDateTime.now().plusHours(i);
            mockHourStates[i] = new HourState(location, 15.0f + i, 3.0f, 1.0f, 70.0f, mockDates[i]);
        }

        when(weatherAPI.getWeatherAsHour(eq(location), any(LocalDateTime.class)))
                .thenReturn(mockHourStates[0], mockHourStates[1], mockHourStates[2], mockHourStates[3]);

        HoursForecast forecast = weatherService.getHourForecast(location);

        verify(weatherAPI, times(4)).getWeatherAsHour(eq(location), any(LocalDateTime.class));

        assertNotNull(forecast);
        assertEquals(4, forecast.getHourForecasts().length);

        for (int i = 0; i < 4; i++) {
            HourState hour = forecast.getHourForecasts()[i];
            assertNotNull(hour);
            assertEquals(15.0f + i, hour.getTemperature());
            assertEquals(3.0f, hour.getRainfall());
            assertEquals(1.0f, hour.getWindspeed());
            assertEquals(70.0f, hour.getClouds());
        }

        // Verifizieren, dass die Methode getWeatherAsHour in der richtigen Reihenfolge und mit den exakten Zeitpunkten (bis zur Sekunde) aufgerufen wurde
        InOrder inOrder = inOrder(weatherAPI);
        for (int i = 0; i < 4; i++) {
            final int index = i;
            inOrder.verify(weatherAPI).getWeatherAsHour(eq(location), argThat(dateTime ->
                    dateTime.toLocalDate().equals(mockDates[index].toLocalDate()) &&
                            dateTime.toLocalTime().withNano(0).equals(mockDates[index].toLocalTime().withNano(0))
            ));
        }
    }
}








