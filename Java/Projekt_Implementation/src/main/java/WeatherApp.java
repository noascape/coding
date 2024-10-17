import api.WeatherAPI;
import model.DaysForecast;
import model.HoursForecast;
import model.Location;

import model.WeatherForecast;
import service.DisplayService;
import service.WeatherService;

public class WeatherApp {

    private final WeatherAPI weatherAPI;
    private final WeatherService weatherService;
    private final DisplayService displayService;

    public WeatherApp() {
        this.weatherAPI = new WeatherAPI("API_Key");
        this.weatherService = new WeatherService(weatherAPI);
        this.displayService = new DisplayService();

    }

    public void createWeather(Location location) {
        DaysForecast dailyForecast = weatherService.getDailyForecast(location);
        HoursForecast hourlyForecast = weatherService.getHourForecast(location);

        WeatherForecast weatherForecast = new WeatherForecast(hourlyForecast, dailyForecast);

        displayService.setWeatherForecast(weatherForecast);
        displayService.printDailyForecast();
        displayService.printHourForecast();
    }


}
