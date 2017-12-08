package com.hellohasan.nineteenthclass;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class AudioService extends Service{

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.addLogAdapter(new AndroidLogAdapter());

        Logger.d("Audio Playing");

        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this, R.raw.song);

            if(!mediaPlayer.isPlaying())
                mediaPlayer.start();

        } else
            Toast.makeText(getApplicationContext(), "Already playing!", Toast.LENGTH_SHORT).show();

        return START_NOT_STICKY; // if app is killed from recent list, service won't restart
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("Audio Stop");
    }
}
