package com.hellohasan.fourteenthclass.Features.ShowSubjectList;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Database.DatabaseQuery;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.fourteenthclass.Features.CreateStudent.Student;
import com.hellohasan.fourteenthclass.Features.CreateSubject.Subject;
import com.hellohasan.fourteenthclass.Features.CreateSubject.SubjectCreateCallback;
import com.hellohasan.fourteenthclass.Features.CreateSubject.SubjectCreateDialogFragment;
import com.hellohasan.fourteenthclass.R;
import com.hellohasan.fourteenthclass.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectActivity extends AppCompatActivity implements SubjectCreateCallback{

    @BindView(R.id.nameEditText)
    EditText nameEditText;
    @BindView(R.id.registrationNumTextView)
    TextView registrationNoTextView;
    @BindView(R.id.phoneNumEditText)
    EditText phoneEditText;
    @BindView(R.id.emailAddressEditText)
    EditText emailEdiText;

    @BindView(R.id.subjectRecyclerView)
    RecyclerView subjectRecyclerView;

    @BindView(R.id.noSubjectTextView)
    TextView noSubjectTextView;

    private List<Subject> subjectList = new ArrayList<>();
    private SubjectRecyclerViewAdapter subjectRecyclerViewAdapter;
    private long studentRegistrationNo;
    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();
    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        studentRegistrationNo = getIntent().getLongExtra(Config.REGISTRATION_NUMBER, -1);

        subjectRecyclerViewAdapter = new SubjectRecyclerViewAdapter(subjectList, this);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        subjectRecyclerView.setAdapter(subjectRecyclerViewAdapter);
        viewVisibilityCheck();

        databaseQueryInterface.getStudentByRegNo(studentRegistrationNo, getApplicationContext(), new DatabaseQueryCallback<Student>() {
            @Override
            public void onQuerySuccess(Student student) {
                mStudent = student;

                nameEditText.setText(student.getName());
                registrationNoTextView.setText(String.valueOf(student.getRegistrationNumber()));
                phoneEditText.setText(student.getPhoneNumber());
                emailEdiText.setText(student.getEmail());

                loadSubjectsFromDb();
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                showToast(throwable);
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showSubjectCreateDialog();
            }
        });
    }


    private void loadSubjectsFromDb() {

        databaseQueryInterface.getAllSubjectsOfAStudent(studentRegistrationNo, getApplicationContext(), new DatabaseQueryCallback<List<Subject>>() {
            @Override
            public void onQuerySuccess(List<Subject> subjects) {
                subjectList.addAll(subjects);
                subjectRecyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                showToast(throwable);
            }
        });

        viewVisibilityCheck();
    }

    private void showSubjectCreateDialog() {
        SubjectCreateDialogFragment subjectCreateDialogFragment = SubjectCreateDialogFragment.newInstance(studentRegistrationNo, "New Subject", this);
        subjectCreateDialogFragment.show(getSupportFragmentManager(), Config.CREATE_SUBJECT);
    }

    @OnClick(R.id.updateButton)
    void updateButtonClicked(){
        hideSoftKeyboard();

        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String email = emailEdiText.getText().toString();

        Student student = new Student(name, studentRegistrationNo, phone, email);

        databaseQueryInterface.updateStudentInfo(student, getApplicationContext(), new DatabaseQueryCallback<Student>() {
            @Override
            public void onQuerySuccess(Student data) {
                mStudent = data;
                Toast.makeText(getApplicationContext(), "Student info updated", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                showToast(throwable);
            }
        });

    }

    public void viewVisibilityCheck() {
        if(subjectList.isEmpty()){
            noSubjectTextView.setVisibility(View.VISIBLE);
            subjectRecyclerView.setVisibility(View.GONE);
        } else {
            noSubjectTextView.setVisibility(View.GONE);
            subjectRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSubjectCreated(Subject subject) {
        Logger.d("ListSize before subject add: " + subjectList.size());
        subjectList.add(subject);
        Logger.d("ListSize after subject add: " + subjectList.size());
        subjectRecyclerViewAdapter.notifyDataSetChanged();
        viewVisibilityCheck();
        Logger.d("Subject created. Row ID: " + subject.get_id());
    }

    private void hideSoftKeyboard(){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void showToast(Throwable throwable){
        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

}
