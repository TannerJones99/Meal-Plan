package com.tannerjones.mealplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodSearch extends AppCompatActivity {

    public static ApiDownload apiDownload = null;
    MealAdapter mealAdapter;
    ArrayList<Meal> meals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_meals);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.search_edit_text);
                String search_term = editText.getText().toString();
                apiDownload = new ApiDownload(search_term, getApplicationContext());
                apiDownload.execute();

                mealAdapter = new MealAdapter();
                recyclerView.setAdapter(mealAdapter);
            }
        });

    }

    public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView view;

        public MealViewHolder(TextView view) {
            super(view);
            this.view = view;
            this.view.setOnClickListener(this);
        }

        public TextView getView() {
            return view;
        }

        @Override
        public void onClick(View v) {
            Meal meal = meals.get(getAdapterPosition());
            AlertDialog.Builder builder = new AlertDialog.Builder(FoodSearch.this);
            builder.setMessage(meal.getName() + ", " + meal.getIngredients());

        }
    }

    public class MealAdapter extends RecyclerView.Adapter<MealViewHolder> {

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
            MealViewHolder viewHolder = new MealViewHolder(textView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
            TextView view = holder.getView();
            view.setText(meals.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return meals.size();
        }
    }
}
