package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutCreated;
import com.now.fitness.nowfitnessui.Model.DBAdapter;
import com.now.fitness.nowfitnessui.Object.WorkoutCreated;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/29/2017.
 */

public class WorkoutCreatedDAL extends DBAdapter implements IWorkoutCreated, IWorkoutCreated.IWorkoutCreatedSchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    public WorkoutCreatedDAL(SQLiteDatabase db) {
        super(db);
    }

    @Override
    protected WorkoutCreatedDAL cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    public boolean insertWorkout(WorkoutCreated work) {

        setContentValue(work);
        try {
            return super.insert(WORKOUTCREATED_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateWorkout(WorkoutCreated work) {
        setContentValue(work);
        try {
            return super.update(WORKOUTCREATED_TABLE, getContentValue(), COLUMN_NAME, null)>0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteWorkout(int id) {

        try {
            return super.delete(WORKOUTCREATED_TABLE, COLUMN_NAME, null)>0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<WorkoutCreated> findAll() {
        return null;
    }

    @Override
    public List<WorkoutCreated> findByUserId(int id) {
        return null;
    }

    @Override
    public List<WorkoutCreated> findByWorkoutId(int id) {
        return null;
    }

    @Override
    public List<WorkoutCreated> findByProgramName(String name) {
        return null;
    }

    private void setContentValue(WorkoutCreated wrk) {

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
