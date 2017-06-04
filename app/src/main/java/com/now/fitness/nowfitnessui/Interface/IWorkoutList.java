package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.WorkoutList;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/30/2017.
 */


public interface IWorkoutList {
    boolean insertExercise();
    List<WorkoutList> findAll();
    List<WorkoutList> findByCategory(String category);
    List<WorkoutList> findByName(String name);

    interface IWorkoutListSchema {
        String WORKOUTLIST_TABLE = "tbworkout_list";
        String COLUMN_ID = "workout_id";
        String COLUMN_NAME = "name";
        String COLUMN_CATEGORY = "category_id";
        String COLUMN_REPS = "repetition";
        String WORKOUTLIST_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + WORKOUTLIST_TABLE
                + " ("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME
                + " TEXT NOT NULL, "
                + COLUMN_CATEGORY
                + " INTEGER NOT NULL, "
                + COLUMN_REPS
                + " INTEGER, "
                + " FOREIGN KEY ("+COLUMN_CATEGORY+") REFERENCES tbworkout_category(category_id) "
                + ")";

        String[] USER_COLUMNS = new String[] { COLUMN_ID,
                COLUMN_NAME, COLUMN_CATEGORY, COLUMN_REPS};
    }
}

