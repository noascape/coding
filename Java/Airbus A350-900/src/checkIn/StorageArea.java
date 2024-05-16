package checkIn;

import java.util.HashSet;
import java.util.Set;

public class StorageArea {
    private int length;
    private int width;
    private int height;
    private Set<String> storedBaggage;

    public StorageArea(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.storedBaggage = new HashSet<>();
    }

    public boolean storeBaggage(String baggageTag) {
        if (storedBaggage.size() < length * width * height) {
            // Finde den nächsten freien Platz
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    for (int k = 0; k < height; k++) {
                        String position = i + "," + j + "," + k;
                        if (!storedBaggage.contains(position)) {
                            storedBaggage.add(position);
                            System.out.println("Baggage with tag " + baggageTag + " stored at position (" + position + ") in 3D area.");
                            return true;
                        }
                    }
                }
            }
        } else {
            System.out.println("Storage area is full. Cannot store baggage with tag " + baggageTag);
            return false;
        }
        return false;
    }
}


