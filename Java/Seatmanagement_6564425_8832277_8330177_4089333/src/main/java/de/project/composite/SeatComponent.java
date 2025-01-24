package de.project.composite;

public abstract class SeatComponent {
    public void add(SeatComponent component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public void remove(SeatComponent component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public SeatComponent getChild(int index) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    public abstract void displayDetails();
}
