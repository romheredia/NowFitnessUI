package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;
import com.now.fitness.nowfitnessui.Object.MyWorkoutRoutine;

import java.util.List;

/**
 * Created by Rom on 9/06/2017.
 */

public interface IMyWorkoutRoutine {
    boolean insertMyWorkoutRoutine(MyWorkoutRoutine myWorkoutRoutine);
    boolean updateMyWorkoutRoutine(MyWorkoutRoutine myWorkoutRoutine);
    List<MyWorkoutRoutine> findAll();
    List<MyWorkoutRoutine> findByCode(String code);
    List<MyWorkoutRoutine> findByName(String name);

    interface IMyWorkoutRoutineSchema {
        String MYWORKOUTROUTINE_TABLE = "tbmyworkout";
        String COLUMN_ROUTINE_ID = "myworkout_routine_id";
        String COLUMN_MYWORKOUT_ID = "myworkout_id";
        String COLUMN_ID = "workout_id";
        String MYWORKOUTROUTINE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + MYWORKOUTROUTINE_TABLE
                + " ("
                + COLUMN_ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_MYWORKOUT_ID + " INTEGER NOT NULL, "
                + COLUMN_ID + " INTEGER NOT NULL, "
                + "FOREIGN KEY ("+COLUMN_MYWORKOUT_ID+") REFERENCES tbmyworkout(myworkout_id) "
                + "FOREIGN KEY ("+COLUMN_ID+") REFERENCES tbworkout_list(workout_id) "
                + ")";

        String[] MYWORKOUTROUTINE_COLUMNS = new String[] { COLUMN_MYWORKOUT_ID };
    }
}