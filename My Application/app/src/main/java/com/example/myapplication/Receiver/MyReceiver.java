package com.example.myapplication.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive: " + "收到");
        String msg = intent.getStringExtra("message");
        Log.d("TAG", "onReceive: " + msg);
    }
}