package human;


import body.Body;

public class HumanBody {
    private final Head head;
    private Hand leftHand;
    private Hand rightHand;
    private  Arm rightArm;
    private  Arm leftArm;
    private  Leg leftLeg;
    private  Leg rightLeg;

    public HumanBody() { head = new Head(); }
    public void addRightArm(Arm arm) {
        rightArm = arm;
        rightArm.setPosition(ArmAndLegPosition.RIGHT);
    }
    public void addLeftArm(Arm arm) {
        leftArm = arm;
        leftArm.setPosition(ArmAndLegPosition.LEFT);
    }

    public void addRightLeg(Leg leg) {
        rightLeg = leg;
        rightLeg.setPosition(ArmAndLegPosition.RIGHT);
    }
    public void addLeftLeg(Leg leg) {
        leftLeg = leg;
        leftLeg.setPosition(ArmAndLegPosition.LEFT);
    }


}
