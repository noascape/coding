import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Application {
    private final Map<String, Passenger> passengerDatabase = new HashMap<>();

    public Application() {
        passengerDatabase.put("ABC123", new Passenger("John Doe", "ABC123"));
        passengerDatabase.put("DEF456", new Passenger("Jane Smith", "DEF456"));
    }

    public static void main(String... args) {
        Application control = new Application();
        control.processBoarding("ABC123");
    }

    public void processBoarding(String boardingPassNumber) {
        System.out.println("initiating biometric scan for passenger.");
        boolean scanSuccess = performBiometricScan();

        if (!scanSuccess) {
            System.out.println("biometric scan failed. boarding denied.");
            return;
        }

        System.out.println("validating boarding pass.");
        boolean isValid = validateBoardingPass(boardingPassNumber);

        if (!isValid) {
            System.out.println("boarding pass is invalid. boarding denied.");
            return;
        }

        System.out.println("boarding pass and biometric scan successful. opening gate.");
        GateController gateController = new GateController();
        gateController.openGate();

        logEvent("passenger with boarding pass " + boardingPassNumber + " boarded successfully.");
    }

    private boolean performBiometricScan() {
        System.out.println("performing biometric scan.");
        return true;
    }

    private boolean validateBoardingPass(String boardingPassNumber) {
        return passengerDatabase.containsKey(boardingPassNumber);
    }

    private void logEvent(String message) {
        System.out.println("[log] " + message);
    }

    @Getter
    @Setter
    public static class Passenger {
        private String name;
        private String boardingPassNumber;

        public Passenger(String name, String boardingPassNumber) {
            this.name = name;
            this.boardingPassNumber = boardingPassNumber;
        }
    }

    private static class GateController {
        public void openGate() {
            System.out.println("gate open.");
        }
    }
}