package com.hellohasan.fourteenthclass.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hellohasan.fourteenthclass.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            databaseHelper = new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + Config.TABLE_STUDENT + "("
                + Config.COLUMN_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_STUDENT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_STUDENT_REGISTRATION + " INTEGER NOT NULL UNIQUE, "
                + Config.COLUMN_STUDENT_PHONE + " TEXT NOT NULL, "
                + Config.COLUMN_STUDENT_EMAIL + " TEXT NOT NULL"
                + ")";

        String CREATE_SUBJECT_TABLE = "CREATE TABLE " + Config.TABLE_SUBJECT + "("
                + Config.COLUMN_SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_SUBJECT_OF_STUDENT_REGISTRATION + " INTEGER NOT NULL, "
                + Config.COLUMN_SUBJECT_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_SUBJECT_CODE + " INTEGER NOT NULL, "
                + Config.COLUMN_SUBJECT_CREDIT + " REAL, " //nullable
                + "FOREIGN KEY (" + Config.COLUMN_SUBJECT_OF_STUDENT_REGISTRATION + ") REFERENCES " + Config.TABLE_STUDENT + "(" + Config.COLUMN_STUDENT_REGISTRATION + ") ON DELETE CASCADE "
                + "CONSTRAINT " + Config.STUDENT_SUB_CONSTRAINT + " UNIQUE (" + Config.COLUMN_SUBJECT_OF_STUDENT_REGISTRATION + "," + Config.COLUMN_SUBJECT_CODE + ")"
                + ")";

        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_SUBJECT_TABLE);

        Logger.d("DB created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SUBJECT);

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys = ON;");
    }
}
