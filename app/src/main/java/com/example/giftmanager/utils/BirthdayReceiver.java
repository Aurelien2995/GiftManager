package com.example.giftmanager.utils;

import android.Manifest;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.giftmanager.R;


public class BirthdayReceiver extends BroadcastReceiver {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        Notification notification = new NotificationCompat.Builder(context, NotificationUtils.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        NotificationManagerCompat.from(context)
                .notify((int) System.currentTimeMillis(), notification);
    }

}
