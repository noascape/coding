package Automat;

import java.util.ArrayList;
import java.util.List;
import Enumeration.BeverageType;

public class Compartement {
    private List<Beverage> beverages;

    public Compartement(BeverageType beverageType) {
        this.beverages = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            beverages.add(new Beverage(beverageType));
        }
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }

    public void serveDrink() {
        if (!beverages.isEmpty()) {
            Beverage servedBeverage = beverages.remove(0);
            System.out.println(servedBeverage.getBeverageType() + " wird serviert.");
        } else {
            System.out.println("Keine Getränke verfügbar.");
        }
    }
}
