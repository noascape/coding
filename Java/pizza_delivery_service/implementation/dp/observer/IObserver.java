package dp.observer;

import shared.PizzaStatus;

public interface IObserver {
    void update(PizzaStatus status);
}