package com.atpl.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import static com.atpl.notifyme.Notification.NotificationEntry.TABLE_NAME;

public class DeletePendingIntent extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String notificationId = intent.getStringExtra("_id");
        com.atpl.notifyme.Notification.NotificationDBHelper mDbHelper = new com.atpl.notifyme.Notification.NotificationDBHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, com.atpl.notifyme.Notification.NotificationEntry._ID+" = "+notificationId,null);
        db.close();
    }
}
