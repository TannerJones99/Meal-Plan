package com.tannerjones.mealplanner;

/*
This class will contain all information for a specific ingredient and handle all functions that ingredient need to handle.
 */

public class Ingredient {
    private String name;
    private String code;
    private String amount;
    private String measure;
    private boolean owned; // boolean that checks if the user has the ingredient or does not have.
    private Meal mealAssociated;

    public Ingredient(String name, String code, String amount, String measure, Meal meal) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.measure = measure;
        this.owned = false;
        this.mealAssociated = meal;
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

    public boolean isOwned() {
        return owned;
    }

    public void switchOwned(){
        owned = !owned;
    }

    public Meal getMealAssociated() {
        return mealAssociated;
    }
}
