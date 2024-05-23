package test;

import bottle.*;
import container.*;
import machines.*;
import track.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class TestManagement {

    private Box box;
    private Pallet pallet;
    private AutonomousForklift forklift;
    private Gripper gripper;
    private Track boxTrack;
    private Track palletTrack;

    @BeforeEach
    public void setUp() {
        box = new Box(UUID.randomUUID());
        pallet = new Pallet("APEJY");
        palletTrack = new Track();
        boxTrack = new Track();
        Trailer trailer = new Trailer();
        forklift = new AutonomousForklift(trailer);
        gripper = new Gripper(palletTrack, forklift);
    }

    @Test
    public void testBottleValidationInBox() {
        for (int i = 0; i < 9; i++) {
            Bottle bottle = new Bottle();
            bottle.fill();
            FrontLabel frontLabel = new FrontLabel();
            frontLabel.setHeader("Lovage");
            bottle.addFrontLabel(frontLabel);
            BackLabel backLabel = new BackLabel(System.nanoTime(), bottle.getserialNumber());
            bottle.addBackLabel(backLabel);
            box.addBottle(bottle);
        }

        assertTrue(areBottlesValid(box));
    }

    private boolean areBottlesValid(Box box) {
        for (Bottle bottle : box.getBottles()) {
            if (!bottle.isFilled() || bottle.getFrontLabelHeader(bottle) == null || bottle.getserialNumber() <= 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testPalletValidation() {
        Pallet testpallet = new Pallet("APEJY");

        for (int i = 0; i < 3; i++) {
            testpallet.addBox(new Box(UUID.randomUUID()));
        }

        palletTrack.add(testpallet);

        assertTrue(isPalletValid(testpallet, "APEJY", 3));
    }

    private boolean isPalletValid(Pallet pallet, String storeLabel, int expectedBoxCount) {
        if (!pallet.getPalletLabel().equals(storeLabel)) {
            return false;
        }
        int boxCount = 0;
        for (Box box : pallet.getBoxes()) {
            if (box != null) {
                boxCount++;
            }
        } return boxCount == expectedBoxCount;

    }

    @Test
    public void testEmergencyStop() {
        forklift.start();
        forklift.accelerate(10);
        forklift.emergencyStop();

        assertEquals(0, forklift.getSpeed());
    }

    @Test
    public void testAccelerateAndSlowDown() {
        forklift.start();
        forklift.accelerate(5);
        assertEquals(5, forklift.getSpeed());

        forklift.slowDown(2);
        assertEquals(3, forklift.getSpeed());
    }
}

