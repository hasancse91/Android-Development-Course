package com.hellohasan.fourteenthclass.Database;

import android.content.Context;

import com.hellohasan.fourteenthclass.Features.CreateStudent.Student;
import com.hellohasan.fourteenthclass.Features.CreateSubject.Subject;

import java.util.List;

public interface DatabaseQueryInterface {
    void insertStudent(Student student, Context context, DatabaseQueryCallback<Student> callback);
    void getAllStudent(Context context, DatabaseQueryCallback<List<Student>> callback);
    void getStudentByRegNo(long registrationNo, Context context, DatabaseQueryCallback<Student> callback);
    void updateStudentInfo(Student student, Context context, DatabaseQueryCallback<Student> callback);
    void deleteStudentByRegNo(long registrationNo, Context context, DatabaseQueryCallback<Boolean> callback);
    void getStudentCount(Context context, DatabaseQueryCallback<Long> callback);

    void insertSubjectOfAStudent(Subject subject, long registrationNo, Context context, DatabaseQueryCallback<Subject> callback);
    void getAllSubjectsOfAStudent(long registrationNo, Context context, DatabaseQueryCallback<List<Subject>> callback);
    void updateSubjectById(Subject subject, Context context, DatabaseQueryCallback<Subject> callback);
    void deleteSubjectById(long id, Context context, DatabaseQueryCallback<Boolean> callback);
    void getSubjectCount(Context context, DatabaseQueryCallback<Long> callback);
}
