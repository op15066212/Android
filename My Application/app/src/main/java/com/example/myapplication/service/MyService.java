package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {

    boolean flag;

    public MyService() {
    }

    @Override
    public void onCreate() {
        flag = true;
        super.onCreate();
        Log.d("TAG", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            while (flag) {
                Log.d("TAG", "onStartCommand");
                SystemClock.sleep(1000);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        flag = false;
        super.onDestroy();
        Log.d("TAG", "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}