package main;

import api.WeatherAPI;
import model.DaysForecast;
import model.HoursForecast;
import model.Location;
import model.WeatherForecast;
import service.DisplayService;
import service.WeatherService;
import states.ErrorState;
import states.IdleState;
import states.State;

public class WeatherApp {

    private final WeatherService weatherService;
    private final DisplayService displayService;
    private State currentState = new IdleState();

    public WeatherApp() {
        WeatherAPI weatherAPI = new WeatherAPI("API_Key");
        this.weatherService = new WeatherService(weatherAPI);
        this.displayService = new DisplayService();
    }

    public void setState(State newState) {
        this.currentState = newState;
    }

    public void getWeatherForecast(Location location) {
        try {
            DaysForecast dailyForecast = weatherService.getDailyForecast(location);
            HoursForecast hourlyForecast = weatherService.getHourForecast(location);

            WeatherForecast weatherForecast = new WeatherForecast(hourlyForecast, dailyForecast);

            displayService.setWeatherForecast(weatherForecast);
            displayService.printDailyForecast();
            displayService.printHourForecast();
        } catch (Exception e) {
            System.out.println("Error occurred while fetching weather data: " + e.getMessage());
            setState(new ErrorState());
        }
    }

    public void requestWeather(Location location) {
        currentState.handleRequest(this, location);
    }
}
