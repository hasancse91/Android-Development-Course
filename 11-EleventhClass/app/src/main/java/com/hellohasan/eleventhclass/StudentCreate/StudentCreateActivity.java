package com.hellohasan.eleventhclass.StudentCreate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hellohasan.eleventhclass.Database.DatabaseQuery;
import com.hellohasan.eleventhclass.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentCreateActivity extends AppCompatActivity {

    @BindView(R.id.studentCountTextView)
    TextView studentCountTextView;

    private DatabaseQuery databaseQuery = new DatabaseQuery(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);
        ButterKnife.bind(this);

        //print number of students in db
        studentCountTextView.setText(String.valueOf(databaseQuery.getStudentCount()));
    }

    public void addEntryToDatabase(View view) {
        //every time we'll create student object with same data. and insert to database
        Student student = new Student("John", 123522, "01522235524", "john@gmail.com");

        databaseQuery.insertStudent(student); //insert student object (actually data) into database

        //you'll find all students of db in studentList
        List<Student> studentList = databaseQuery.getAllStudent();
        Logger.d("Number of Student: " + studentList.size()); //get student count
        //place a debugging breakpoint at this line or above line and check the studentList in debug monitor

        //another way to count students in db
        long count = databaseQuery.getStudentCount();
        studentCountTextView.setText(String.valueOf(count)); //print the counter into TextView
    }
}
