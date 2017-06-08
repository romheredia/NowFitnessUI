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
    List<MyWorkout> findByMyWorkoutPlanId(int id);
    List<MyWorkout> findByCode(String code);
    List<MyWorkout> findByName(String name);

    interface IMyWorkoutSchema {
        String MYWORKOUT_TABLE = "tbmyworkout";
        String COLUMN_MYWORKOUT_ID = "myworkout_id";
        String COLUMN_MYWORKOUT_NAME = "myworkout_name";
        String COLUMN_PLAN_ID = "myworkout_plan_id";
        String MYWORKOUT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + MYWORKOUT_TABLE
                + " ("
                + COLUMN_MYWORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MYWORKOUT_NAME + " TEXT NOT NULL, "
                + COLUMN_PLAN_ID + " INTEGER NOT NULL, "
                + "FOREIGN KEY ("+COLUMN_PLAN_ID+") REFERENCES tbmyworkout_plan(myworkout_plan_id)"
                + ")";

        String[] MYWORKOUT_COLUMNS = new String[] { COLUMN_MYWORKOUT_ID, COLUMN_MYWORKOUT_NAME };
    }
}
