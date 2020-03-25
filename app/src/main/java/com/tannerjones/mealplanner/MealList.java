package com.tannerjones.mealplanner;

/*
This class will read meals from a file and handle the list of meals.
 */

import java.io.Serializable;
import java.util.ArrayList;

public class MealList implements Serializable {
    private ArrayList<Meal> meals;

    public MealList(){
        UpdateMeals();
    }

    public boolean UpdateMeals(){
        // Will update the list of meals from the file whenever called. Returns true if successfully update. Returns false if not.
        meals = new ArrayList<>();

        return true;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }
}
