package com.hellohasan.thirtheenthclass.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.hellohasan.thirtheenthclass.Config;
import com.hellohasan.thirtheenthclass.Student;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQuery implements DatabaseQueryInterface {

    @Override
    public void insertStudent(Student student, Context context, DatabaseQueryCallback<Long> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());
        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());

        try {
            long id = sqLiteDatabase.insertOrThrow(Config.TABLE_STUDENT, null, contentValues);
            callback.onSuccessQuery(id);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            callback.onErrorQuery(e);
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getAllStudents(Context context, DatabaseQueryCallback<List<Student>> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        String SELECT_QUERY = String.format("SELECT * FROM %s", Config.TABLE_STUDENT);
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
            if(cursor!=null && cursor.moveToFirst()) {
                List<Student> studentList = new ArrayList<>();
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                    String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                    long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION));
                    String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE));
                    String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL));

                    studentList.add(new Student(id, name, registrationNumber, phone, email));
                } while (cursor.moveToNext());

                callback.onSuccessQuery(studentList);
            } else
                callback.onErrorQuery(new Exception("No student found in database"));

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            callback.onErrorQuery(e);
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getStudentCount(Context context, DatabaseQueryCallback<Long> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            long count  = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);
            callback.onSuccessQuery(count);
        } catch (SQLiteException e){
            callback.onErrorQuery(e);
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void getStudentByRegistrationNo(long registrationNo, Context context, DatabaseQueryCallback<Student> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = null;

        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, //table name
                    new String[] {Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_PHONE, Config.COLUMN_STUDENT_EMAIL}, //desired column names
                    Config.COLUMN_STUDENT_REGISTRATION + " = ? ", //column name of `where` clause
                    new String[] {String.valueOf(registrationNo)}, //value to be match. it must be string
                    null, null, null); //for now, we won't aggregate and order our data. so 3 fields are `null`

            if(cursor!=null && cursor.moveToFirst()){
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME)); //also can use cursor.getString(0);
                long registration = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION)); //cursor.getString(1);
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE)); //cursor.getString(2);
                String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL)); //cursor.getString(3);

                callback.onSuccessQuery(new Student(name, registration, phone, email));
            }
            else
                callback.onErrorQuery(new Exception("No student found with this registration number"));

        } catch (SQLiteException e){
            callback.onErrorQuery(e);
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

    }

    @Override
    public void deleteStudentByRegistrationNo(long registrationNo, Context context, DatabaseQueryCallback<Boolean> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            int count = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                                                Config.COLUMN_STUDENT_REGISTRATION + " = ? ",
                                                new String[] {String.valueOf(registrationNo)});
            if(count>0)
                callback.onSuccessQuery(true);
            else
                callback.onSuccessQuery(false);

        } catch (SQLiteException e){
            callback.onErrorQuery(e);

        } finally {
            sqLiteDatabase.close();
        }

    }
}
