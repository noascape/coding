import model.Location;


public class Application {
    public static void main(String[] args) {

        Location berlin = new Location(52.52f, 13.405f);

        WeatherApp weatherApp = new WeatherApp();
        weatherApp.createWeather(berlin);

    }
}