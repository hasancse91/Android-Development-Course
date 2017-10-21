package com.hellohasan.fourteenthclass.Features.ShowStudentList;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Database.DatabaseQuery;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.fourteenthclass.Features.CreateStudent.Student;
import com.hellohasan.fourteenthclass.Features.CreateStudent.StudentCreateCallback;
import com.hellohasan.fourteenthclass.Features.CreateStudent.StudentCreateDialogFragment;
import com.hellohasan.fourteenthclass.R;
import com.hellohasan.fourteenthclass.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentListActivity extends AppCompatActivity implements StudentCreateCallback {

    private List<Student> studentList = new ArrayList<>();

    @BindView(R.id.studentCountTextView)
    TextView studentCountTextView;
    @BindView(R.id.subjectCountTextView)
    TextView subjectCountTextView;
    @BindView(R.id.emptyStudentListTextView)
    TextView studentListEmptyTextView;
    @BindView(R.id.studentRecyclerView)
    RecyclerView recyclerView;
    private StudentListRecyclerViewAdapter studentListRecyclerViewAdapter;
    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        studentListRecyclerViewAdapter = new StudentListRecyclerViewAdapter(this, studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(studentListRecyclerViewAdapter);

        viewVisibility();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStudentCreateDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseQueryInterface.getAllStudent(this, new DatabaseQueryCallback<List<Student>>() {
            @Override
            public void onQuerySuccess(List<Student> students) {
                studentList.clear();
                studentList.addAll(students);
                studentListRecyclerViewAdapter.notifyDataSetChanged();
                viewVisibility();
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                viewVisibility();
                Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        showTableRowCount();
    }

    public void viewVisibility() {
        if(studentList.isEmpty())
            studentListEmptyTextView.setVisibility(View.VISIBLE);
        else
            studentListEmptyTextView.setVisibility(View.GONE);
    }

    private void openStudentCreateDialog() {
        StudentCreateDialogFragment studentCreateDialogFragment = StudentCreateDialogFragment.newInstance("Create Student", this);
        studentCreateDialogFragment.show(getSupportFragmentManager(), Config.CREATE_STUDENT);
    }

    public void showTableRowCount() {
        databaseQueryInterface.getStudentCount(this, new DatabaseQueryCallback<Long>() {
            @Override
            public void onQuerySuccess(Long data) {
                studentCountTextView.setText(String.valueOf(data));
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                studentCountTextView.setText(throwable.getMessage());
            }
        });

        databaseQueryInterface.getSubjectCount(this, new DatabaseQueryCallback<Long>() {
            @Override
            public void onQuerySuccess(Long data) {
                subjectCountTextView.setText(String.valueOf(data));
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                subjectCountTextView.setText(throwable.getMessage());
            }
        });
    }

    @Override
    public void onStudentCreated(Student student) {
        studentList.add(student);
        studentListRecyclerViewAdapter.notifyDataSetChanged();
        viewVisibility();
        showTableRowCount();
        Logger.d(student.getName());
    }
}
