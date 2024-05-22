package machines;

import container.Pallet;

public interface IAutonomousForklift {

    void start();
    void accelerate(double speed);
    void slowDown(double speed);
    void turnLeft(double degree);
    void turnRight(double degree);
    void stop();
    void emergencyStop();
    void moveForksUp();
    void moveForksDown();
    void take(Pallet pallet);
    void releasePallet(Pallet pallet);

}
