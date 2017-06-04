package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutCategory;
import com.now.fitness.nowfitnessui.Model.DBAdapter;
import com.now.fitness.nowfitnessui.Object.WorkoutCategory;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/29/2017.
 */

public class WorkoutCategoryDAL extends DBAdapter implements IWorkoutCategory, IWorkoutCategory.IWorkoutCategorySchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    @Override
    protected WorkoutCategoryDAL cursorToEntity(Cursor cursor) {
        return null;
    }

    public WorkoutCategoryDAL(SQLiteDatabase mDb) {
        super(mDb);
    }

    @Override
    public boolean insertWorkoutCategory() {
        // set values
        setContentValue();
        try {
            return super.insert(WORKOUTCAT_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<WorkoutCategory> findAll() {
        return null;
    }

    @Override
    public List<WorkoutCategory> findByCode(String code) {
        return null;
    }

    @Override
    public List<WorkoutCategory> findByName(String name) {
        return null;
    }

    private void setContentValue() {

        initialValues = new ContentValues();

        //read form text
        initialValues.put("USER_ID", "");
        initialValues.put("FIRSTNAME", "");

    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
