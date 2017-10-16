package com.hellohasan.twelfthclass.StudentCreate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.twelfthclass.Database.DatabaseQuery;
import com.hellohasan.twelfthclass.Database.DatabaseQueryCallback;
import com.hellohasan.twelfthclass.Database.DatabaseQueryInterface;
import com.hellohasan.twelfthclass.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentCreateActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText nameEditText;
    @BindView(R.id.registrationEditText)
    EditText registrationEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.studentCreateErrorMessageTextView)
    TextView studentCreateErrorMessage;

    @BindView(R.id.studentCountTextView)
    TextView studentCountTextView;

    @BindView(R.id.searchEditText)
    EditText searchEditText;
    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.registrationTextView)
    TextView registrationTextView;
    @BindView(R.id.phoneTextView)
    TextView phoneTextView;
    @BindView(R.id.emailTextView)
    TextView emailTextView;
    @BindView(R.id.searchErrorMessageTextView)
    TextView searchErrorTextView;

    DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create);
        ButterKnife.bind(this);


        //print number of students in db
        databaseQueryInterface.getStudentCount(this, new DatabaseQueryCallback<Long>() {
            @Override
            public void onSuccessQuery(Long studentCount) {
                studentCountTextView.setText(String.valueOf(studentCount));
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Student count error: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @OnClick(R.id.addStudentButton)
    public void createStudent(View view) {
        hideKeyboard(view);
        studentCreateErrorMessage.setVisibility(View.GONE);
        searchErrorTextView.setVisibility(View.GONE);

        String name = nameEditText.getText().toString();
        long registrationNo = Integer.parseInt(registrationEditText.getText().toString());
        String phone = phoneEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Student student = new Student(name, registrationNo, phone, email);

        //insert student to database
        databaseQueryInterface.insertStudent(student, this, new DatabaseQueryCallback<Long>() {
            @Override
            public void onSuccessQuery(Long studentDatabaseId) {
                Toast.makeText(getApplicationContext(), "Success! New student ID: " + studentDatabaseId, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                studentCreateErrorMessage.setVisibility(View.VISIBLE);
                studentCreateErrorMessage.setText(throwable.getMessage());
            }
        });

        //count the number of student in `student` table
        databaseQueryInterface.getStudentCount(this, new DatabaseQueryCallback<Long>() {
            @Override
            public void onSuccessQuery(Long studentCount) {
                studentCountTextView.setText(String.valueOf(studentCount)); //print the counter into TextView
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Error in getStudentCount: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //get all students from student table
        databaseQueryInterface.getAllStudents(this, new DatabaseQueryCallback<List<Student>>() {
            @Override
            public void onSuccessQuery(List<Student> studentList) {
                Logger.d("Number of Student: " + studentList.size()); //get student count
                //place a debugging breakpoint at this line or above line and check the studentList in debug monitor
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Error in getAllStudent: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @OnClick(R.id.searchButton)
    public void searchStudentByRegNo(View view){
        hideKeyboard(view);
        studentCreateErrorMessage.setVisibility(View.GONE);
        searchErrorTextView.setVisibility(View.GONE);
        long registrationNo = Integer.parseInt(searchEditText.getText().toString());
        databaseQueryInterface.getStudentByRegistrationNo(registrationNo, this, new DatabaseQueryCallback<Student>() {
            @Override
            public void onSuccessQuery(Student student) {
                nameTextView.setText(student.getName());
                registrationTextView.setText(String.valueOf(student.getRegistrationNumber()));
                phoneTextView.setText(student.getPhoneNumber());
                emailTextView.setText(student.getEmail());
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                searchErrorTextView.setVisibility(View.VISIBLE);
                searchErrorTextView.setText(throwable.getMessage());

                nameTextView.setText("");
                registrationTextView.setText("");
                phoneTextView.setText("");
                emailTextView.setText("");
            }
        });
    }

    private void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
