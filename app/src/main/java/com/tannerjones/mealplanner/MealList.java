package com.tannerjones.mealplanner;

/*
This class will read meals from a file and handle the list of meals.
 */

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class MealList implements Serializable {
    private ArrayList<Meal> meals;

    public MealList(){
        meals = new ArrayList<>();
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void addMealToList(Meal meal, ArrayList<MealPlan> plans, Context context){
        meals.add(meal);
        new MealSave().updateMealPlans(plans, context);
    }
}
