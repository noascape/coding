package PA;

import Database.Item;
import lombok.extern.slf4j.Slf4j;
import java.util.Stack;

@Slf4j
public class WasteContainer {
    private final Stack<Item> plasticBottleContainer;
    private final Stack<Item> glassBottleContainer;
    private final Stack<Item> canContainer;

    public WasteContainer() {
        plasticBottleContainer = new Stack<>();
        glassBottleContainer = new Stack<>();
        canContainer = new Stack<>();
    }

    public void addPlasticBottle(Item item) {
        plasticBottleContainer.push(item);
        log.info("Plastic bottle: {} added to plastic container" , item.getLabel());
    }

    public void addGlassBottle(Item item) {
        glassBottleContainer.push(item);
        log.info("Glass bottle: {} added to glass container" , item.getLabel());
    }

    public void addCan(Item item) {
        canContainer.push(item);
        log.info("Can: {} added to can container" , item.getLabel());
    }

    public void compress(Item item) {
        log.info("Compressing item {} " , item.getLabel());
        item.setCompressed(true);
    }
}

