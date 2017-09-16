package com.hellohasan.seventhclass.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hellohasan.seventhclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.movieNameTextView)
    TextView movieNameTextView;
    @BindView(R.id.movieRankTextView)
    TextView movieRank;
    @BindView(R.id.movieItem)
    LinearLayout linearLayout;

    public CustomViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
