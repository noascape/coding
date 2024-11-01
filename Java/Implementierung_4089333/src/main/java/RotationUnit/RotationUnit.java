package RotationUnit;

import lombok.extern.slf4j.Slf4j;
import Database.Item;

@Slf4j
public class RotationUnit {
    private final Scanner scanner;
    private final Roller rollerA;
    private final Roller rollerB;

    public RotationUnit() {
        this.rollerA = new Roller("Roller A");
        this.rollerB = new Roller("Roller B");
        this.scanner = new Scanner(this);
    }

    public void rotateItem(Item item) {
        rollerA.rotate(item);
        rollerB.rotate(item);
    }

    public String scanItem(Item item) {
        return scanner.scanItem(item);
    }
}






