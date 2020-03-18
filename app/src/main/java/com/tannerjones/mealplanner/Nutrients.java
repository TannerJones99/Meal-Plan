package com.tannerjones.mealplanner;

public class Nutrients {
    private String name;
    private int amount;
    private int mealId;
    private String unit;

    public Nutrients(String name, int amount, String unit, int mealId) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getMealId() {
        return mealId;
    }

    public String getUnit() {
        return unit;
    }
}
