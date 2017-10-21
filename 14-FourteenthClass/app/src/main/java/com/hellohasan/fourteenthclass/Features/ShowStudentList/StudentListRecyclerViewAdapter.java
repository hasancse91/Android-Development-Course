package com.hellohasan.fourteenthclass.Features.ShowStudentList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Database.DatabaseQuery;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.fourteenthclass.Features.CreateStudent.Student;
import com.hellohasan.fourteenthclass.Features.ShowSubjectList.SubjectActivity;
import com.hellohasan.fourteenthclass.R;
import com.hellohasan.fourteenthclass.Util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class StudentListRecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<Student> studentList;
    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public StudentListRecyclerViewAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final int itemPosition = position;
        Student student = studentList.get(position);

        holder.nameTextView.setText(student.getName());
        holder.registrationNumTextVeiw.setText(String.valueOf(student.getRegistrationNumber()));
        holder.emailTextView.setText(student.getEmail());
        holder.phoneTextView.setText(student.getPhoneNumber());

        holder.crossButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure, You wanted to delete this student?");
                        alertDialogBuilder.setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        deleteStudent(itemPosition);
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SubjectActivity.class);
                intent.putExtra(Config.REGISTRATION_NUMBER, studentList.get(itemPosition).getRegistrationNumber());
                context.startActivity(intent);
            }
        });
    }

    private void deleteStudent(final int position) {
        Student student = studentList.get(position);

        databaseQueryInterface.deleteStudentByRegNo(student.getRegistrationNumber(), context, new DatabaseQueryCallback<Boolean>() {
            @Override
            public void onQuerySuccess(Boolean data) {
                studentList.remove(position);
                notifyDataSetChanged();
                ((StudentListActivity) context).viewVisibility();
                ((StudentListActivity) context).showTableRowCount();
                Toast.makeText(context, "Student deleted successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onQueryFailed(Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
