package com.firstapp.fcm_cloudmessaging_concept;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService1 extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title=message.getNotification().getTitle();
        String body=message.getNotification().getBody();
        NotificationChannel notificationChannel= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("ChannelID","NotificationTest", NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification.Builder builder= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this,"ChannelID")
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.ic_baseline_airplanemode_active_24)
                    .setAutoCancel(true);
        }
        NotificationManagerCompat.from(this).notify(1,builder.build());
        super.onMessageReceived(message);


        //Toast.makeText(this, ""+message.getNotification(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {

    }
}
