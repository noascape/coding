package flightmanagement;

public interface IFlightManagement {
    void executeClimb();
    void executeSink();
    void executeTurnLeft();
    void executeTurnLeft(int degrees);
    void executeTurnRight();
    void executeTurnRight(int degrees);
}
