package model;

import java.time.LocalDateTime;

public class WeatherState {
    protected Location location;
    protected float temperature;
    protected float rainfall;
    protected float windspeed;
    protected float clouds;
    protected LocalDateTime time;

    // Konstruktor
    public WeatherState(Location location, float temperature, float rainfall, float windspeed, float clouds, LocalDateTime time) {
        this.location = location;
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.windspeed = windspeed;
        this.clouds = clouds;
        this.time = time;
    }

    // Getter und Setter für WeatherState
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getRainfall() {
        return rainfall;
    }

    public void setRainfall(float rainfall) {
        this.rainfall = rainfall;
    }

    public float getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(float windspeed) {
        this.windspeed = windspeed;
    }

    public float getClouds() {
        return clouds;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
