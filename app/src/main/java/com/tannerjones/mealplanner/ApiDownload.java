package com.tannerjones.mealplanner;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

public class ApiDownload extends AsyncTask<Void, Void, String> {

    private static final String search_term = "";
    private URL url;

    // Creates an FoodSearch URI using an api_key and a search term
    // i.e.
    // https://api.nal.usda.gov/fdc/v1/search?api_key=YOUR_API_KEY&generalSearchInput=YOUR_INPUT
    public ApiDownload(String search_term, Context context) {
        Uri.Builder builder = Uri.parse("http://api.nal.usda.gov/fdc/v1/search").buildUpon();
        builder.appendQueryParameter("api_key", context.getResources().getString(R.string.api_key));
        builder.appendQueryParameter("generalSearchInput", search_term);

        try {
            url = new URL(builder.toString());
            Log.i("RESULT", url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    // Begin drawing data from JSON objects
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder json = new StringBuilder();
        HashSet<String> tagSet = new HashSet<>();
        String result = null;

        return null;
    }
}
