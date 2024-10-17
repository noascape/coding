package jgiven;

import com.tngtech.jgiven.junit5.SimpleScenarioTest;
import model.Location;
import org.junit.jupiter.api.Test;
import service.WeatherService;
import api.WeatherAPI;

public class WeatherServiceScenarioTest extends SimpleScenarioTest<WeatherServiceScenarioTest.Steps> {

    @Test
    public void userRequestsWeatherForecast() {
        given().a_user_at_location(52.52f, 13.405f);
        when().the_user_requests_the_weather_forecast();
        then().the_weather_information_is_displayed();
    }

    public static class Steps {
        private Location location;
        private WeatherService weatherService;

        public void a_user_at_location(float latitude, float longitude) {
            location = new Location(latitude, longitude);
            WeatherAPI weatherAPI = new WeatherAPI("API_Key");
            weatherService = new WeatherService(weatherAPI);
        }

        public void the_user_requests_the_weather_forecast() {
            weatherService.getDailyForecast(location);
            weatherService.getHourForecast(location);
        }

        public void the_weather_information_is_displayed() {
            System.out.println("Wetterinformationen werden angezeigt");
        }
    }
}


