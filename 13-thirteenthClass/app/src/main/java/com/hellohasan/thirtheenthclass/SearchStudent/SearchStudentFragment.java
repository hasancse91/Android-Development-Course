package com.hellohasan.thirtheenthclass.SearchStudent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hellohasan.thirtheenthclass.Database.DatabaseQuery;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.thirtheenthclass.R;
import com.hellohasan.thirtheenthclass.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchStudentFragment extends Fragment {

    private Unbinder unbinder;

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

    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public SearchStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_student, container, false);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.searchButton)
    public void searchStudentByRegNo(){
        searchErrorTextView.setVisibility(View.GONE);

        long registrationNo = Integer.parseInt(searchEditText.getText().toString());

        databaseQueryInterface.getStudentByRegistrationNo(registrationNo, getContext(), new DatabaseQueryCallback<Student>() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
