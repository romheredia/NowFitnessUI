package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IWorkoutList;
import com.now.fitness.nowfitnessui.Model.DBAdapter;
import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.WorkoutList;

import java.util.ArrayList;
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
    protected WorkoutList cursorToEntity(Cursor cursor) {
        WorkoutList wrk = new WorkoutList();

        int idIndex;
        String categoryCodeIndex;
        String exerciseNameIndex;
        int repetitionsIndex;
        String tagIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                idIndex  = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                wrk.setWorkoutId(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_NAME) != -1) {
                exerciseNameIndex = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_NAME));
                wrk.setExerciseName(exerciseNameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_CATEGORY) != -1) {
                categoryCodeIndex = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_CATEGORY));
                wrk.setCategoryCode(categoryCodeIndex);
            }
            if (cursor.getColumnIndex(COLUMN_REPS) != -1) {
                repetitionsIndex = cursor.getInt(cursor.getColumnIndexOrThrow(
                        COLUMN_REPS));
                wrk.setRepetitions(repetitionsIndex);
            }
            if (cursor.getColumnIndex(COLUMN_TAG) != -1) {
                tagIndex = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAG));
                wrk.setTag(tagIndex);
            }
        }

        return wrk;
    }

    @Override
    public boolean insertExercise(WorkoutList lst) {

        setContentValue(lst);
        try {
            return super.insert(WORKOUTLIST_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<WorkoutList> findAll() {
        List<WorkoutList> workoutList = new ArrayList<>();
        cursor = super.query(WORKOUTLIST_TABLE, WORKOUTLIST_COLUMNS, null, null, COLUMN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                WorkoutList workoutListItem = cursorToEntity(cursor);
                workoutList.add(workoutListItem);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return workoutList;
    }

    @Override
    public List<WorkoutList> findByCategory(String category) {
        return null;
    }

    @Override
    public List<WorkoutList> findByName(String name) {

        List<WorkoutList> workoutList = new ArrayList<WorkoutList>();
        //loop through the name
        for (int i = 0;i < name.length(); i++){
            char str = Character.toUpperCase(name.charAt(i));
            cursor = super.query(WORKOUTLIST_TABLE, null, COLUMN_TAG + " = ?", new String[]{str+""}, null);
            Log.i(TAG, "letter: " + str);

            //   cursor = super.rawQuery("select * from tbuser_profile",null);
            if (cursor != null) {
                cursor.moveToFirst();
                Log.i("CURSOR", "here");
                while (!cursor.isAfterLast()) {
                    Log.i("CURSOR", "Not null");
                    WorkoutList wrk = cursorToEntity(cursor);
                    workoutList.add(wrk);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }

        return workoutList;
    }

    private void setContentValue(WorkoutList lst) {

        initialValues = new ContentValues();

        //read form text
        initialValues.put("WORKOUT_ID", lst.getWorkoutId());
        initialValues.put("NAME", lst.getExerciseName());
        initialValues.put("CATEGORY_ID", lst.getCategoryCode());
        initialValues.put("REPETITION", lst.getRepetitions());
        initialValues.put("TAG", lst.getTag());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }

}
