package com.hellohasan.tenthclass.Network;

import com.hellohasan.tenthclass.RepositoryListShow.Repository;
import com.hellohasan.tenthclass.UserInfoShow.User;

import java.util.List;

public class NetworkCallMock implements GitHubApiService{
    @Override
    public void getUser(String userId, ResponseCallback<User> callback) {
        User user = new User();
        user.setName("Hasan");
        user.setAvatarUrl("https://avatars0.githubusercontent.com/u/3769029?s=400&u=2835d412e21c189e40a2584f76bed1f91b21bea7&v=4");

        callback.onSuccess(user);
    }

    @Override
    public void getRepositoryList(String userId, ResponseCallback<List<Repository>> callback) {

    }
}
