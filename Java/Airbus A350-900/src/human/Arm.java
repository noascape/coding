package human;

public class Arm {
    private ArmAndLegPosition position;
    private Hand leftHand;
    private Hand rightHand;

    public void addLeftHand(Hand hand) {
        leftHand = hand;
        leftHand.setPosition(ArmAndLegPosition.LEFT);
    }

    public void addRightHand(Hand hand) {
        rightHand = hand;
        rightHand.setPosition(ArmAndLegPosition.RIGHT);
    }

    public void setLeftHand(Hand hand) {
        this.leftHand = hand;
        leftHand.setPosition(ArmAndLegPosition.LEFT);
        this.rightHand = hand;
        rightHand.setPosition(ArmAndLegPosition.RIGHT);
    }

    public void setPosition(ArmAndLegPosition position) {
    }
}
