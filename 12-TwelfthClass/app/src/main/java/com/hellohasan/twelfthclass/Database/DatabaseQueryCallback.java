package com.hellohasan.twelfthclass.Database;

public interface DatabaseQueryCallback<T> {
    void onSuccessQuery(T data);
    void onErrorQuery(Throwable throwable);
}
