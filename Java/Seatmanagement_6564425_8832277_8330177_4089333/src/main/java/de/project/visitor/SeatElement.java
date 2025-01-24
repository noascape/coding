package de.project.visitor;

public interface SeatElement {
    void accept(SeatVisitor visitor);
}
