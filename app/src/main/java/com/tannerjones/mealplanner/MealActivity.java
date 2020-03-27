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
import java.util.Collections;

public class MealActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<MealPlan> mealPlans;
    MealPlanAdapter mealPlanAdapter;
    Button addButton;
    Button removeButton;
    String planToRemove;
    RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealPlans = new MealSave().getMealPlans(getApplicationContext());
        setContentView(R.layout.mealplanlayout);
        addButton = findViewById(R.id.AddMealPlan);
        removeButton = findViewById(R.id.RemoveMealPlan);
        addButton.setOnClickListener(this);
        removeButton.setOnClickListener(this);
        planToRemove = "";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Started", "onStart");
        rv = findViewById(R.id.MealPlanRv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        mealPlanAdapter = new MealPlanAdapter();
        rv.setAdapter(mealPlanAdapter);
        mealPlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.AddMealPlan){
            addMealPlan();
        }
        else if(view.getId() == R.id.RemoveMealPlan){
            removeMealPlan();
        }
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

    class MealPlanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView view;

        public MealPlanHolder(@NonNull TextView itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(this);
        }

        public TextView getView(){
            return view;
        }

        @Override
        public void onClick(View view) {
            mealListActivity(view);
        }
    }

    // adds a meal plan and gives it a default name value.
    public void addMealPlan(){
        MealPlan newMealPlan;
        int planNumber = 1;
        boolean nameAvailable = true;
        ArrayList<Integer> planNums = new ArrayList<>();
        for(int i = 0; i < mealPlans.size(); i++){
            if(mealPlans.size() != 0){
                planNums.add(Integer.parseInt(mealPlans.get(i).getName().substring(mealPlans.get(i).getName().length()-1)));
            }
        }
        int lowestNum = 1;
        Collections.sort(planNums);
        if(mealPlans.size() != 0) {
            do {
                for (int i = 0; i < planNums.size(); i++) {
                    if (planNums.get(i) == planNumber) {
                        nameAvailable = false;
                        planNumber++;
                    } else {
                        nameAvailable = true;
                    }
                }
            } while(!nameAvailable);
            newMealPlan = new MealPlan("Meal Plan " + planNumber);
        }
        else{
            newMealPlan = new MealPlan("Meal Plan 1");
        }
        mealPlans.add(newMealPlan);
        mealPlanAdapter.notifyDataSetChanged();
        new MealSave().updateMealPlans(mealPlans, getApplicationContext());
    }

    // pops up a dialog that lets them delete
    public void removeMealPlan(){
        if(mealPlans.size() == 1){
            Toast.makeText(getApplicationContext(), "Cannot delete last meal plan", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Remove Meal Plan");

            // Converting names of meals plans to a String[];
            String[] listOfMeals = new String[mealPlans.size()];
            for (int i = 0; i < mealPlans.size(); i++) {
                listOfMeals[i] = mealPlans.get(i).getName();
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
                    new MealSave().updateMealPlans(mealPlans, getApplicationContext());
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
        for(int i = 0; i < mealPlans.size(); i++){
            if(mealPlans.get(i).getName().equals(name)){
                mealPlans.remove(mealPlans.get(i));
                mealPlanAdapter.notifyItemRemoved(i);
                mealPlanAdapter.notifyItemRangeChanged(i, mealPlans.size());
            }
        }
    }

    // function that will start an activity that will allow the user to see the list of meals in a meal plan.
    public void mealListActivity(View view){
        MealPlan plan = null;
        TextView tv = (TextView) view;
        for(int i = 0; i < mealPlans.size(); i++){
            if(tv.getText().equals(mealPlans.get(i).getName())){
                plan = mealPlans.get(i);
            }
        }
        if(plan == null){
            Log.i("MEAL PLAN NOT FOUND", "Meal was not found");
        }
        else {
            Intent intent = new Intent(getApplicationContext(), ViewMealsActivity.class);
            intent.putExtra("MEAL", plan);
            intent.putExtra("PLAN", mealPlans);
            startActivity(intent);
        }
    }
}
