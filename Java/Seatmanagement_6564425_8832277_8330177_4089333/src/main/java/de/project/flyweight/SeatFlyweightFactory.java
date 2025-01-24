package de.project.flyweight;

import java.util.HashMap;
import java.util.Map;

public class SeatFlyweightFactory {
    private final Map<String, SeatFlyweight> flyweightCache = new HashMap<>();

    public SeatFlyweight getFlyweight(String seatClass) {
        if (!flyweightCache.containsKey(seatClass)) {
            switch (seatClass) {
                case "Economy" -> flyweightCache.put(seatClass, new SeatFlyweight("Economy", 76, 45));
                case "Business" -> flyweightCache.put(seatClass, new SeatFlyweight("Business", 96, 55));
                case "FirstClass" -> flyweightCache.put(seatClass, new SeatFlyweight("FirstClass", 116, 65));
                default -> throw new IllegalArgumentException("Unknown seat class: " + seatClass);
            }
        }
        return flyweightCache.get(seatClass);
    }

    public int getCacheSize() {
        return flyweightCache.size();
    }
}
