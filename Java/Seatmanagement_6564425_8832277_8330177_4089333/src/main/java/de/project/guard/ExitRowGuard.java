package de.project.guard;

public class ExitRowGuard implements SeatGuard {
    @Override
    public boolean validate(SeatRequest request) {
        if (request.isExitRow() && request.getAge() < 18) {
            System.out.println("Validation failed: Exit row seat can only be reserved by adults.");
            return false;
        }
        System.out.println("Validation passed for exit row seat.");
        return true;
    }
}
