package shared;

import dp.builder.Pizza;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ToString
public class PizzaMaker {
    private final String name;

    public void preparePizza(Pizza pizza) {
        log.info("preparing: {}", pizza);
    }

    public void cancelPizza(Pizza pizza) {
        log.info("canceling: {}", pizza);
    }
}