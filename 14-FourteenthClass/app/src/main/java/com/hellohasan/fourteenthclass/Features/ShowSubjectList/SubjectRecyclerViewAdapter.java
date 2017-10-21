package com.hellohasan.fourteenthclass.Features.ShowSubjectList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellohasan.fourteenthclass.Database.DatabaseQuery;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryCallback;
import com.hellohasan.fourteenthclass.Database.DatabaseQueryInterface;
import com.hellohasan.fourteenthclass.Features.CreateSubject.Subject;
import com.hellohasan.fourteenthclass.R;

import java.util.List;

public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectViewHolder> {

    private List<Subject> subjectList;
    private Context context;
    private DatabaseQueryInterface databaseQueryInterface = new DatabaseQuery();

    public SubjectRecyclerViewAdapter(List<Subject> subjectList, Context context) {
        this.subjectList = subjectList;
        this.context = context;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item, parent, false);
        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, int position) {
        final int itemPosition = position;
        final Subject subject = subjectList.get(position);

        holder.subjectNameTextView.setText(subject.getName());
        holder.subjectCodeTextView.setText(String.valueOf(subject.getCode()));
        holder.subjectCreditTextView.setText(String.valueOf(subject.getCredit()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseQueryInterface.deleteSubjectById(subject.get_id(), context, new DatabaseQueryCallback<Boolean>() {
                    @Override
                    public void onQuerySuccess(Boolean data) {
                        subjectList.remove(itemPosition);
                        notifyDataSetChanged();
                        if(subjectList.isEmpty())
                            ((SubjectActivity) context).viewVisibilityCheck();
                    }

                    @Override
                    public void onQueryFailed(Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
}
