package com.hellohasan.eighteenthclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null){
            Logger.d(bundle.getString("image"));
        }

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.d("onCreate() Token: " + refreshedToken);
    }
}
