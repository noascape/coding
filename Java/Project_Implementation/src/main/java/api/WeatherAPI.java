package api;

import model.DayState;
import model.HourState;
import model.Location;
import java.time.LocalDateTime;
import java.util.Random;

public class WeatherAPI {
    private final String apiKey;
    private final Random random = new Random();

    public WeatherAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public DayState getWeatherAsDay(Location location, LocalDateTime time) {

        float temperature = random.nextFloat() * 40;
        float rainfall = random.nextFloat() * 10;
        float windspeed = random.nextFloat() * 20;
        float clouds = random.nextFloat();
        LocalDateTime sunrise = time.toLocalDate().atStartOfDay().plusHours(random.nextInt(6) + 5);
        LocalDateTime sunfall = sunrise.plusHours(12 + random.nextInt(3));


        return new DayState(location, temperature, rainfall, windspeed, clouds, time, sunrise, sunfall);
    }


    public HourState getWeatherAsHour(Location location, LocalDateTime time) {

        float temperature = random.nextFloat() * 40;
        float rainfall = random.nextFloat() * 10;
        float windspeed = random.nextFloat() * 20;
        float clouds = random.nextFloat();


        return new HourState(location, temperature, rainfall, windspeed, clouds, time);
    }
}

