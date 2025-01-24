package de.project.composite;

import java.util.ArrayList;
import java.util.List;

public class SeatGroup extends SeatComponent {
    private final String groupName;
    private final List<SeatComponent> components = new ArrayList<>();

    public SeatGroup(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public void add(SeatComponent component) {
        components.add(component);
    }

    @Override
    public void remove(SeatComponent component) {
        components.remove(component);
    }

    @Override
    public SeatComponent getChild(int index) {
        return components.get(index);
    }

    @Override
    public void displayDetails() {
        System.out.println("Group: " + groupName);
        for (SeatComponent component : components) {
            component.displayDetails();
        }
    }
}
