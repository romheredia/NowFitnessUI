package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.WorkoutCategory;

import java.util.List;

/**
 * This class is the interface for the WorkoutCategory data access layer
 * @author  Maycor Gerilla on 5/29/2017.
 */

public interface IWorkoutCategory {
    boolean insertWorkoutCategory();
    List<WorkoutCategory> findAll();
    List<WorkoutCategory> findByCode(String code);
    List<WorkoutCategory> findByName(String name);

    interface IWorkoutCategorySchema {
        String WORKOUTCAT_TABLE = "tbworkout_category";
        String COLUMN_ID = "category_id";
        String COLUMN_CATEGORY = "category_type";
        String WORKOUTCAT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + WORKOUTCAT_TABLE
                + " ("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY, "
                + COLUMN_CATEGORY
                + " TEXT NOT NULL "
                + ")";

        String POPULATE_TABLE = "Insert into tbworkout_category (category_id,category_type)\n" +
                "values\n" +
                "(101,'Abs'),\n" +
                "(102,'Back'),\n" +
                "(103,'Biceps'),\n" +
                "(104,'Calf'),\n" +
                "(105,'Chest'),\n" +
                "(106,'Legs'),\n" +
                "(107,'Shoulders'),\n" +
                "(108,'Triceps');";

        String[] USER_COLUMNS = new String[] { COLUMN_ID, COLUMN_CATEGORY};
    }

}

