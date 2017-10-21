package com.hellohasan.fourteenthclass.Database;

public interface DatabaseQueryCallback<T> {
    void onQuerySuccess(T data);
    void onQueryFailed(Throwable throwable);
}
