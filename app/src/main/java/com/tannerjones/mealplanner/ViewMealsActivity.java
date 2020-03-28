package com.tannerjones.mealplanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewMealsActivity extends AppCompatActivity implements View.OnClickListener {

    MealPlan plan;
    ArrayList<Meal> meals;
    MealList list;
    ArrayList<MealPlan> plans;
    String planToRemove;
    MealViewAdapter mealViewAdapter;
    Button removeButton;
    RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealviewlayout);
        Intent intent = getIntent();
        plan = (MealPlan) intent.getSerializableExtra("MEAL");
        plans = (ArrayList<MealPlan>) intent.getSerializableExtra("PLAN");
        list = plan.getMealsList();
        meals = list.getMeals();
        removeButton = findViewById(R.id.removeMeal);
        removeButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rv = findViewById(R.id.MealRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        mealViewAdapter = new MealViewAdapter();
        rv.setAdapter(mealViewAdapter);
        mealViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if((Button) view == findViewById(R.id.removeMeal)){
            removeMeal();
        }
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

    class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView view;

        public MealViewHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
        }

        public TextView getView(){
            return view;
        }

        @Override
        public void onClick(View view) {
            mealInfoActivity(view);
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

            builder.setNegativeButton("Cancel", null);

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

    public void mealInfoActivity(View view){
        Meal meal = null;
        TextView tv = (TextView) view;
        for(int i = 0; i < meals.size(); i++){
            if(tv.getText().equals(meals.get(i).getName())){
                meal = meals.get(i);
            }
        }
        if(meal == null){
            Log.i("MEAL NOT FOUND", "Meal was not found");
        }
        else{
            Intent intent = new Intent(getApplicationContext(), MealInfoActivity.class);
            intent.putExtra("MEAL", meal);
            startActivity(intent);
        }
    }


}
