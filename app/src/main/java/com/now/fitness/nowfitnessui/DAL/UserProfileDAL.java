package com.now.fitness.nowfitnessui.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.now.fitness.nowfitnessui.Interface.IUserProfile;
import com.now.fitness.nowfitnessui.Model.DBContentProvider;
import com.now.fitness.nowfitnessui.Object.UserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the data access layer for the UserProfile Model which imp
 * @author  Maycor Gerilla on 5/29/2017.
 */

public class UserProfileDAL extends DBContentProvider implements IUserProfile, IUserProfile.IUserProfileSchema {
    private final String TAG = "NOWFitness:Database";

    private Cursor cursor;
    private ContentValues initialValues;

    protected UserProfile cursorToEntity(Cursor cursor) {
        UserProfile user = new UserProfile();

        int idIndex;
        String fnameIndex;
        String lnameIndex;
        String genderIndex;
        double heightIndex;
        double weightIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(COLUMN_ID) != -1) {
                idIndex  = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                user.setUserId(idIndex);
            }
            if (cursor.getColumnIndex(COLUMN_FNAME) != -1) {
                fnameIndex = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_FNAME));
                user.setFirstname(fnameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_LNAME) != -1) {
                lnameIndex = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_LNAME));
                user.setLastname(lnameIndex);
            }
            if (cursor.getColumnIndex(COLUMN_GENDER) != -1) {
                genderIndex = cursor.getString(cursor.getColumnIndexOrThrow(
                        COLUMN_GENDER));
                user.setGender(genderIndex);
            }
            if (cursor.getColumnIndex(COLUMN_HEIGHT) != -1) {
                heightIndex = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_HEIGHT));
                user.setHeight(heightIndex);
            }

            if (cursor.getColumnIndex(COLUMN_WEIGHT) != -1) {
                weightIndex = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT));
                user.setWeight(weightIndex);
            }

        }
        return user;
    }

    /**
     * @param mDb
     */
    public UserProfileDAL(SQLiteDatabase mDb) {
        super(mDb);
    }

    /**
     * Inserts user to tbuser)profile
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(UserProfile user) {

        // set values
        setContentValue(user);
        try {
            return super.insert(USER_TABLE, getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.d(TAG, "error: "+ex.getMessage());
            return false;
        }
    }

    /**
     * Updates the user profile
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(UserProfile user) {

        setContentValue(user);
        try {
            return super.update(USER_TABLE, getContentValue(), COLUMN_ID, null)>0;
        } catch (SQLiteConstraintException ex){
            Log.d(TAG, "error: "+ex.getMessage());
            return false;
        }
    }

    /**
     * Finds all users
     * @return Arraylist of UserProfile entity
     */
    @Override
    public List<UserProfile> findAll() {

        List<UserProfile> userList = new ArrayList<UserProfile>();
        cursor = super.query(USER_TABLE, USER_COLUMNS, null, null, COLUMN_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UserProfile user = cursorToEntity(cursor);
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return userList;
    }

    /**
     * Finds user based on the supplied user_id
     * @param id
     * @return Arraylist of UserProfile entity
     */
    @Override
    public List<UserProfile> findById(int id) {
        List<UserProfile> userList = new ArrayList<UserProfile>();
        cursor = super.query(USER_TABLE, null, COLUMN_ID + " = ?", new String[] { id+"" }, null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                UserProfile user = cursorToEntity(cursor);
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return userList;
    }

    /**
     * Finds user based on the supplied name
     * @param name
     * @return Arraylist of UserProfile entity
     */
    @Override
    public List<UserProfile> findByName(String name) {

        List<UserProfile> userList = new ArrayList<UserProfile>();
        cursor = super.query(USER_TABLE, null, COLUMN_FNAME + "= ?", new String[] { name }, null);
        Log.i(TAG, "name: "+name);

        //   cursor = super.rawQuery("select * from tbuser_profile",null);
        if (cursor != null) {
            cursor.moveToFirst();
            Log.i("CURSOR","here");
            while (!cursor.isAfterLast()) {
                Log.i("CURSOR","Not null");
                UserProfile user = cursorToEntity(cursor);
                userList.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return userList;
    }

    /**
     * Sets the content value
     * @param user
     */
    private void setContentValue(UserProfile user) {

        initialValues = new ContentValues();

        //initialValues.put("USER_ID", user.getUserId());
        initialValues.put("FIRSTNAME", user.getFirstname());
        initialValues.put("LASTNAME", user.getLastname());
        initialValues.put("GENDER", user.getGender());
        initialValues.put("WEIGHT", user.getWeight());
        initialValues.put("HEIGHT", user.getHeight());
    }

    /**
     * Retrieves the content value
     * @return ContentValues
     */
    private ContentValues getContentValue() {
        return initialValues;
    }
}

