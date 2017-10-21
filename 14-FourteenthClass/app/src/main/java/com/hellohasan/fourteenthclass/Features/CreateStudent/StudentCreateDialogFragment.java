package com.hellohasan.fourteenthclass.Features.CreateStudent;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Database.DatabaseQuery;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.fourteenthclass.R;
import com.hellohasan.fourteenthclass.Util.Config;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class StudentCreateDialogFragment extends DialogFragment {

    @BindView(R.id.studentNameEditText)
    EditText nameEditText;
    @BindView(R.id.registrationEditText)
    EditText registrationEditText;
    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.createButton)
    Button createButton;
    @BindView(R.id.cancelButton)
    Button cancelButton;

    private Unbinder unbinder;

    private String nameString = "";
    private long registrationNumber = -1;
    private String phoneString = "";
    private String emailString = "";

    private static StudentCreateCallback studentCreateCallback;

    public StudentCreateDialogFragment() {
        // Required empty public constructor
    }

    public static StudentCreateDialogFragment newInstance(String title, StudentCreateCallback listener){
        studentCreateCallback = listener;
        StudentCreateDialogFragment studentCreateDialogFragment = new StudentCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString(Config.TITLE, title);
        studentCreateDialogFragment.setArguments(args);

        studentCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return studentCreateDialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_student_create, container, false);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        String title = getArguments().getString(Config.TITLE);
        getDialog().setTitle(title);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameString = nameEditText.getText().toString();
                registrationNumber = Integer.parseInt(registrationEditText.getText().toString());
                phoneString = phoneEditText.getText().toString();
                emailString = emailEditText.getText().toString();

                Student mStudent = new Student(nameString, registrationNumber, phoneString, emailString);

                DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();
                databaseQueryInterface.insertStudent(mStudent, getContext(), new DatabaseQueryCallback<Student>() {
                    @Override
                    public void onQuerySuccess(Student student) {
                        studentCreateCallback.onStudentCreated(student);
                        getDialog().dismiss();
                    }

                    @Override
                    public void onQueryFailed(Throwable throwable) {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //noinspection ConstantConditions
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
