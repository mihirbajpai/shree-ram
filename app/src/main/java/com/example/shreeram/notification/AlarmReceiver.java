package com.example.shreeram.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Call the method to show the notification
        NotificationHelper.showNotification(context, "Your Notification Title", "Your Notification Message");
    }
}
