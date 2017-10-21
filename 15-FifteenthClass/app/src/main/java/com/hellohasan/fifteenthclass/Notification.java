package com.hellohasan.fifteenthclass;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class Notification {

    public static void createNotification(String title, String message, String activity, int notificationId, Context context) throws ClassNotFoundException {

        Intent intent = new Intent(context, Class.forName(activity));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSound(sound);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, noBuilder.build()); //0 = ID of notification

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("Notification created");
    }
}
