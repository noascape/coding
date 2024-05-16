import java.util.ArrayList;
import java.util.Random;

public class Identity {

    private final ArrayList<Integer> usedserialNumbers = new ArrayList<Integer>();

    public String generateSerialNumber() {

        Random random = new Random();
        int number = random.nextInt(900000000) + 1000000000;

        while (usedserialNumbers.contains(number)) {
            random = new Random();
            number = random.nextInt(900000000) + 1000000000;
        }

        usedserialNumbers.add(number);

        return String.valueOf(number);
    }
}

