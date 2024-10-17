package service;

import model.*;

public class DisplayService implements IDisplayService {
    private WeatherForecast weatherForecast;

    @Override
    public void printDailyForecast() {
        System.out.println("Tägliche Vorhersage:");
        for (DayState dayState : weatherForecast.getDaysForecast().getDailyForecasts()) {
            if (dayState != null) {
                System.out.println("Temperatur: " + dayState.getTemperature());
                System.out.println("Sonnenaufgang: " + dayState.getSunrise());
                System.out.println("Sonnenuntergang: " + dayState.getSunfall());
                System.out.println();
            }
        }
    }

    @Override
    public void printHourForecast() {
        System.out.println("Stündliche Vorhersage:");
        for (HourState hourState : weatherForecast.getHoursForecast().getHourForecasts()) {
            if (hourState != null) {
                System.out.println("Temperatur: " + hourState.getTemperature());
                System.out.println("Zeit: " + hourState.getTime());
                System.out.println();
            }
        }
    }

    public void setWeatherForecast(WeatherForecast weatherForecast) {
        this.weatherForecast = weatherForecast;
    }
}
