package com.example.product.HelperMethod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "myapp.db";  // You cna find this file inside Internal memory = data/data/com.example.product/databases/myapp.db
    public static final int DATABASE_VERSION = 1;               //

    public static final String TABLE_NAME = "user";
    public static final String ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // as constructor calling super i.e. its calling SQLiteOpenHelper constructor to check that
        // the DATABASE_NAME is present or not if not then it'll go to the onCreate() method
        // if database is not created then it'll not go to the onCreate() method

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define database schema and create table
        final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "( " +
                        ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_MOBILE_NUMBER + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT" + " )";

        db.execSQL(SQL_CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }


    public boolean addUser(String name, String mobileNumber, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);       //(columnName, values);
        values.put(COLUMN_MOBILE_NUMBER, mobileNumber);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        Log.d("userAdded", "userAdded newRowId = " + newRowId);

        return newRowId != -1;
    }

    public Cursor getAllUser() {
        SQLiteDatabase dbGet = this.getReadableDatabase();

        String queryGet = "SELECT * FROM " + TABLE_NAME;

//        Cursor cursor = dbGet.rawQuery(queryGet, null);
//        return cursor;
        return dbGet.rawQuery(queryGet, null);
    }


    public int getUserEmail(String email, String password) {
        SQLiteDatabase dbGetEmail = this.getReadableDatabase();


        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = '" + email + "'";
        Cursor cursor = dbGetEmail.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexPassword = cursor.getColumnIndex(COLUMN_PASSWORD);
            String storedPassword = cursor.getString(columnIndexPassword);

            if (password.equals(storedPassword)) {
                cursor.close();
                return 1;   // Indicates valid email and password
            } else {
                cursor.close();
                return -1;     // Indicates invalid password
            }
        }
        return 0;       // Indicates user/email ID not found

    }
}


