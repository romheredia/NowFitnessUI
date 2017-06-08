package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.WorkoutCategory;

import java.util.List;

/**
 * Created by Rom on 6/06/2017.
 */

public interface IMyWorkoutPlan {
    boolean insertMyWorkoutPlan(MyWorkoutPlan myWorkoutPlan);
    boolean updateMyWorkoutPlan(MyWorkoutPlan myWorkoutPlan);
    List<MyWorkoutPlan> findAll();
    List<MyWorkoutPlan> findByCode(String code);
    List<MyWorkoutPlan> findByName(String name);

    interface IMyWorkoutPlanSchema {
        String MYWORKOUTPLAN_TABLE = "tbmyworkout_plan";
        String COLUMN_PLAN_ID = "myworkout_plan_id";
        String COLUMN_PLAN_NAME = "myworkout_plan_name";
        String COLUMN_NUMBEROFWORKOUTS = "number_of_workouts";
        String MYWORKOUTPLAN_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + MYWORKOUTPLAN_TABLE
                + " ("
                + COLUMN_PLAN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PLAN_NAME
                + " TEXT NOT NULL, "
                + COLUMN_NUMBEROFWORKOUTS
                + " INTEGER"
                + ")";

        String[] MYWORKOUTPLAN_COLUMNS = new String[] { COLUMN_PLAN_ID, COLUMN_PLAN_NAME,
                COLUMN_NUMBEROFWORKOUTS};
    }
}
