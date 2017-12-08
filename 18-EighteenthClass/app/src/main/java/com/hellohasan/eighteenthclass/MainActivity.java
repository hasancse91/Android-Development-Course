package com.hellohasan.eighteenthclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.firebaseToken)
    TextView firebaseTokenTextView;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusModel event) throws ClassNotFoundException {

        Logger.d("Event Fired. Tag: " + event.getEventTag());

        if (event.isTagMatched("firebase-registered")) {
            printFirebaseToken();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        printFirebaseToken();

    }

    private void printFirebaseToken(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.d("Firebase Token from MainActivity: " + refreshedToken);
        firebaseTokenTextView.setText(refreshedToken);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
