package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutCategory;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.WorkoutCategory;

import java.util.List;

/**
 * This class is the Workout category data access layer which implements the IWorkoutCategory and IWorkoutCategorySchema interface
 * @author Maycor Gerilla on 5/29/2017.
 */

public class WorkoutCategoryDAL extends DBContentProvider implements IWorkoutCategory, IWorkoutCategory.IWorkoutCategorySchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    /**
     * @param cursor
     * @return WorkoutCategoryDAL
     */
    @Override
    protected WorkoutCategoryDAL cursorToEntity(Cursor cursor) {
        return null;
    }

    /**
     * @param mDb
     */
    public WorkoutCategoryDAL(SQLiteDatabase mDb) {
        super(mDb);
    }

    /**
     * Inserts workout category to tbworkout_category table
     * @return boolean
     */
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

    /**
     * Retrieves all the workout category
     * @return List<WorkoutCategory>
     */
    @Override
    public List<WorkoutCategory> findAll() {
        return null;
    }

    /**
     * Retrieves the workout category by code
     * @param code
     * @return List<WorkoutCategory>
     */
    @Override
    public List<WorkoutCategory> findByCode(String code) {
        return null;
    }

    /**
     *
     * @param name
     * @return List<WorkoutCategory>
     */
    @Override
    public List<WorkoutCategory> findByName(String name) {
        return null;
    }

    private void setContentValue() {

        initialValues = new ContentValues();

        //read from text
        initialValues.put("CATEGORY_ID", "");
        initialValues.put("CATEGORY_TYPE", "");

    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
