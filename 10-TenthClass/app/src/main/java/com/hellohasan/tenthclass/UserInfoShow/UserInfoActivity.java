package com.hellohasan.tenthclass.UserInfoShow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.tenthclass.Network.GitHubApiService;
import com.hellohasan.tenthclass.Network.NetworkCallMock;
import com.hellohasan.tenthclass.Network.ResponseCallback;
import com.hellohasan.tenthclass.R;
import com.hellohasan.tenthclass.RepositoryListShow.RepositoryListActivity;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends AppCompatActivity implements ResponseCallback<User> {

    @BindView(R.id.userIdEditText)
    EditText userIdEditText;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.userInfoScrollView)
    ScrollView userInfoShowScrollView;
    @BindView(R.id.errorMessageTextView)
    TextView errorMessageTextView;

    @BindView(R.id.userProfilePic)
    ImageView profileImageView;
    @BindView(R.id.nameTextView)
    TextView nameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    @OnClick(R.id.getUserButton)
    public void getUserInformation() {
        progressBar.setVisibility(View.VISIBLE);
        errorMessageTextView.setVisibility(View.GONE);
        userInfoShowScrollView.setVisibility(View.GONE);
        GitHubApiService gitHubApiService = new NetworkCallMock();
        gitHubApiService.getUser(userIdEditText.getText().toString(), this);
    }

    @OnClick(R.id.seeRepoButton)
    public void getRepositoryList() {

        String userId = userIdEditText.getText().toString();

        if(userId.isEmpty())
            Toast.makeText(this, "Enter GitHub User ID", Toast.LENGTH_LONG).show();
        else {
            Intent intent = new Intent(this, RepositoryListActivity.class);
            intent.putExtra("user_id", userId);
            startActivity(intent);
        }

    }

    @Override
    public void onSuccess(User data) {
        progressBar.setVisibility(View.GONE);

        userInfoShowScrollView.setVisibility(View.VISIBLE);
        errorMessageTextView.setVisibility(View.GONE);

        Picasso.with(this)
                .load(data.getAvatarUrl())
                .placeholder(R.drawable.ic_image_black)
                .error(R.drawable.ic_broken_image_black)
                .into(profileImageView);

        nameTextView.setText(data.getName());
    }

    @Override
    public void onError(Throwable th) {
        progressBar.setVisibility(View.GONE);

        userInfoShowScrollView.setVisibility(View.GONE);
        errorMessageTextView.setVisibility(View.VISIBLE);
        errorMessageTextView.setText(th.getMessage());
    }
}
