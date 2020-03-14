package com.tannerjones.mealplanner;

import java.util.ArrayList;

public class MealPlan {
    private String name;
    private ArrayList<MealList> mealsList;

    public MealPlan(String name) {
        this.name = name;
        mealsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<MealList> getMealsList() {
        return mealsList;
    }

}
