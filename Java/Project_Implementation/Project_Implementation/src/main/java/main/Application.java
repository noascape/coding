package main;

import model.Location;

public class Application {
    public static void main(String[] args) {
        WeatherApp weatherApp = new WeatherApp();

        Location berlin = new Location(52.52f, 13.405f);
        Location badMergentheim = new Location(49.4885f, 9.7704f);

        printHeader("Berlin");
        weatherApp.requestWeather(berlin);

        printHeader("Bad Mergentheim");
        weatherApp.requestWeather(badMergentheim);
    }

    private static void printHeader(String location) {
        System.out.printf("       Weather App - Standort: %s%n", location);
        System.out.println("====================================================");
    }
}
