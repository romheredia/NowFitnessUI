package com.now.fitness.nowfitnessui.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * This class is the abstraction of the CRUD functionality which allows user to query and modify the data managed by  provider
 * @author Maycor Gerilla on 5/21/2017.
 */

public abstract class DBContentProvider  {

    public SQLiteDatabase mDb;

    public DBContentProvider(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Deletes from the table specified
     * @param tableName
     * @param selection
     * @param selectionArgs
     * @return
     */
    public int delete(String tableName, String selection,
                      String[] selectionArgs) {
        return mDb.delete(tableName, selection, selectionArgs);
    }

    /**
     * Inserts to table
     * @param tableName
     * @param values
     * @return
     */
    public long insert(String tableName, ContentValues values) {
        return mDb.insert(tableName, null, values);
    }

    protected abstract <T> T cursorToEntity(Cursor cursor);

    /**
     * Retrieves result
     * @param tableName
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return Cursor
     */
    public Cursor query(String tableName, String[] columns, String selection, String[] selectionArgs, String sortOrder) {
        return mDb.query(tableName, columns, selection, selectionArgs, null, null, sortOrder);
    }

    /**
     * Retrieves result
     * @param tableName
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @param limit
     * @return Cursor
     */
    public Cursor query(String tableName, String[] columns,
                        String selection, String[] selectionArgs, String sortOrder,
                        String limit) {

        return mDb.query(tableName, columns, selection,
                selectionArgs, null, null, sortOrder, limit);
    }

    /**
     * Retrieves result
     * @param tableName
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return Cursor
     */
    public Cursor query(String tableName, String[] columns,
                        String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy, String limit) {

        return mDb.query(tableName, columns, selection,
                selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * Updates the table
     * @param tableName
     * @param values
     * @param selection
     * @param selectionArgs
     * @return int
     */
    public int update(String tableName, ContentValues values,
                      String selection, String[] selectionArgs) {
        return mDb.update(tableName, values, selection,
                selectionArgs);
    }

    /**
     * Retrieves result
     * @param sql
     * @param selectionArgs
     * @return Cursor
     */
    public Cursor rawQuery(String sql, String[] selectionArgs) {
        return mDb.rawQuery(sql, selectionArgs);
    }
}
