package com.now.fitness.nowfitnessui.Interface;

import com.now.fitness.nowfitnessui.Object.UserProfile;

import java.util.List;

/**
 * Created by Maycor Gerilla on 5/29/2017.
 */

public interface IUserProfile {
    boolean insertUser(UserProfile user);
    boolean updateUser(UserProfile user);
    List<UserProfile> findAll();
    List<UserProfile> findById(int id);
    List<UserProfile> findByName(String name);

    interface IUserProfileSchema {
        String USER_TABLE = "tbuser_profile";
        String COLUMN_ID = "user_id";
        String COLUMN_FNAME = "firstname";
        String COLUMN_LNAME = "lastname";
        String COLUMN_GENDER = "gender";
        String COLUMN_HEIGHT = "height";
        String COLUMN_WEIGHT = "weight";
        String USER_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
                + USER_TABLE
                + " ("
                + COLUMN_ID
                + " INTEGER PRIMARY KEY, "
                + COLUMN_FNAME
                + " TEXT NOT NULL, "
                + COLUMN_LNAME
                + " TEXT NOT NULL, "
                + COLUMN_GENDER
                + " TEXT, "
                + COLUMN_HEIGHT
                + " DOUBLE, "
                + COLUMN_WEIGHT
                + " DOUBLE "
                + ")";

        String[] USER_COLUMNS = new String[] { COLUMN_ID,
                COLUMN_FNAME, COLUMN_LNAME, COLUMN_GENDER, COLUMN_HEIGHT, COLUMN_WEIGHT };
    }
}

