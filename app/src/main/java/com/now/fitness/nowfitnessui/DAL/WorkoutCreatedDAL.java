package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutCreated;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.WorkoutCreated;

import java.util.List;

/**
 *  This class is the Workout created Data access layer which implements the interface IWorkoutCreated and IWorkoutCreatedSchema
 * @author Maycor Gerilla on 5/29/2017.
 */

public class WorkoutCreatedDAL extends DBContentProvider implements IWorkoutCreated, IWorkoutCreated.IWorkoutCreatedSchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    /**
     * @param db
     */
    public WorkoutCreatedDAL(SQLiteDatabase db) {
        super(db);
    }

    /**
     * @param cursor
     * @return WorkoutCreatedDAL
     */
    @Override
    protected WorkoutCreatedDAL cursorToEntity(Cursor cursor) {
        return null;
    }

    /**
     *  Inserts the workoutto table tbworkout_saved
     * @param work
     * @return boolean
     */
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

    /**
     * Updates the selected workout
     * @param work
     * @return boolean
     */
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

    /**
     * Deletes the workout selected
     * @param id
     * @return boolean
     */
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
