package de.project.observer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CabinCrew implements Observer {
    private final String name;

    @Override
    public void update(String message) {
        System.out.println("Cabin Crew " + name + " received notification: " + message);
    }
}
