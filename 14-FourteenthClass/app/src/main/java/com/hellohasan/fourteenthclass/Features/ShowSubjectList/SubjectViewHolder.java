package com.hellohasan.fourteenthclass.Features.ShowSubjectList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellohasan.fourteenthclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SubjectViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.subjectName)
    TextView subjectNameTextView;
    @BindView(R.id.subjectCode)
    TextView subjectCodeTextView;
    @BindView(R.id.creditTextView)
    TextView subjectCreditTextView;
    @BindView(R.id.deleteButton)
    ImageView deleteButton;

    public SubjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
