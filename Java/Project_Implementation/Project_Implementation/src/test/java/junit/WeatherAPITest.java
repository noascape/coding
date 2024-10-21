package junit;

import api.WeatherAPI;
import model.DayState;
import model.HourState;
import model.Location;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Weather API Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WeatherAPITest {

    private WeatherAPI weatherAPI;

    @BeforeEach
    void setUp() {
        weatherAPI = new WeatherAPI("test-api-key");
    }

    @Nested
    @DisplayName("DayState Tests")
    class DayStateTests {

        @Test
        @Order(1)
        @DisplayName("Test weather as day")
        void testWeatherAsDay() {
            Location location = new Location(52.52f, 13.405f);
            LocalDateTime time = LocalDateTime.now();

            DayState dayState = weatherAPI.getWeatherAsDay(location, time);

            Assertions.assertNotNull(dayState);
            Assertions.assertEquals(location, dayState.getLocation());
        }

        @ParameterizedTest
        @Order(2)
        @CsvSource({
                "52.52, 13.405, 2024-10-15T10:00",
                "49.4885, 9.7704, 2024-10-16T12:00"
        })
        @DisplayName("Parameterized Test for DayState")
        void testWeatherAsDayParameterized(float longitude, float latitude, String dateTime) {
            Location location = new Location(longitude, latitude);
            LocalDateTime time = LocalDateTime.parse(dateTime);

            DayState dayState = weatherAPI.getWeatherAsDay(location, time);

            Assertions.assertNotNull(dayState);
            Assertions.assertEquals(location, dayState.getLocation());
        }

        @TestFactory
        @DisplayName("TestFactory for DayState Exception")
        Stream<Executable> testDayStateException() {
            return Stream.of(
                    () -> assertThrows(NullPointerException.class, () -> weatherAPI.getWeatherAsDay(null, LocalDateTime.now())),
                    () -> assertThrows(NullPointerException.class, () -> weatherAPI.getWeatherAsDay(new Location(52.52f, 13.405f), null))
            );
        }
    }

    @Nested
    @DisplayName("HourState Tests")
    class HourStateTests {

        @Test
        @Order(3)
        @DisplayName("Test weather as hour")
        void testWeatherAsHour() {
            Location location = new Location(52.52f, 13.405f);
            LocalDateTime time = LocalDateTime.now();

            HourState hourState = weatherAPI.getWeatherAsHour(location, time);

            Assertions.assertNotNull(hourState);
            Assertions.assertEquals(location, hourState.getLocation());
        }
    }
}


