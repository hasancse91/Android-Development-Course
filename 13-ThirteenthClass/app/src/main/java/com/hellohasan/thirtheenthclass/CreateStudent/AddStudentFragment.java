package com.hellohasan.thirtheenthclass.CreateStudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.thirtheenthclass.Database.DatabaseQuery;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.thirtheenthclass.R;
import com.hellohasan.thirtheenthclass.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddStudentFragment extends Fragment {

    private Unbinder unbinder;

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

    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public AddStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.addStudentButton)
    public void createStudent() {
        studentCreateErrorMessage.setVisibility(View.GONE);

        String name = nameEditText.getText().toString();
        long registrationNo = Integer.parseInt(registrationEditText.getText().toString());
        String phone = phoneEditText.getText().toString();
        String email = emailEditText.getText().toString();

        Student student = new Student(name, registrationNo, phone, email);

        //insert student to database
        databaseQueryInterface.insertStudent(student, getContext(), new DatabaseQueryCallback<Long>() {
            @Override
            public void onSuccessQuery(Long studentDatabaseId) {
                studentCreateErrorMessage.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Success! New student ID: " + studentDatabaseId, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                studentCreateErrorMessage.setVisibility(View.VISIBLE);
                studentCreateErrorMessage.setText(throwable.getMessage());
            }
        });

        //get student count and show on fragment
        databaseQueryInterface.getStudentCount(getContext(), new DatabaseQueryCallback<Long>() {
            @Override
            public void onSuccessQuery(Long data) {
                studentCountTextView.setText(String.valueOf(data));
            }

            @Override
            public void onErrorQuery(Throwable throwable) {
                studentCountTextView.setText(throwable.getMessage());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
