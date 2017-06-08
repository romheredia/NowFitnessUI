package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IMyWorkout;
import com.now.fitness.nowfitnessui.Interface.IMyWorkoutRoutine;
import com.now.fitness.nowfitnessui.Model.DBAdapter;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rom on 9/06/2017.
 */

public class MyWorkoutRoutineDAL extends DBAdapter implements IMyWorkoutRoutine, IMyWorkoutRoutine.IMyWorkoutRoutineSchema {

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
        int myWorkoutId;
        int workoutId;


        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ROUTINE_ID) != -1) {
                myWorkoutRoutineId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ROUTINE_ID));
                myWorkoutRoutine.setMyWorkoutRoutineId(myWorkoutRoutineId);
            }
            if (cursor.getColumnIndex(COLUMN_MYWORKOUT_ID) != -1) {
                myWorkoutId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MYWORKOUT_ID));
                myWorkoutRoutine.setMyWorkoutId(myWorkoutId);
            }
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                workoutId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                myWorkoutRoutine.setMyWorkoutId(workoutId);
            }
        }
        return myWorkoutRoutine;
    }

    private void setContentValue(MyWorkoutRoutine myWorkoutRoutine) {
        initialValues = new ContentValues();

    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
