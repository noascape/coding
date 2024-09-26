package Automat;

import Enumeration.BeverageType;

public class Beverage {
    private BeverageType beverageType;

    public Beverage(BeverageType beverageType) {
        this.beverageType = beverageType;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }
}