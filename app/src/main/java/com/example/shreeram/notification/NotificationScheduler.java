package com.example.shreeram.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationScheduler {

    public static void scheduleNotification(Context context) {
        // Set the time when the notification should be shown (e.g., 10 seconds from now)
        long futureInMillis = System.currentTimeMillis() + 10000; // 10 seconds

        // Create an Intent for the AlarmReceiver
        Intent notificationIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule the notification using AlarmManager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }
}
