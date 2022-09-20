package com.firstapp.fcm_cloudmessaging_concept.alltopics;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.firstapp.fcm_cloudmessaging_concept.R;

public class NotificationHelper extends ContextWrapper {

    Class name;
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            createChannels("","",name);
        }
        else
        {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannels(String title, String body, Class activityName) {
        String channelID="MyChannelID";
        String channelName="Firebase Notification Channel";

        NotificationChannel notificationChannel=new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);

        Intent intent=new Intent(this,activityName);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder=new Notification.Builder(this,channelID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_baseline_airplanemode_active_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,builder.build());

    }


}
