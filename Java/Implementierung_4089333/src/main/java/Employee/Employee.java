package Employee;

import lombok.Getter;
import PA.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class Employee {
    private final String name;
    private final IDCard idCard;
    private static double cardIdCounter = 1;

    public Employee(String name) {
        this.name = name;
        this.idCard = new IDCard(generateCardId());
    }

    private double generateCardId() {
        return cardIdCounter++;
    }

    public double getCardId() {
        return idCard.getId();
    }

    public void authenticate(Reader reader) {
        log.info("{} holds his card up to the reader" , name);
        reader.scanCard(idCard);
    }
}
