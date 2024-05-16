package human;


public class Hand {
    private ArmAndLegPosition position;
    private Arm arm;

    public ArmAndLegPosition getPosition() {
        return position;
    }
    public void setArm(Arm arm) {this.arm = arm;}
    public void setPosition(ArmAndLegPosition position) {
        this.position = position;
    }
}
