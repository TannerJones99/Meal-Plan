package com.tannerjones.mealplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMealsActivity extends AppCompatActivity {

    MealPlan plan;
    ArrayList<Meal> meals;
    MealList list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealviewlayout);
        Intent intent = getIntent();
        plan = (MealPlan) intent.getSerializableExtra("MEAL");
        list = plan.getMealsList();
        meals = list.getMeals();
    }

    class MealViewAdapter extends RecyclerView.Adapter<MealViewHolder> {

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MealViewHolder mealViewHolder = new MealViewHolder(textView);
            return mealViewHolder;
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

    class MealViewHolder extends RecyclerView.ViewHolder {

        private TextView view;

        public MealViewHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
        }

        public TextView getView(){
            return view;
        }
    }
}
