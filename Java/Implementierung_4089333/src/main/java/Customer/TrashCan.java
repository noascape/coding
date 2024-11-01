package Customer;

import Database.Item;
import lombok.Getter;
import java.util.Stack;

@Getter
public class TrashCan {
    @Getter
    private static final TrashCan instance = new TrashCan();
    private final Stack<Item> trashContainer;

    private TrashCan() {
        this.trashContainer = new Stack<>();
    }
}

