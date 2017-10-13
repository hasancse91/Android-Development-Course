package com.hellohasan.tenthclass.RepositoryListShow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hellohasan.tenthclass.Network.GitHubApiService;
import com.hellohasan.tenthclass.Network.NetworkCall;
import com.hellohasan.tenthclass.Network.ResponseCallback;
import com.hellohasan.tenthclass.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryListActivity extends AppCompatActivity {

    @BindView(R.id.data)
    TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_list);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        String userId = getIntent().getStringExtra("user_id");

        GitHubApiService gitHubApiService = new NetworkCall();
        gitHubApiService.getRepositoryList(userId, new ResponseCallback<List<Repository>>() {
            @Override
            public void onSuccess(List<Repository> data) {
                Logger.d("Repo list size: " + data.size());

                Gson gson = new Gson();
                String repoListJson = gson.toJson(data);

                dataTextView.setText(repoListJson);
            }

            @Override
            public void onError(Throwable th) {
                Logger.d(th.getMessage());
                dataTextView.setText(th.getMessage());
            }
        });

    }

}
