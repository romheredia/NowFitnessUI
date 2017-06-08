package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.WorkoutCreated;

import java.util.List;

/**
 * This class is the interface for the WorkoutCreated data access layer
 * @author Maycor Gerilla on 5/29/2017.
 */


public interface IWorkoutCreated {
    boolean insertWorkout(WorkoutCreated work);
    boolean updateWorkout(WorkoutCreated work);
    boolean deleteWorkout(int id);
    List<WorkoutCreated> findAll();
    List<WorkoutCreated> findByUserId(int id);
    List<WorkoutCreated> findByWorkoutId(int id);
    List<WorkoutCreated> findByProgramName(String name);

    interface IWorkoutCreatedSchema {
        String WORKOUTCREATED_TABLE = "tbworkout_saved";
        String COLUMN_USER_ID = "user_id";
        String COLUMN_WORKOUT_ID = "workout_id";
        String COLUMN_NAME = "workout_name";
        String COLUMN_STATUS = "status";
        String WORKOUTCREATED_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + WORKOUTCREATED_TABLE
                + " ("
                + COLUMN_USER_ID
                + " INTEGER NOT NULL, "
                + COLUMN_WORKOUT_ID
                + " INTEGER NOT NULL, "
                + COLUMN_NAME
                + " TEXT NOT NULL, "
                + COLUMN_STATUS
                + " TEXT NOT NULL, "
                + " FOREIGN KEY ("+COLUMN_USER_ID+") REFERENCES tbuser_profile(user_id) "
                + " FOREIGN KEY ("+COLUMN_WORKOUT_ID+") REFERENCES tbworkout_list(workout_id) "
                + ")";

        String[] USER_COLUMNS = new String[] { COLUMN_USER_ID, COLUMN_WORKOUT_ID,
                COLUMN_NAME, COLUMN_STATUS};
    }
}

