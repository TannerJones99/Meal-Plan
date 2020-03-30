package com.tannerjones.mealplanner;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MealSave {
    private final String FILE_PATH = "MealsInfo.txt";

    public void updateMealPlans(ArrayList<MealPlan> plans, Context context){
        try {
            FileOutputStream fo = context.openFileOutput(FILE_PATH, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fo);
            oos.writeObject(plans);

            oos.close();
            fo.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public ArrayList<MealPlan> getMealPlans(Context context){
        ArrayList<MealPlan> plans = null;
        try {
            FileInputStream fi = context.openFileInput(FILE_PATH);
            ObjectInputStream oi = new ObjectInputStream(fi);
            plans = (ArrayList<MealPlan>) oi.readObject();
            oi.close();
            fi.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
            Log.i("FILE NOT FOUND", "FILE NOT FOUND");
        } catch(IOException e){
            e.printStackTrace();
            Log.i("IO Exception", "IO Exception");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            Log.i("Class not found", "Class not found");
        }
        return plans;
    }

    // will be called when app is initiated to give the user one default meal plan.
    public void initFile(Context context){
        File file = new File(context.getFilesDir(), FILE_PATH);
        if(!file.exists()){
            updateMealPlans(new ArrayList<MealPlan>(), context);
            Log.i("new file", "new file");
        }
        if(getMealPlans(context).size() == 0){
            ArrayList<MealPlan> plans = new ArrayList<>();
            plans.add(new MealPlan("Meal Plan 1"));
            updateMealPlans(plans, context);
            Log.i("Plan added", "Plan Added");
        }
    }
}
