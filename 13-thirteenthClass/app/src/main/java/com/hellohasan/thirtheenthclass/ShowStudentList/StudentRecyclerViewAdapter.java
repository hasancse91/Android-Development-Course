package com.hellohasan.thirtheenthclass.ShowStudentList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellohasan.thirtheenthclass.Database.DatabaseQuery;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.thirtheenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.thirtheenthclass.R;
import com.hellohasan.thirtheenthclass.Student;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentCustomViewHolder>{

    private List<Student> studentList;
    private Context context;
    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public StudentRecyclerViewAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    @Override
    public StudentCustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentCustomViewHolder holder, int position) {
        final int itemPosition = position;
        final Student student = studentList.get(position);

        holder.nameTextView.setText(student.getName());
        holder.registrationTextView.setText(String.valueOf(student.getRegistrationNumber()));
        holder.phoneTextView.setText(student.getPhoneNumber());
        holder.emailTextView.setText(student.getEmail());

        holder.crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d("remove button clicked");
                databaseQueryInterface.deleteStudentByRegistrationNo(student.getRegistrationNumber(), context, new DatabaseQueryCallback<Boolean>() {
                    @Override
                    public void onSuccessQuery(Boolean data) {
                        if(data) {
                            studentList.remove(itemPosition);
                            notifyDataSetChanged();
                        } else
                            Toast.makeText(context, context.getResources().getString(R.string.student_not_delete), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onErrorQuery(Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
