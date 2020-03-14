package com.tannerjones.mealplanner;

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

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    private ArrayList<MealPlan> mealPlans;
    MealPlanAdapter mealPlanAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealPlans = new ArrayList<>(); // temporary declaration until a way to save meal plans.
        setContentView(R.layout.mealplanlayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView rv = findViewById(R.id.MealPlanRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        mealPlanAdapter = new MealPlanAdapter();
        rv.setAdapter(mealPlanAdapter);
    }

    class MealPlanAdapter extends RecyclerView.Adapter<MealPlanHolder> {

        @NonNull
        @Override
        public MealPlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            MealPlanHolder mealPlanHolder = new MealPlanHolder(textView);
            return mealPlanHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MealPlanHolder holder, int position) {
            TextView view = holder.getView();
            view.setText(mealPlans.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return mealPlans.size();
        }
    }

    class MealPlanHolder extends RecyclerView.ViewHolder {

        private TextView view;

        public MealPlanHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
        }

        public TextView getView(){
            return view;
        }
    }

    public void addMealPlan(){
        // class will pop up dialog that User will create a MealPlan object and add it to ArrayList.
    }

    public void removeMealPlan(){
        // class will pop up dialog that user will delete a MealPlan
    }
}
