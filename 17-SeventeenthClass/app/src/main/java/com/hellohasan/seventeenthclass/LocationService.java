package com.hellohasan.seventeenthclass;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.Timer;
import java.util.TimerTask;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

public class LocationService extends Service {

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;

    private LocationManager locationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("Service: onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("Service: onCreate");

        locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);

        // cancel if already existed
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, 5*1000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTimer!=null)
            mTimer.cancel();
        Logger.d("Service stopped");
    }


    private class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {

//             run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        Logger.d("GPS found and getLocation() method call");
                        getLocation();

                    } else {
                        Logger.d("GPS not found");
                        Toast.makeText(getApplicationContext(), "GPS not found", Toast.LENGTH_SHORT).show();
                    }

                }

            });
        }

    }

    public void getLocation () {

        Logger.d("inside getLocation()");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Logger.d("Location permission issue");
            Toast.makeText(getApplicationContext(), "Location permission denied", Toast.LENGTH_SHORT).show();
            return;
        }

        SmartLocation.with(getApplicationContext()).location().oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                Logger.d("onLocationUpdated called");
                if(location!=null){
                    Toast.makeText(getApplicationContext(), "Lat: " + location.getLatitude() + " Long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    Logger.d("Lat: " + location.getLatitude() + " Long: " + location.getLongitude());
                } else {
                    Toast.makeText(getApplicationContext(), "Location is null", Toast.LENGTH_SHORT).show();
                    Logger.d("Location null");
                }
            }
        });
    }

}


