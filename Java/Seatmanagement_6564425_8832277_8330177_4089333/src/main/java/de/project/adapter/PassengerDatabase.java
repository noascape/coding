package de.project.adapter;

import lombok.Data;
import java.util.List;

@Data
public class PassengerDatabase {
    private final List<String> passengers;

    public PassengerDatabase(List<String> passengers) {
        this.passengers = passengers;
    }

    // Returns passenger data in a specific format (comma-separated string)
    public String getPassengerData() {
        return DataConversionUtils.listToCommaSeparatedString(passengers);
    }
}
