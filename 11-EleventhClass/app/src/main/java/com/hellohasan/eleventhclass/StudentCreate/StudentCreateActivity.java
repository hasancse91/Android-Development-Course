package com.hellohasan.eleventhclass.StudentCreate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hellohasan.eleventhclass.Database.DatabaseQuery;
import com.hellohasan.eleventhclass.R;
import com.orhanobut.logger.Logger;

import java.util.List;

public class StudentCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);


    }

    public void addEntryToDatabase(View view) {
        Student student = new Student("John", 123522, "01522235524", "john@gmail.com");
        DatabaseQuery databaseQuery = new DatabaseQuery(this);
        databaseQuery.insertStudent(student);

        List<Student> studentList = databaseQuery.getAllStudent();
        Logger.d("List Length: " + studentList.size());
    }
}
