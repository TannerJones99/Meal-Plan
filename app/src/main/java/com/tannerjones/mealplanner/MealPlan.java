package com.tannerjones.mealplanner;

import java.io.Serializable;
import java.util.ArrayList;

public class MealPlan implements Serializable {
    private String name;
    private MealList mealsList;

    public MealPlan(String name) {
        this.name = name;
        mealsList = new MealList();
    }

    public String getName() {
        return name;
    }

    public MealList getMealsList() {
        return mealsList;
    }

}
