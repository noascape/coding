package dp.command;

import dp.builder.Pizza;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import shared.PizzaMaker;

@RequiredArgsConstructor
@ToString
public class PreparePizzaCommand extends Command {
    private final PizzaMaker pizzaMaker;
    private final Pizza pizza;

    public void execute() {
        pizzaMaker.preparePizza(pizza);
    }

    public void undo() {
        pizzaMaker.cancelPizza(pizza);
    }
}