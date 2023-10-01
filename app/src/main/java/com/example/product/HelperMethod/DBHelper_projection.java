package com.example.product.HelperMethod;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper_projection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myapp.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper_projection(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // as constructor calling super i.e. its calling SQLiteOpenHelper constructor to check that
        // the DATABASE_NAME is present or not if not then it'll go to the onCreate() method
        // if database is not created then it'll not go to the onCreate() method
    }


    /**
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define database schema and create table
        db.execSQL(UserContract.UserEntry.SQL_CREATE_TABLE);
    }


    /**
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserContract.UserEntry.SQL_DELETE_TABLE);
        onCreate(db);
    }


    // Define the database schema
    public static final class UserContract {
        private UserContract() {
        }

        ;

        public static abstract class UserEntry implements BaseColumns {

            public static final String TABLE_NAME = "user";
            public static final String COLUMN_NAME = "name";
            public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
            public static final String COLUMN_EMAIL = "email";
            public static final String COLUMN_PASSWORD = "password";


            /*            CREATE TABLE users (
                                _ID INTEGER PRIMARY KEY,
                                name TEXT,
                                mobile_number TEXT,
                                email TEXT,
                                password TEXT
                        );*/
            public static final String SQL_CREATE_TABLE =
                    "CREATE TABLE " + TABLE_NAME + "( " +
                            _ID + " INTEGER PRIMARY KEY, " +
                            COLUMN_NAME + " TEXT, " +
                            COLUMN_MOBILE_NUMBER + " TEXT, " +
                            COLUMN_EMAIL + " TEXT, " +
                            COLUMN_PASSWORD + " TEXT" + " )";

            public static final String SQL_DELETE_TABLE =
                    "DROP TABLE IF EXISTS " + TABLE_NAME;


        }
    }


    // Perform database operation
    public static class UserRepository {
        private DBHelper_projection dbHelper;

        public UserRepository(Context context) {
            dbHelper = new DBHelper_projection(context);
        }

        public boolean addUser(String name, String mobileNumber, String email, String password) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(UserContract.UserEntry.COLUMN_NAME, name);       //(columnName, values);
            values.put(UserContract.UserEntry.COLUMN_MOBILE_NUMBER, mobileNumber);
            values.put(UserContract.UserEntry.COLUMN_EMAIL, email);
            values.put(UserContract.UserEntry.COLUMN_PASSWORD, password);

            long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

            Log.d("userAdded", "userAdded newRowId = " + newRowId);

            if (newRowId > 0) {
                return true;
            } else {
                return false;
            }


        }


        public Cursor getUser() {
            SQLiteDatabase dbGet = dbHelper.getReadableDatabase();

            String[] projection = {
                    UserContract.UserEntry.COLUMN_NAME,
                    UserContract.UserEntry.COLUMN_MOBILE_NUMBER,
                    UserContract.UserEntry.COLUMN_EMAIL,
                    UserContract.UserEntry.COLUMN_PASSWORD
            };

            Cursor cursor = dbGet.query(
                    UserContract.UserEntry.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    null
            );
            return cursor;
        }
    }


}
