package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IMyWorkout;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.MyWorkout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rom on 6/06/2017.
 */

public class MyWorkoutDAL extends DBContentProvider implements IMyWorkout, IMyWorkout.IMyWorkoutSchema {

    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    public MyWorkoutDAL(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public boolean insertMyWorkout(MyWorkout myWorkout) {
        setContentValue(myWorkout);
        try {
            return super.insert(MYWORKOUT_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException e) {
            Log.d(TAG, "error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateMyWorkouts(MyWorkout myWorkout) {
        return false;
    }

    @Override
    public List<MyWorkout> findAll() {
        List<MyWorkout> myWorkoutList = new ArrayList<>();
        cursor = super.query(MYWORKOUT_TABLE, MYWORKOUT_COLUMNS, null, null, COLUMN_MYWORKOUT_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                MyWorkout myWorkout = cursorToEntity(cursor);
                myWorkoutList.add(myWorkout);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return myWorkoutList;
    }

    @Override
    public List<MyWorkout> findByMyWorkoutPlanId(int id) {

        List<MyWorkout> myWorkoutList = new ArrayList<>();
//        cursor = super.query(MYWORKOUT_TABLE, null, COLUMN_PLAN_ID + "= ?", new String[] { id }, null);
        cursor = rawQuery("SELECT myworkout_id, myworkout_name from tbmyworkout where myworkout_plan_id = ?", new String[] {(String.valueOf(id))});
        Log.i(TAG, "plan id: "+id);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                MyWorkout myWorkout = cursorToEntity(cursor);
                myWorkoutList.add(myWorkout);
                cursor.moveToNext();
            }
            cursor.close();
        }

            return myWorkoutList;
    }

    @Override
    public List<MyWorkout> findByCode(String code) {
        return null;
    }

    @Override
    public List<MyWorkout> findByName(String name) {
        List<MyWorkout> myWorkoutList = new ArrayList<>();
        cursor = super.query(MYWORKOUT_TABLE, null, COLUMN_MYWORKOUT_NAME + "= ?", new String[] { name }, null);
        Log.i(TAG, "name: "+name);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                MyWorkout myWorkout = cursorToEntity(cursor);
                myWorkoutList.add(myWorkout);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return myWorkoutList;
    }

    @Override
    protected MyWorkout cursorToEntity(Cursor cursor) {
        MyWorkout myWorkout = new MyWorkout();

        int idIndex;
        String myWorkoutNameIndex;
        int myWorkoutPlanIdIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_MYWORKOUT_ID) != -1) {
                idIndex = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MYWORKOUT_ID));
                myWorkout.setMyWorkoutId(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_MYWORKOUT_NAME) != -1) {
                myWorkoutNameIndex = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MYWORKOUT_NAME));
                myWorkout.setMyWorkoutName(myWorkoutNameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_PLAN_ID) != -1) {
                myWorkoutPlanIdIndex = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAN_ID));
                myWorkout.setMyWorkoutPlanId(myWorkoutPlanIdIndex);
            }
        }
        return myWorkout;
    }

    private void setContentValue(MyWorkout myWorkout) {
        initialValues = new ContentValues();

        initialValues.put("myworkout_name", myWorkout.getMyWorkoutName());
        initialValues.put("myworkout_plan_id", myWorkout.getMyWorkoutPlanId());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
