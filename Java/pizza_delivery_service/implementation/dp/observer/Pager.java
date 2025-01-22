package dp.observer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import shared.PizzaStatus;

@Slf4j
@RequiredArgsConstructor
@Getter
@ToString
public class Pager implements IObserver {
    private final String driverName;

    public void update(PizzaStatus status) {
        if (status == PizzaStatus.READY_FOR_DELIVERY) {
            log.info("driver {} received notification. pizza is ready for delivery.", driverName);
        }
    }
}