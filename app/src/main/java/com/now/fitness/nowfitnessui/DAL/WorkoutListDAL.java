package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutList;
import com.now.fitness.nowfitnessui.Model.DBAdapter;
import com.now.fitness.nowfitnessui.Object.WorkoutList;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/30/2017.
 */

public class WorkoutListDAL extends DBAdapter implements IWorkoutList, IWorkoutList.IWorkoutListSchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    public WorkoutListDAL(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected WorkoutListDAL cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    public boolean insertExercise() {

        setContentValue();
        try {
            return super.insert(WORKOUTLIST_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<WorkoutList> findAll() {
        return null;
    }

    @Override
    public List<WorkoutList> findByCategory(String category) {
        return null;
    }

    @Override
    public List<WorkoutList> findByName(String name) {
        return null;
    }

    private void setContentValue() {

        initialValues = new ContentValues();

        //read form text
        initialValues.put("USER_ID", "");
        initialValues.put("FIRSTNAME", "");
        initialValues.put("USER_ID", "");
        initialValues.put("FIRSTNAME", "");

    }

    private ContentValues getContentValue() {
        return initialValues;
    }

}
