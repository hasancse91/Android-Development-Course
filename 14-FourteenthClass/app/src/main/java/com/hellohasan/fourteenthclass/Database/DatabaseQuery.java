package com.hellohasan.fourteenthclass.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Features.CreateStudent.Student;
import com.hellohasan.fourteenthclass.Features.CreateSubject.Subject;
import com.hellohasan.fourteenthclass.Util.Config;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQuery implements DatabaseQueryInterface{

    @Override
    public void insertStudent(Student student, Context context, DatabaseQueryCallback<Student> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());
        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());

        try {
            long id = sqLiteDatabase.insertOrThrow(Config.TABLE_STUDENT, null, contentValues);
            if(id>0)
                callback.onQuerySuccess(student);
            else
                callback.onQueryFailed(new Exception("Student is not inserted"));
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            callback.onQueryFailed(e);
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getAllStudent(Context context, DatabaseQueryCallback<List<Student>> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
            if(cursor!=null && cursor.moveToFirst()){
                    List<Student> studentList = new ArrayList<>();
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                        long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION));
                        String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL));
                        String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE));

                        studentList.add(new Student(name, registrationNumber, phone, email));
                    }   while (cursor.moveToNext());
                    callback.onQuerySuccess(studentList);
                } else
                    callback.onQueryFailed(new Exception("Student not found"));
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            callback.onQueryFailed(e);
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getStudentByRegNo(long registrationNo, Context context, DatabaseQueryCallback<Student> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNo));
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);

            if(cursor!=null && cursor.moveToFirst()){
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION));
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL));

                callback.onQuerySuccess(new Student(name, registrationNumber, phone, email));
            } else
                callback.onQueryFailed(new Exception("Student not found"));
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            callback.onQueryFailed(e);
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }
    }

    @Override
    public void updateStudentInfo(Student student, Context context, DatabaseQueryCallback<Student> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());
        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());

        try {
            int countRow = sqLiteDatabase.update(Config.TABLE_STUDENT, contentValues,
                    Config.COLUMN_STUDENT_REGISTRATION + " = ? ",
                    new String[] {String.valueOf(student.getRegistrationNumber())});
            if(countRow>0)
                callback.onQuerySuccess(student);
            else
                callback.onQueryFailed(new Exception("Student cannot be updated"));

        } catch (SQLiteException e){
            Logger.d("SQLite Exception: " + e.getMessage());
            callback.onQueryFailed(e);
            Toast.makeText(context, "Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void deleteStudentByRegNo(long registrationNo, Context context, DatabaseQueryCallback<Boolean> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            int deletedRowCount = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                                            Config.COLUMN_STUDENT_REGISTRATION + " = ? ",
                                            new String[]{ String.valueOf(registrationNo)});
            if(deletedRowCount>0)
                callback.onQuerySuccess(true);
            else
                callback.onQueryFailed(new Exception("Failed to delete student"));
        } catch (SQLiteException e){
            callback.onQueryFailed(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getStudentCount(Context context, DatabaseQueryCallback<Long> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            long count  = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);
            callback.onQuerySuccess(count);
        } catch (Exception e){
            callback.onQueryFailed(e);
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void insertSubjectOfAStudent(Subject subject, long registrationNo, Context context, DatabaseQueryCallback<Subject> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());
        contentValues.put(Config.COLUMN_SUBJECT_OF_STUDENT_REGISTRATION, registrationNo);

        try {
            long rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_SUBJECT, null, contentValues);

            subject.set_id(rowId);

            if(rowId>0)
                callback.onQuerySuccess(subject);
            else
                callback.onQueryFailed(new Exception("Failed to insert subject"));
        } catch (SQLiteException e){
            Logger.d(e);
            callback.onQueryFailed(e);
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        } finally {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void getAllSubjectsOfAStudent(long registrationNo, Context context, DatabaseQueryCallback<List<Subject>> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<Subject> subjectList = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SUBJECT,
                    new String[] {Config.COLUMN_SUBJECT_ID, Config.COLUMN_SUBJECT_NAME, Config.COLUMN_SUBJECT_CODE, Config.COLUMN_SUBJECT_CREDIT},
                    Config.COLUMN_SUBJECT_OF_STUDENT_REGISTRATION + " = ? ",
                    new String[] {String.valueOf(registrationNo)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                do {
                    long subjectId = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_SUBJECT_ID));
                    String subjectName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SUBJECT_NAME));
                    int subjectCode = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CODE));
                    double subjectCredit = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CREDIT));

                    subjectList.add(new Subject(subjectId, subjectName, subjectCode, subjectCredit));
                } while (cursor.moveToNext());

                callback.onQuerySuccess(subjectList);
            } else
                callback.onQueryFailed(new Exception("Subject not found"));
        } catch (SQLiteException e){
            callback.onQueryFailed(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

    }

    @Override
    public void updateSubjectById(Subject subject, Context context, DatabaseQueryCallback<Subject> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());

        try {
            int countRow = sqLiteDatabase.update(Config.TABLE_SUBJECT, contentValues,
                    Config.COLUMN_SUBJECT_ID + " = ? ",
                    new String[] {String.valueOf(subject.get_id())});

            if(countRow>0)
                callback.onQuerySuccess(subject);
            else
                callback.onQueryFailed(new Exception("Failed to update this subject"));
        } catch (SQLiteException e){
            callback.onQueryFailed(e);
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void deleteSubjectById(long id, Context context, DatabaseQueryCallback<Boolean> callback) {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            int count = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                        Config.COLUMN_SUBJECT_ID + " = ?",
                        new String[] {String.valueOf(id)});

            if(count>0)
                callback.onQuerySuccess(true);
            else
                callback.onQueryFailed(new Exception("Failed to delete subject"));

        } catch (SQLiteException e){
            Logger.d(e);
            callback.onQueryFailed(e);
        } finally {
            sqLiteDatabase.close();
        }

    }

    @Override
    public void getSubjectCount(Context context, DatabaseQueryCallback<Long> callback) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        try {
            long count  = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_SUBJECT);
            callback.onQuerySuccess(count);
        } catch (Exception e){
            callback.onQueryFailed(e);
        } finally {
            sqLiteDatabase.close();
        }
    }
}
