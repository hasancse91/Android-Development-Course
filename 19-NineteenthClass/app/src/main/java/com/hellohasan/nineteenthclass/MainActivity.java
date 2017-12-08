package com.hellohasan.nineteenthclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent audioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioService = new Intent(this, AudioService.class);

    }

    public void playSong(View view) {
        startService(audioService);
    }

    public void pauseSong(View view) {
        stopService(audioService);
    }
}
