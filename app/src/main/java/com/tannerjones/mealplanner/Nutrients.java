package com.tannerjones.mealplanner;

public class Nutrients {
    private String name;
    private String amount;
    private String unit;
    private Meal mealAssociated;

    public Nutrients(String name, String amount, String unit, Meal meal) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.mealAssociated = meal;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public Meal getMealAssociated() {
        return mealAssociated;
    }
}
