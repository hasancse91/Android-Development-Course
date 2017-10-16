package com.hellohasan.thirtheenthclass.Database;

import android.content.Context;

import com.hellohasan.thirtheenthclass.Student;

import java.util.List;

public interface DatabaseQueryInterface {
    void insertStudent(Student student, Context context, DatabaseQueryCallback<Long> callback);
    void getAllStudents(Context context, DatabaseQueryCallback<List<Student>> callback);
    void getStudentCount(Context context, DatabaseQueryCallback<Long> callback);
    void getStudentByRegistrationNo(long registrationNo, Context context, DatabaseQueryCallback<Student> callback);
    void deleteStudentByRegistrationNo(long registrationNo, Context context, DatabaseQueryCallback<Boolean> callback);
}
