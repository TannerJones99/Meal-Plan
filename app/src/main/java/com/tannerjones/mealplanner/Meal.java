package com.tannerjones.mealplanner;

import java.util.ArrayList;

/*
This class will contain all information for a specific meal and handle all functions that meals need to handle.
 */

public class Meal {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Nutrients> nutrients;
    private String id;
    private String foodCode;
    private String foodCategory;



    public Meal(String name, ArrayList<Ingredient> ingredient, ArrayList<Nutrients> nutrients, String id, String foodCode, String foodCategory) {
        this.name = name;
        this.ingredients = ingredient;
        this.nutrients = nutrients;
        this.id = id;
        this.foodCode = foodCode;
        this.foodCategory = foodCategory;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getId() {
        return id;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public ArrayList<Nutrients> getNutrients() {
        return nutrients;
    }
}
