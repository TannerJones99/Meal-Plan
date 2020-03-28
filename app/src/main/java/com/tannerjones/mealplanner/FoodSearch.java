package com.tannerjones.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import static com.tannerjones.mealplanner.ApiDownload.meals;

public class FoodSearch extends AppCompatActivity {

    public static ApiDownload apiDownload = null;
    public static Context context;
    public static RecyclerView recyclerView;
    public static MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_meals);
        context = getApplicationContext();
        mealAdapter = new MealAdapter();


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.search_edit_text);
                String search_term = editText.getText().toString();

                apiDownload = new ApiDownload(search_term);
                apiDownload.execute();
            }
        });
    }

    interface RecyclerViewClickListener {
        public void onClick(View view, int position);
    }

    public class MealAdapter extends RecyclerView.Adapter<FoodSearch.MealViewHolder>
            implements RecyclerViewClickListener {

        @NonNull
        @Override
        public FoodSearch.MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
            FoodSearch.MealViewHolder viewHolder = new FoodSearch.MealViewHolder(textView, this);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FoodSearch.MealViewHolder holder, int position) {
            holder.view.setText(meals.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return meals.size();
        }

        @Override
        public void onClick(View view, int position) {
            Intent intent = new Intent(getApplicationContext(), MealInfoActivity.class);
            intent.putExtra("MEAL", meals.get(position));
            startActivity(intent);

            /*

            ArrayList<Ingredient> ingredientsArrayList = meals.get(position).getIngredients();
            StringBuilder ingredients = new StringBuilder();
            ingredients.append("Ingredients:\n");

            //Handle Ingredients
            for (int i = 0; i < ingredientsArrayList.size(); i++) {
                if (meals.get(position).getId() == ingredientsArrayList.get(i).getMealId()) {
                    ingredients.append(meals.get(position).getIngredients().get(i).getName() + "\n");
                }
            }

            ArrayList<Nutrients> nutrientsArrayList = meals.get(position).getNutrients();
            StringBuilder nutrients = new StringBuilder();
            nutrients.append("Nutrients:\n");

            //Handle Nutrients
            for (int i = 0; i < nutrientsArrayList.size(); i++) {
                nutrients.append(meals.get(position).getNutrients().get(i).getName() + ": " +
                meals.get(position).getNutrients().get(i).getAmount() +
                meals.get(position).getNutrients().get(i).getUnit() + "\n");
            }
            */
        }
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        public TextView view;
        public RecyclerViewClickListener listener;

        public MealViewHolder(final TextView view, final MealAdapter listener) {
            super(view);
            this.view = view;
            this.listener = listener;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
