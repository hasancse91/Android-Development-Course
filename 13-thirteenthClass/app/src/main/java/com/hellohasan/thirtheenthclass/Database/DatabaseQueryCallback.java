package com.hellohasan.thirtheenthclass.Database;

public interface DatabaseQueryCallback<T> {
    void onSuccessQuery(T data);
    void onErrorQuery(Throwable throwable);
}
