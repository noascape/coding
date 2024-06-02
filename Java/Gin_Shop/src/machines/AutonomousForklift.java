package machines;

import java.util.Random;
import container.Pallet;
import container.Trailer;

public class AutonomousForklift implements IAutonomousForklift {
    private final String chassis = "Chassis";
    private final int wheels = 4;
    private final String mast = "Mast";
    private final int fork = 2;
    private boolean isStarted;
    private double speed;
    private Pallet currentPallet;
    private final String engine = "Exchangeable AI enginge in 2 versions (2048/4096 GPU, composition";

    private Trailer currentTrailer;
    private Random random = new Random();

    public AutonomousForklift(Trailer trailer) {
        currentTrailer = trailer;
        this.speed = 0;
    }

    public void drive(Pallet pallet) {
        if (currentTrailer.isFull()) {
            System.out.println("Trailer is full.");
            System.out.println("Autonomous Forklift turned off!");
            isStarted = false;
            return;
        }
        start();
        accelerate(5);
        turnLeft(30);
        accelerate(20);
        slowDown(2);
        turnRight(12);
        moveForksDown();
        stop();
        take(pallet);
        moveForksUp();
        accelerate(5);
        turnLeft(12);
        accelerate(20);
        turnRight(30);
        slowDown(2);
        stop();
        releasePallet(pallet);
    }

    @Override
    public void start() {
        if (!isStarted) {
            isStarted = true;
            System.out.println("Starting Autonomous Forklift.");
        } else {
            System.out.println("Autonomous Forklift already started.");
        }
    }


    @Override
    public void accelerate(double speed) {
        if (isStarted) {
            if (isObstacleDetected()) {
                System.out.println("Obstacle Detected!!!");
                emergencyStop();
                return;
            }
            this.speed += speed;
            System.out.println("Accelerating Autonomous Forklift. Current speed: " + speed);
        } else {
            System.out.println("Cannot accelerate. Autonomous Forklift is not started.");
        }
    }

    @Override
    public void slowDown(double speed) {
        if (isStarted) {
            if (isObstacleDetected()) {
                System.out.println("Obstacle Detected!!!");
                emergencyStop();
                return;
            }
            this.speed -= speed;
            System.out.println("Slowing Autonomous Forklift. Current speed: " + speed);
        } else {
            System.out.println("Cannot slow down. Autonomous Forklift is not started.");
        }
    }

    @Override
    public void turnLeft(double degree) {
        if (isStarted) {
            if (isObstacleDetected()) {
                System.out.println("Obstacle Detected!!!");
                emergencyStop();
                return;
            }
            if (degree >= 5.0 && degree <= 30.0) {
                System.out.println("Turning left by " + degree + " degrees.");
            } else {
                System.out.println("Cannot turn left. Autonomous Forklift is not started.");
            }
        }
    }

    @Override
    public void turnRight(double degree) {
        if (isStarted) {
            if (isObstacleDetected()) {
                System.out.println("Obstacle Detected!!!");
                emergencyStop();
                return;
            }
            if (degree >= 5.0 && degree <= 30.0) {
                System.out.println("Turning right by " + degree + " degrees.");
            }
        } else {
            System.out.println("Cannot turn right. Autonomous Forklift is not started.");
        }
    }

    @Override
    public void stop() {
        if (isStarted) {
            this.speed = 0;
            System.out.println("Stopping Autonomous Forklift.");
        } else {
            System.out.println("Cannot stop. Autonomous Forklift is not started.");
        }
    }

    @Override
    public void emergencyStop() {
        this.speed = 0;
        System.out.println("Emergency Stopping Autonomous Forklift.");
    }

    @Override
    public void moveForksUp() {
        if (isStarted) {
            System.out.println("Moving Forks Up Autonomous Forklift.");
        }
    }

    @Override
    public void moveForksDown() {
        if (isStarted) {
            System.out.println("Moving Forks Down Autonomous Forklift.");
        }
    }

    @Override
    public void take(Pallet pallet) {
        if (isStarted) {
            currentPallet = pallet;
            System.out.println("Autonomous Forklift picks up the Pallet.");
        }
    }

    @Override
    public void releasePallet(Pallet pallet) {
        if (currentTrailer.isFull()) {
            System.out.println("Trailer is full.");
            return;
        }
       currentTrailer.addPallet(pallet);
        System.out.println("Autonomous Forklift released Pallet on the Trailer.");
    }

    private boolean isObstacleDetected() {
        return random.nextInt(10) == 0;
    }

    //Testmanagement
    public boolean isStarted() {
        return isStarted;
    }
    public double getSpeed() {
        return this.speed;
    }
}
