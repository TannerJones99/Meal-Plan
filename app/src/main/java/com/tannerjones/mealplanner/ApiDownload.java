package com.tannerjones.mealplanner;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
import java.util.TreeSet;

import javax.net.ssl.HttpsURLConnection;

public class ApiDownload extends AsyncTask<Void, Void, ArrayList<String>> {

    private String search_term = "";
    private URL url;

    // Creates an FoodSearch URI using an api_key and a search term
    // i.e.
    // https://api.nal.usda.gov/fdc/v1/search?api_key=YOUR_API_KEY&generalSearchInput=YOUR_INPUT
    public ApiDownload(String search_term, Context context) {
        this.search_term = search_term;
        Uri.Builder builder = Uri.parse("https://api.nal.usda.gov/fdc/v1/search").buildUpon();
        builder.appendQueryParameter("api_key", context.getResources().getString(R.string.api_key));
        builder.appendQueryParameter("generalSearchInput", search_term);

        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Begin drawing data from JSON objects
    @Override
    protected ArrayList<String> doInBackground(Void... voids) {
        StringBuilder json = new StringBuilder();
        ArrayList<String> foodDescription = new ArrayList<>();

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

            JSONObject reader = new JSONObject(json.toString());
            JSONArray foods = reader.getJSONArray("foods");
            for (int i = 0; i < foods.length(); i++) {
                JSONObject food = foods.getJSONObject(i);
                String descriptions = food.getString("description");
                foodDescription.add(descriptions);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foodDescription;
    }

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        for (int i = 0; i < s.size(); i++) {
            Log.i("NAME", s.get(i));
        }
        FoodSearch.apiDownload = null;
    }
}
