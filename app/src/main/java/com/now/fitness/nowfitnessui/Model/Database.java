package com.now.fitness.nowfitnessui.Model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.now.fitness.nowfitnessui.DAL.MyWorkoutDAL;
import com.now.fitness.nowfitnessui.DAL.MyWorkoutPlanDAL;
import com.now.fitness.nowfitnessui.DAL.MyWorkoutRoutineDAL;
import com.now.fitness.nowfitnessui.DAL.UserProfileDAL;
import com.now.fitness.nowfitnessui.DAL.WorkoutCategoryDAL;
import com.now.fitness.nowfitnessui.DAL.WorkoutCreatedDAL;
import com.now.fitness.nowfitnessui.DAL.WorkoutListDAL;
import com.now.fitness.nowfitnessui.Interface.IMyWorkout;
import com.now.fitness.nowfitnessui.Interface.IMyWorkoutPlan;
import com.now.fitness.nowfitnessui.Interface.IUserProfile;
import com.now.fitness.nowfitnessui.Interface.IWorkoutCategory;
import com.now.fitness.nowfitnessui.Interface.IWorkoutCreated;
import com.now.fitness.nowfitnessui.Interface.IWorkoutList;
import com.now.fitness.nowfitnessui.Object.MyWorkoutPlan;

/**
 * Created by Maycor Gerilla on 5/30/2017.
 */

public class Database {
    private static final String DATABASE_NAME = "nowftiness_db";
    private DatabaseHelper mDbHelper;
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    public static UserProfileDAL mUserDAL;
    public static WorkoutCategoryDAL mWorkoutCategoryDAL;
    public static WorkoutCreatedDAL mWorkoutCreatedDAL;
    public static WorkoutListDAL mWorkoutListDAL;
    public static MyWorkoutPlanDAL mMyWorkoutPlanDAL;
    public static MyWorkoutDAL mMyWorkoutDAL;
    public static MyWorkoutRoutineDAL mMyWorkoutRoutineDAL;
    private static final String TAG = "NOWFitness:Database";

    public Database(Context context) {
        this.mContext = context;
    }

    public Database open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase mDb = mDbHelper.getWritableDatabase();

        Log.i(TAG, "Open DB");
        mUserDAL = new UserProfileDAL(mDb);
        mWorkoutCategoryDAL = new WorkoutCategoryDAL(mDb);
        mWorkoutCreatedDAL = new WorkoutCreatedDAL(mDb);
        mWorkoutListDAL = new WorkoutListDAL(mDb);
        mMyWorkoutPlanDAL = new MyWorkoutPlanDAL(mDb);
        mMyWorkoutDAL = new MyWorkoutDAL(mDb);

        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "tables created");
            db.execSQL(IUserProfile.IUserProfileSchema.USER_TABLE_CREATE);
            db.execSQL(IWorkoutCategory.IWorkoutCategorySchema.WORKOUTCAT_TABLE_CREATE);
            db.execSQL(IWorkoutCreated.IWorkoutCreatedSchema.WORKOUTCREATED_TABLE_CREATE);
            db.execSQL(IWorkoutList.IWorkoutListSchema.WORKOUTLIST_TABLE_CREATE);
            db.execSQL(IMyWorkoutPlan.IMyWorkoutPlanSchema.MYWORKOUTPLAN_TABLE_CREATE);
            db.execSQL(IMyWorkout.IMyWorkoutSchema.MYWORKOUT_TABLE_CREATE);

            Log.i(TAG, "populate table");
            db.execSQL(IWorkoutCategory.IWorkoutCategorySchema.POPULATE_TABLE);
            db.execSQL(IWorkoutList.IWorkoutListSchema.POPULATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version "
                    + oldVersion + " to "
                    + newVersion + " which destroys all old data");

            db.execSQL("DROP TABLE IF EXISTS "
                    + IUserProfile.IUserProfileSchema.USER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IWorkoutCategory.IWorkoutCategorySchema.WORKOUTCAT_TABLE_CREATE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IWorkoutCreated.IWorkoutCreatedSchema.WORKOUTCREATED_TABLE_CREATE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IWorkoutList.IWorkoutListSchema.WORKOUTLIST_TABLE_CREATE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IMyWorkoutPlan.IMyWorkoutPlanSchema.MYWORKOUTPLAN_TABLE_CREATE);
            db.execSQL("DROP TABLE IF EXISTS "
                    + IMyWorkout.IMyWorkoutSchema.MYWORKOUT_TABLE_CREATE);
            onCreate(db);
        }
    }
}
