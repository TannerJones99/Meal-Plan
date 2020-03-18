package com.tannerjones.mealplanner;

import java.util.ArrayList;

/*
This class will contain all information for a specific meal and handle all functions that meals need to handle.
 */

public class Meal {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Nutrients> nutrients;
    private int id;


    public Meal(String name, ArrayList<Ingredient> ingredient, ArrayList<Nutrients> nutrients, int id) {
        this.name = name;
        this.ingredients = ingredient;
        this.nutrients = nutrients;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Nutrients> getNutrients() {
        return nutrients;
    }

    public int getId() {
        return id;
    }
}
