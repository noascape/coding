package service;

import lombok.Setter;
import model.DayState;
import model.HourState;
import model.WeatherForecast;

import java.time.format.DateTimeFormatter;

@Setter
public class DisplayService implements IDisplayService {
    private WeatherForecast weatherForecast;

    @Override
    public void printDailyForecast() {
        System.out.println("Tägliche Vorhersage:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n",
                "Datum", "Temperatur (°C)", "Sonnenaufgang", "Sonnenuntergang",
                "Niederschlag (mm)", "Windgeschwindigkeit (m/s)", "Wolkenbedeckung (%)");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (DayState dayState : weatherForecast.getDaysForecast().getDailyForecasts()) {
            if (dayState != null) {
                System.out.printf("%-15s %-15.2f %-15s %-15s %-15.2f %-15.2f %-15.2f%n",
                        dayState.getTime().toLocalDate(),
                        dayState.getTemperature(),
                        dayState.getSunrise().toLocalTime(),
                        dayState.getSunfall().toLocalTime(),
                        dayState.getRainfall(),
                        dayState.getWindspeed(),
                        dayState.getClouds());
            }
        }
        System.out.println();
    }

    @Override
    public void printHourForecast() {
        System.out.println("Stündliche Vorhersage:");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n",
                "Zeit", "Temperatur (°C)", "Niederschlag (mm)",
                "Windgeschwindigkeit (m/s)", "Wolkenbedeckung (%)");
        System.out.println("---------------------------------------------------------------------------------");

        for (HourState hourState : weatherForecast.getHoursForecast().getHourForecasts()) {
            if (hourState != null) {
                System.out.printf("%-15s %-15.2f %-15.2f %-15.2f %-15.2f%n",
                        hourState.getTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                        hourState.getTemperature(),
                        hourState.getRainfall(),
                        hourState.getWindspeed(),
                        hourState.getClouds());
            }
        }
        System.out.println();
    }

}
