package com.hellohasan.fourteenthclass.Features.CreateSubject;

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


public class SubjectCreateDialogFragment extends DialogFragment {

    @BindView(R.id.subjectNameEditText)
    EditText subjectNameEditText;
    @BindView(R.id.subjectCodeEditText)
    EditText subjectCodeEditText;
    @BindView(R.id.subjectCreditEditText)
    EditText subjectCreditEditText;
    @BindView(R.id.createButton)
    Button createButton;
    @BindView(R.id.cancelButton)
    Button cancelButton;

    private static long studentRegistrationNo = -1;
    private Unbinder unbinder;
    private static SubjectCreateCallback subjectCreateCallback;

    public SubjectCreateDialogFragment() {
        // Required empty public constructor
    }

    public static SubjectCreateDialogFragment newInstance(long registrationNo, String title, SubjectCreateCallback callback){
        studentRegistrationNo = registrationNo;
        subjectCreateCallback = callback;
        SubjectCreateDialogFragment subjectCreateDialogFragment = new SubjectCreateDialogFragment();
        Bundle args = new Bundle();
        args.putString(Config.TITLE, title);
        subjectCreateDialogFragment.setArguments(args);

        subjectCreateDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);

        return subjectCreateDialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_subject_create, container, false);
        ButterKnife.bind(this, view);
        unbinder = ButterKnife.bind(this, view);

        String title = getArguments().getString(Config.TITLE);
        getDialog().setTitle(title);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subName = subjectNameEditText.getText().toString();
                int subCode = Integer.parseInt(subjectCodeEditText.getText().toString());
                double subCredit = Double.parseDouble(subjectCreditEditText.getText().toString());
                Subject mSubject = new Subject(subName, subCode, subCredit);

                DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();
                databaseQueryInterface.insertSubjectOfAStudent(mSubject, studentRegistrationNo, getContext(), new DatabaseQueryCallback<Subject>() {
                    @Override
                    public void onQuerySuccess(Subject subject) {
                        subjectCreateCallback.onSubjectCreated(subject);
                        Toast.makeText(getContext(), "New Subject created", Toast.LENGTH_LONG).show();
                        getDialog().dismiss();
                    }

                    @Override
                    public void onQueryFailed(Throwable throwable) {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
