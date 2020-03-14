package com.tannerjones.mealplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealActivity extends AppCompatActivity {

    private ArrayList<MealPlan> mealPlans;


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
}
