package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.WorkoutList;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/30/2017.
 */


public interface IWorkoutList {
    boolean insertExercise(WorkoutList lst);
    List<WorkoutList> findAll();
    List<WorkoutList> findByCategory(String category);
    List<WorkoutList> findByName(String name);

    interface IWorkoutListSchema {
        String WORKOUTLIST_TABLE = "tbworkout_list";
        String COLUMN_ID = "workout_id";
        String COLUMN_NAME = "name";
        String COLUMN_CATEGORY = "category_id";
        String COLUMN_REPS = "repetition";
        String COLUMN_TAG = "tag";
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
                + COLUMN_TAG
                + " TEXT, "
                + " FOREIGN KEY ("+COLUMN_CATEGORY+") REFERENCES tbworkout_category(category_id) "
                + ")";

        String POPULATE_TABLE = "Insert into tbworkout_list (name,category_id,repetition,tag)\n" +
                "values\n" +
                "('Tuck Jump',107,10,'A'),\n" +
                "('Push up',103,25,'B'),\n" +
                "('Sit Ups',101,20,'C'),\n" +
                "('Jumping Jacks',107,50,'D'),\n" +
                "('Burpees',107,15,'E'),\n" +
                "('Lunges',107,20,'F'),\n" +
                "('Box Jumps',107,10,'G'),\n" +
                "('Skaters',107,40,'H'),\n" +
                "('High Knees',107,50,'I'),\n" +
                "('Russian Twists',101,40,'J'),\n" +
                "('Bridges',107,20,'K'),\n" +
                "('Crunches',101,30,'L'),\n" +
                "('Sprints',107,5,'M'),\n" +
                "('1 Min Plank',101,1,'N'),\n" +
                "('Squats',107,25,'O'),\n" +
                "('Squat Jacks',107,25,'P'),\n" +
                "('Superman',101,15,'Q'),\n" +
                "('Inchworms',107,5,'R'),\n" +
                "('Squat Jumps',107,20,'S'),\n" +
                "('Side Shuffle',107,5,'T'),\n" +
                "('Side Lunges',107,20,'U'),\n" +
                "('Lunge Hops',107,30,'V'),\n" +
                "('Tricep Dips',103,20,'W'),\n" +
                "('Plank Jacks',101,40,'X'),\n" +
                "('Up Down Push Ups',103,20,'Y'),\n" +
                "('Moutain Climbers',107,30,'Z');";

                String[] WORKOUTLIST_COLUMNS = new String[] { COLUMN_ID,
                COLUMN_NAME, COLUMN_CATEGORY, COLUMN_REPS, COLUMN_TAG};
    }
}

