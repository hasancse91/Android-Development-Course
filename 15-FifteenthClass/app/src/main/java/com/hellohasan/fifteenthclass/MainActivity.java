package com.hellohasan.fifteenthclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNotification(View view) {
        try {
            Notification.createNotification("Greetings", "Hello Users! It's a message.", "com.hellohasan.fifteenthclass.SecondActivity", 101, this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
