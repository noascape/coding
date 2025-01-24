package de.project.visitor;

public class SeatReportVisitor implements SeatVisitor {
    @Override
    public void visit(IndividualSeat seat) {
        System.out.println("Visiting seat: " + seat.getSeatId() + " (" + seat.getSeatClass() + ")");
    }

    @Override
    public void visit(SeatGroup group) {
        System.out.println("Visiting group: " + group.groupName);
    }
}
