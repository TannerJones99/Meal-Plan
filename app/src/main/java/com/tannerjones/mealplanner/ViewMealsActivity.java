package com.tannerjones.mealplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMealsActivity extends AppCompatActivity {

    MealPlan plan;
    ArrayList<Meal> meals;
    MealList list;
    ArrayList<MealPlan> plans;
    String planToRemove;
    MealViewAdapter mealViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealviewlayout);
        Intent intent = getIntent();
        plan = (MealPlan) intent.getSerializableExtra("MEAL");
        plans = (ArrayList<MealPlan>) intent.getSerializableExtra("PLAN");
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

    public void removeMeal(){
        if(meals.size() == 0){
            Toast.makeText(getApplicationContext(), "No meals to delete", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Remove Meal");

            // Converting names of meals plans to a String[];
            String[] listOfMeals = new String[meals.size()];
            for (int i = 0; i < meals.size(); i++) {
                listOfMeals[i] = meals.get(i).getName();
            }
            int choice = -1;
            builder.setSingleChoiceItems(listOfMeals, choice, null);

            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ListView listView = ((AlertDialog) dialogInterface).getListView();
                    Object checkedItem = listView.getAdapter().getItem(listView.getCheckedItemPosition());

                    planToRemove = (String) checkedItem;
                    deleteMealByName(planToRemove);
                    new MealSave().updateMealPlans(plans, getApplicationContext());
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.i("Cancel", "Cancelled remove");
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void deleteMealByName(String name){
        for(int i = 0; i < meals.size(); i++){
            if(meals.get(i).getName().equals(name)){
                meals.remove(meals.get(i));
                mealViewAdapter.notifyItemRemoved(i);
                mealViewAdapter.notifyItemRangeChanged(i, meals.size());
            }
        }
    }
}
