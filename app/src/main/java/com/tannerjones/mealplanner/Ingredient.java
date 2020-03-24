package com.tannerjones.mealplanner;

/*
This class will contain all information for a specific ingredient and handle all functions that ingredient need to handle.
 */

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private int mealId;
    private boolean owned; // boolean that checks if the user has the ingredient or does not have.


    public Ingredient(String name, int mealId) {
        this.name = name;
        this.owned = false;
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public int getMealId() {
        return mealId;
    }

    public boolean isOwned() {
        return owned;
    }

    public void switchOwned(){
        owned = !owned;
    }
}
