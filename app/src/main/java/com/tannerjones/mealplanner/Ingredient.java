package com.tannerjones.mealplanner;

/*
This class will contain all information for a specific ingredient and handle all functions that ingredient need to handle.
 */

public class Ingredient {
    private String name;
    private String code;
    private String amount;
    private String measure;
    private boolean have; // boolean that checks if the user has the ingredient or does not have.

    public Ingredient(String name, String code, String amount, String measure) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAmount() {
        return amount;
    }

    public String getMeasure() {
        return measure;
    }
}
