package de.project.facade;

import de.project.factory.Seat;
import de.project.factory.SeatFactory;
import de.project.builder.SeatConfiguration;
import de.project.builder.SeatFeature;
import de.project.adapter.PassengerAdapter;
import de.project.adapter.PassengerDatabase;
import de.project.adapter.SeatManager;
import de.project.factory.SeatType;

import java.util.EnumSet;
import java.util.List;

public class SeatManagementSystem {
    private final SeatManager seatManager;

    public SeatManagementSystem() {
        this.seatManager = new SeatManager();
    }

    // Create a seat using the Factory pattern
    public Seat createSeat(String type) {
        return SeatFactory.createSeat(SeatType.valueOf(type));
    }

    // Build a custom seat configuration
    public SeatConfiguration configureSeat(boolean powerOutlet, boolean extraScreen, boolean extraLegroom) {
        EnumSet<SeatFeature> features = EnumSet.noneOf(SeatFeature.class);

        if (powerOutlet) {
            features.add(SeatFeature.POWER_OUTLET);
        }
        if (extraScreen) {
            features.add(SeatFeature.EXTRA_SCREEN);
        }
        if (extraLegroom) {
            features.add(SeatFeature.EXTRA_LEGROOM);
        }

        return SeatConfiguration.builder()
                .features(features)
                .build();
    }

    // Add passengers using the Adapter pattern
    public void addPassengersFromDatabase(PassengerDatabase database) {
        PassengerAdapter adapter = new PassengerAdapter(database);
        adapter.adaptData(seatManager);
    }

    // Get the current list of passengers
    public List<String> getPassengerList() {
        return seatManager.getPassengerList();
    }
}
