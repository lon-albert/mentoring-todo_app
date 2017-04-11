package com.android.ilab.todoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.android.ilab.todoapp.pojos.User;

public class UserDB extends SQLiteOpenHelper {

    private static final String TAG = UserDB.class.getSimpleName();

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private Context mContext;

    // Database Name
    private static final String DATABASE_NAME = "todo_app_user_db";

    // User table name
    private static final String TABLE_USER = "user";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_UNAME = "username";
    private static final String KEY_FNAME = "fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_UNAME + " TEXT,"
                + KEY_FNAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_CREATED_AT + " TEXT,"
                + KEY_UPDATED_AT + " TEXT"
                + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database tables created");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
    public void addUser(String email, String uname, String fname,
                       String created_at, String updated_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, uname);
        values.put(KEY_FNAME, fname);
        values.put(KEY_EMAIL, email);
        values.put(KEY_CREATED_AT, created_at);
        values.put(KEY_UPDATED_AT, updated_at);

        // Inserting Row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Getting user data from database
     */
    public User getUserDetails() {
        User user = new User();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            user.setUsername(cursor.getString(1));
            user.setFullname(cursor.getString(2));
            user.setEmail(cursor.getString(3));
            user.setCreated_at(cursor.getString(4));
            user.setUpdated_at(cursor.getString(5   ));
        }
        cursor.close();
        db.close();
        return user;
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
    }

}