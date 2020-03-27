package com.tannerjones.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MealInfoActivity extends AppCompatActivity {

    RecyclerView nutRV;
    RecyclerView ingRV;
    Meal meal;
    MealNutAdapter mealNutAdapter;
    MealIngAdapter mealIngAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealinfolayout);
        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("MEAL");
    }

    @Override
    protected void onStart() {
        super.onStart();
        nutRV = findViewById(R.id.nutrientsRecycler);
        LinearLayoutManager linearLayoutManagerNut = new LinearLayoutManager(this);
        nutRV.setLayoutManager(linearLayoutManagerNut);
        mealNutAdapter = new MealNutAdapter();
        nutRV.setAdapter(mealNutAdapter);
        mealNutAdapter.notifyDataSetChanged();

        ingRV = findViewById(R.id.ingredientsRecycler);
        LinearLayoutManager linearLayoutManagerIng = new LinearLayoutManager(this);
        ingRV.setLayoutManager(linearLayoutManagerIng);
        mealIngAdapter = new MealIngAdapter();
        ingRV.setAdapter(mealIngAdapter);
        mealIngAdapter.notifyDataSetChanged();
    }

    class MealNutAdapter extends RecyclerView.Adapter<MealNutHolder> {

        @NonNull
        @Override
        public MealNutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MealNutHolder mealNutHolder = new MealNutHolder(textView);
            return mealNutHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MealNutHolder holder, int position) {
            TextView view = holder.getView();
            view.setText(meal.getNutrients().get(position).getName());
        }

        @Override
        public int getItemCount() {
            return meal.getNutrients().size();
        }
    }

    class MealNutHolder extends RecyclerView.ViewHolder {

        private TextView view;

        public MealNutHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
        }

        public TextView getView(){
            return view;
        }

    }

    class MealIngAdapter extends RecyclerView.Adapter<MealIngHolder> {

        @NonNull
        @Override
        public MealIngHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MealIngHolder mealIngHolder = new MealIngHolder(textView);
            return mealIngHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MealIngHolder holder, int position) {
            TextView view = holder.getView();
            view.setText(meal.getIngredients().get(position).getName());
        }

        @Override
        public int getItemCount() {
            return meal.getIngredients().size();
        }
    }

    class MealIngHolder extends RecyclerView.ViewHolder {

        private TextView view;

        public MealIngHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
        }

        public TextView getView(){
            return view;
        }

    }
}
