package dp.memento;

import dp.builder.Pizza;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PizzaMemento {
    private final Pizza pizza;
}