package com.hellohasan.tenthclass.Network;

import com.hellohasan.tenthclass.RepositoryListShow.Repository;
import com.hellohasan.tenthclass.UserInfoShow.User;

import java.util.List;

public interface GitHubApiService {
    void getUser(String userId, ResponseCallback<User> callback);
    void getRepositoryList(String userId, ResponseCallback<List<Repository>> callback);
}
