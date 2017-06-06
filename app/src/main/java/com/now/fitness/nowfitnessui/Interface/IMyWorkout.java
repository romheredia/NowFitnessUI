package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.MyWorkout;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;

import java.util.List;

/**
 * Created by Rom on 6/06/2017.
 */

public interface IMyWorkout {
    boolean insertMyWorkout(MyWorkout myWorkout);
    boolean updateMyWorkouts(MyWorkout myWorkout);
    List<MyWorkout> findAll();
    List<MyWorkout> findByCode(String code);
    List<MyWorkout> findByName(String name);

    interface IMyWorkoutSchema {
        String MYWORKOUTPLAN_TABLE = "tbmyworkout";
        String COLUMN_ID = "myworkout__id";
        String COLUMN_NAME = "myworkout_name";
        String COLUMN_NUMBEROFWORKOUTS = "number_of_workouts";
        String MYWORKOUTPLAN_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + MYWORKOUTPLAN_TABLE
                + " ("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME
                + " TEXT NOT NULL, "
                + COLUMN_NUMBEROFWORKOUTS
                + " INTEGER"
                + ")";

        String[] USER_COLUMNS = new String[] { COLUMN_ID, COLUMN_NAME,
                COLUMN_NUMBEROFWORKOUTS};
    }
}
