package RotationUnit;

import lombok.extern.slf4j.Slf4j;
import Database.Item;
import Database.Side;

@Slf4j
public class Roller {
    private final String name;

    public Roller(String name) {
        this.name = name;
    }

    public void rotate(Item item) {
        log.info("{} is starting rotation for the item.", name);

        if ("Roller B".equals(name)) {  // only Roller B updates the side
            Side newSide = getNextSide(item.getCurrentSide());
            item.setCurrentSide(newSide);
            log.info("Rotation completed. Item is now on the {} side.", newSide);
        }
    }

    private Side getNextSide(Side currentSide) {
        return switch (currentSide) {
            case FRONT -> Side.RIGHT;
            case RIGHT -> Side.BACK;
            case BACK -> Side.LEFT;
            case LEFT -> Side.FRONT;
        };
    }
}





