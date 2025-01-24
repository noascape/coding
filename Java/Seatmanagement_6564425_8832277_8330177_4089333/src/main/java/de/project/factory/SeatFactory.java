package de.project.factory;

import java.util.Map;
import java.util.function.Supplier;

public class SeatFactory {
    private static final Map<SeatType, Supplier<Seat>> seatMap = Map.of(
            SeatType.ECONOMY, () -> Seat.builder().type(SeatType.ECONOMY).legroom(76).width(45).build(),
            SeatType.ECONOMY_PLUS, () -> Seat.builder().type(SeatType.ECONOMY_PLUS).legroom(84).width(48).build(),
            SeatType.BUSINESS, () -> Seat.builder().type(SeatType.BUSINESS).legroom(96).width(55).build(),
            SeatType.FIRST_CLASS, () -> Seat.builder().type(SeatType.FIRST_CLASS).legroom(116).width(65).build()
    );

    public static Seat createSeat(SeatType seatType) {
        return seatMap.getOrDefault(seatType,
                () -> { throw new IllegalArgumentException("Invalid seat type: " + seatType); }).get();
    }
}
