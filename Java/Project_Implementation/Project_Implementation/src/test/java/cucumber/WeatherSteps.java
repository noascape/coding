package cucumber;

import api.WeatherAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.DayState;
import model.Location;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeatherSteps {

    private Location location;
    private WeatherAPI weatherAPI;
    private DayState dayState;

    @Given("a location with latitude {float} and longitude {float}")
    public void setLocation(float latitude, float longitude) {
        location = new Location(latitude, longitude);
        weatherAPI = mock(WeatherAPI.class);
    }

    @When("the user requests the weather for the day")
    public void requestWeatherAsDay() {
        dayState = weatherAPI.getWeatherAsDay(location, null);
    }

    @Then("the daily forecast should be displayed")
    public void verifyDayForecastDisplayed() {
        assertNotNull(dayState);
        verify(weatherAPI).getWeatherAsDay(location, null);
    }
}

