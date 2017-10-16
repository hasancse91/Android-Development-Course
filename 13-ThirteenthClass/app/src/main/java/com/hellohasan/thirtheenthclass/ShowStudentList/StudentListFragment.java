package com.hellohasan.thirtheenthclass.ShowStudentList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hellohasan.thirtheenthclass.Database.DatabaseQuery;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.thirtheenthclass.R;
import com.hellohasan.thirtheenthclass.Student;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment implements DatabaseQueryCallback<List<Student>> {

    private Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.noStudentFoundTextView)
    TextView noStudentFoundTextView;

    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public StudentListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        databaseQueryInterface.getAllStudents(getContext(), this);

        return view;
    }

    @Override
    public void onSuccessQuery(List<Student> studentList) {
        noStudentFoundTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        StudentRecyclerViewAdapter studentRecyclerViewAdapter = new StudentRecyclerViewAdapter(studentList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(studentRecyclerViewAdapter);
    }

    @Override
    public void onErrorQuery(Throwable throwable) {
        noStudentFoundTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        noStudentFoundTextView.setText(throwable.getMessage());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
