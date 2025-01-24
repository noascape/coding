package de.project.visitor;

public interface SeatVisitor {
    void visit(IndividualSeat seat);
    void visit(SeatGroup group);
}
