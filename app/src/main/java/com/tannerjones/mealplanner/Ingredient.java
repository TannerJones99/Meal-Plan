package com.tannerjones.mealplanner;

/*
This class will contain all information for a specific ingredient and handle all functions that ingredient need to handle.
 */

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private int mealId;


    public Ingredient(String name, int mealId) {
        this.name = name;
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public int getMealId() {
        return mealId;
    }
}
