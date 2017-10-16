package com.hellohasan.thirtheenthclass.ShowStudentList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellohasan.thirtheenthclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentCustomViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.registrationTextView)
    TextView registrationTextView;
    @BindView(R.id.phoneTextView)
    TextView phoneTextView;
    @BindView(R.id.emailTextView)
    TextView emailTextView;

    @BindView(R.id.crossButton)
    ImageView crossButton;

    public StudentCustomViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
