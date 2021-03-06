package com.tannerjones.mealplanner;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static com.tannerjones.mealplanner.FoodSearch.context;

public class ApiDownload extends AsyncTask<Void, Void, ArrayList<Meal>> {

    private URL url;
    public static ArrayList<Meal> meals;
    private String api_key = context.getResources().getString(R.string.api_key);
    public String error = "";

    // Creates an FoodSearch URI using an api_key and a search term
    // i.e.
    // https://api.nal.usda.gov/fdc/v1/search?api_key=YOUR_API_KEY&generalSearchInput=YOUR_INPUT
    public ApiDownload(String search_term) {
        Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/search").buildUpon();
        builder.appendQueryParameter("api_key", api_key);
        builder.appendQueryParameter("generalSearchInput", search_term);

        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Begin drawing data from JSON objects
    @Override
    protected ArrayList<Meal> doInBackground(Void... voids) {
        StringBuilder json = new StringBuilder();
        ArrayList<Meal> meals = new ArrayList<>();

        String result = null;
        try {
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            JSONObject reader = new JSONObject(json.toString());
            JSONArray foods = reader.getJSONArray("foods");
            for (int i = 0; i < foods.length(); i++) {
                JSONObject food = foods.getJSONObject(i);
                String description = food.getString("description");
                int fdcId = food.getInt("fdcId");
                Ingredient ingredient = new Ingredient(food.getString("ingredients"), fdcId);
                ingredients.add(ingredient);

                Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/" + fdcId).buildUpon();
                builder.appendQueryParameter("api_key", api_key);

                try {
                    url = new URL(builder.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                connection = (HttpsURLConnection) url.openConnection();
                is = connection.getInputStream();

                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                json = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    json.append(line);
                }

                ArrayList<Nutrients> nutrients = new ArrayList<>();
                reader = new JSONObject(json.toString());
                JSONArray nutrientsObject = reader.getJSONArray("foodNutrients");
                for (int j = 0; j < nutrientsObject.length(); j++) {
                    JSONObject nutrientList = nutrientsObject.getJSONObject(j);
                    int amount = nutrientList.getInt("amount");
                    JSONObject nutrientDetails = nutrientList.getJSONObject("nutrient");
                    String name = nutrientDetails.getString("name");
                    String unit = nutrientDetails.getString("unitName");
                    Nutrients nutrient = new Nutrients(name, amount, unit, fdcId);
                    nutrients.add(nutrient);
                }

                Meal meal = new Meal(description, ingredients, nutrients, fdcId);
                meals.add(meal);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return meals;
    }

    @Override
    protected void onPostExecute(ArrayList<Meal> s) {
        meals = s;
        FoodSearch.apiDownload = null;

        FoodSearch.recyclerView.setAdapter(FoodSearch.mealAdapter);
    }
}
