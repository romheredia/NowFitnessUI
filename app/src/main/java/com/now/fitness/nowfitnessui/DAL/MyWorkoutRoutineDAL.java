package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IMyWorkout;
import com.now.fitness.nowfitnessui.Interface.IMyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;

import java.util.ArrayList;
import java.util.List;

import static com.now.fitness.nowfitnessui.Interface.IWorkoutList.IWorkoutListSchema.COLUMN_NAME;

/**
 * Created by Rom on 9/06/2017.
 */

public class MyWorkoutRoutineDAL extends DBContentProvider implements IMyWorkoutRoutine, IMyWorkoutRoutine.IMyWorkoutRoutineSchema {

    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    public MyWorkoutRoutineDAL(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public boolean insertMyWorkoutRoutine(MyWorkoutRoutine myWorkoutRoutine) {
        setContentValue(myWorkoutRoutine);
        try {
            return super.insert(MYWORKOUTROUTINE_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException e) {
            Log.d(TAG, "error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateMyWorkoutRoutine(MyWorkoutRoutine myWorkoutRoutine) {
        return false;
    }

    @Override
    public List<MyWorkoutRoutine> findAll() {
        List<MyWorkoutRoutine> myWorkoutRoutineList = new ArrayList<>();
        cursor = super.query(MYWORKOUTROUTINE_TABLE, MYWORKOUTROUTINE_COLUMNS, null, null, COLUMN_ROUTINE_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                MyWorkoutRoutine myWorkoutRoutineItem = cursorToEntity(cursor);
                myWorkoutRoutineList.add(myWorkoutRoutineItem);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return myWorkoutRoutineList;
    }

    @Override
    public List<MyWorkoutRoutine> findMyWorkoutRoutine(int planId, int myWorkoutId) {
        List<MyWorkoutRoutine> myWorkoutRoutineList = new ArrayList<>();
//        cursor = super.query(MYWORKOUT_TABLE, null, COLUMN_PLAN_ID + "= ?", new String[] { id }, null);
        cursor = rawQuery("SELECT myworkout_routine_id, myworkout_plan_id, myworkout_id, tbmyworkout_routine.workout_id, tbworkout_list.name "
                + "FROM tbmyworkout_routine INNER JOIN tbworkout_list ON tbmyworkout_routine.workout_id = tbworkout_list.workout_id "
                + "WHERE myworkout_plan_id = ? and myworkout_id = ?", new String[] {String.valueOf(planId), String.valueOf(myWorkoutId)});
        Log.i(TAG, "plan id: "+planId);
        Log.i(TAG, "my workout id: "+myWorkoutId);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                MyWorkoutRoutine myWorkoutRoutineItem = cursorToEntity(cursor);
                myWorkoutRoutineList.add(myWorkoutRoutineItem);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return myWorkoutRoutineList;
    }

    @Override
    public List<MyWorkoutRoutine> findByMyWorkoutRoutineId(int routineId) {
        List<MyWorkoutRoutine> myWorkoutRoutineList = new ArrayList<>();
//        cursor = super.query(MYWORKOUT_TABLE, null, COLUMN_PLAN_ID + "= ?", new String[] { id }, null);
        cursor = rawQuery("SELECT myworkout_routine_id, myworkout_plan_id, myworkout_id from tbmyworkout_routine where myworkout_routine_id = ?", new String[] {(String.valueOf(routineId))});
        Log.i(TAG, "plan id: "+routineId);

        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                MyWorkoutRoutine myWorkoutRoutineItem = cursorToEntity(cursor);
                myWorkoutRoutineList.add(myWorkoutRoutineItem);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return myWorkoutRoutineList;
    }


    @Override
    public List<MyWorkoutRoutine> findByCode(String code) {
        return null;
    }

    @Override
    public List<MyWorkoutRoutine> findByName(String name) {
        return null;
    }

    @Override
    protected MyWorkoutRoutine cursorToEntity(Cursor cursor) {
        MyWorkoutRoutine myWorkoutRoutine = new MyWorkoutRoutine();

        int myWorkoutRoutineId;
        int myWorkoutPlanId;
        int myWorkoutId;
        int workoutId;
        String workoutName;


        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ROUTINE_ID) != -1) {
                myWorkoutRoutineId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ROUTINE_ID));
                myWorkoutRoutine.setMyWorkoutRoutineId(myWorkoutRoutineId);
            }
            if (cursor.getColumnIndex(COLUMN_PLAN_ID) != -1) {
                myWorkoutPlanId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAN_ID));
                myWorkoutRoutine.setMyWorkoutPlanId(myWorkoutPlanId);
            }
            if (cursor.getColumnIndex(COLUMN_MYWORKOUT_ID) != -1) {
                myWorkoutId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MYWORKOUT_ID));
                myWorkoutRoutine.setMyWorkoutId(myWorkoutId);
            }
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                workoutId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                myWorkoutRoutine.setWorkoutId(workoutId);
            }
            if (cursor.getColumnIndex(COLUMN_NAME) != -1) {
                workoutName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                myWorkoutRoutine.setWorkoutName(workoutName);
            }

        }
        return myWorkoutRoutine;
    }

    private void setContentValue(MyWorkoutRoutine myWorkoutRoutine) {
        initialValues = new ContentValues();
        initialValues.put("myworkout_plan_id", myWorkoutRoutine.getMyWorkoutPlanId());
        initialValues.put("myworkout_id", myWorkoutRoutine.getMyWorkoutId());
        initialValues.put("workout_id", myWorkoutRoutine.getWorkoutId());
        initialValues.put("name", myWorkoutRoutine.getWorkoutName());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
