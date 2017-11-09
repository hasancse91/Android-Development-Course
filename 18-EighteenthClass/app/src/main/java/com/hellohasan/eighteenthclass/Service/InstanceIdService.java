package com.hellohasan.eighteenthclass.Service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class InstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.d("Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
    }
}
