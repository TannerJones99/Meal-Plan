package com.tannerjones.mealplanner;

import java.util.ArrayList;

public class MealPlan {
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
