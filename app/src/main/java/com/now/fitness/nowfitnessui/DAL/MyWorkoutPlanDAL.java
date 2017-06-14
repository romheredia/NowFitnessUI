package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IMyWorkoutPlan;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;

import java.util.ArrayList;
import java.util.List;

/**
 *  This class is the MyWorkoutPlan created Data access layer which implements the interface IWorkoutCreated and IWorkoutCreatedSchema
 * @author Romeric Heredia on 6/06/2017.
 */

public class MyWorkoutPlanDAL extends DBContentProvider implements IMyWorkoutPlan, IMyWorkoutPlan.IMyWorkoutPlanSchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    public MyWorkoutPlanDAL(SQLiteDatabase db) {
        super(db);
    }

    /**
     * Inserts workout to table tbmyworkout_plan
     * @param myWorkoutPlan
     * @return boolean
     */
    @Override
    public boolean insertMyWorkoutPlan(MyWorkoutPlan myWorkoutPlan) {
        setContentValue(myWorkoutPlan);
        try {

            return super.insert(MYWORKOUTPLAN_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException e) {
            Log.d(TAG, "error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateMyWorkoutPlan(MyWorkoutPlan myWorkoutPlan) {
        return false;
    }

    @Override
    public List<MyWorkoutPlan> findAll() {
        List<MyWorkoutPlan> myWorkoutPlanList = new ArrayList<>();
        cursor = super.query(MYWORKOUTPLAN_TABLE, MYWORKOUTPLAN_COLUMNS, null, null, COLUMN_PLAN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                MyWorkoutPlan myWorkoutPlan = cursorToEntity(cursor);
                myWorkoutPlanList.add(myWorkoutPlan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return myWorkoutPlanList;
    }

    @Override
    public List<MyWorkoutPlan> findByCode(String code) {
        return null;
    }

    /**
     * Retrieves MyWorkoutPlan by name
     * @param name
     * @return List<MyWorkoutPlan>
     */
    @Override
    public List<MyWorkoutPlan> findByName(String name) {
        List<MyWorkoutPlan> myWorkoutPlanList = new ArrayList<>();
        cursor = super.query(MYWORKOUTPLAN_TABLE, null, COLUMN_PLAN_NAME + "= ?", new String[] { name }, null);
        Log.i(TAG, "name: "+name);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                MyWorkoutPlan myWorkoutPlan = cursorToEntity(cursor);
                myWorkoutPlanList.add(myWorkoutPlan);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return myWorkoutPlanList;
    }

    /**
     * Retrieves result from cursor
     * @param cursor
     * @return WorkoutList
     */
    @Override
    protected MyWorkoutPlan cursorToEntity(Cursor cursor) {
        MyWorkoutPlan myWorkoutPlan = new MyWorkoutPlan();

        int idIndex;
        String planNameIndex;
        int numberOfWorkoutsIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_PLAN_ID) != -1) {
                idIndex = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAN_ID));
                myWorkoutPlan.setMyWorkoutPlanId(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_PLAN_NAME) != -1) {
                planNameIndex = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLAN_NAME));
                myWorkoutPlan.setMyWorkoutPlanName(planNameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_NUMBEROFWORKOUTS) != -1) {
                numberOfWorkoutsIndex = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NUMBEROFWORKOUTS));
                myWorkoutPlan.setNumberOfWorkouts(numberOfWorkoutsIndex);
            }
        }
        return myWorkoutPlan;
    }

    /**
     * Sets the content valaue
     * @param myWorkoutPlan
     */
    private void setContentValue(MyWorkoutPlan myWorkoutPlan) {
        initialValues = new ContentValues();

        initialValues.put("myworkout_plan_name", myWorkoutPlan.getMyWorkoutPlanName());
        initialValues.put("number_of_workouts", myWorkoutPlan.getNumberOfWorkouts());
    }

    /**
     * retrieves the content values
     * @return
     */
    private ContentValues getContentValue() {
        return initialValues;
    }
}
