package com.amiba.frame.androidframe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author wudl
 * @// TODO: 16/7/7
 * @see SQLiteOpenHelper
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String THIS_FILE = "DatabaseHelper";
    private boolean opened = false;
    private SQLiteDatabase mSQLiteDatabase;
    public static String CREATE_TABLE_SQL;

    public DataBaseHelper(Context context, String db_name) {
        super(context, db_name, null, DATABASE_VERSION);
        Log.d(THIS_FILE, "DatabaseHelper------");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(THIS_FILE, "onCreate------");
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(THIS_FILE, "Upgrading database from version " + oldVersion + " to " + newVersion);
//		version starting at 1
        onCreate(db);
    }

    public synchronized SQLiteDatabase open() {
        if (!opened) {
            mSQLiteDatabase = this.getWritableDatabase();
            opened = true;
        }
        return mSQLiteDatabase;
    }


    public synchronized void close() {
        this.close();
        opened = false;
    }

    public synchronized boolean isOpen() {
        return opened;
    }

}
