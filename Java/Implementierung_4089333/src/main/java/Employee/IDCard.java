package Employee;
import lombok.Getter;

@Getter
public class IDCard {
    private final double id;

    public IDCard(double generatedId) {
        this.id = generatedId;
    }
}

