package bottle;

import java.util.HashSet;
import java.util.Set;

public class Identity {
    private static final Set<Integer> usedSerialNumbers = new HashSet<>();

    public static boolean isDuplicateSerialNumber(int serialNumber) {
        return !usedSerialNumbers.add(serialNumber);
    }
}




