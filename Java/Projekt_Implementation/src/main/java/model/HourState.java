package model;

import java.time.LocalDateTime;

public class HourState extends WeatherState {

    // Konstruktor
    public HourState(Location location, float temperature, float rainfall, float windspeed, float clouds, LocalDateTime time) {
        super(location, temperature, rainfall, windspeed, clouds, time);  // Aufruf des Konstruktors der Superklasse
    }
}
