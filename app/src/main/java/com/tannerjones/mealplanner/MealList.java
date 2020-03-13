package com.tannerjones.mealplanner;

/*
This class will read meals from a file and handle the list of meals.
 */

import java.util.ArrayList;

public class MealList {
    private ArrayList<Meal> meals;
    private String MEAL_FILE = "File Path";

    public MealList(){
        UpdateMeals();

    }

    public boolean UpdateMeals(){
        // Will update the list of meals from the file whenever called. Returns true if successfully update. Returns false if not.
        meals = new ArrayList<>();

        return true;
    }
}
