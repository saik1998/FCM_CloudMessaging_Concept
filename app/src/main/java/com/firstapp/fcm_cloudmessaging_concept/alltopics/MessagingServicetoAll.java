package com.firstapp.fcm_cloudmessaging_concept.alltopics;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingServicetoAll extends FirebaseMessagingService {
    NotificationHelper notificationHelper;
    Class activityName;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        notificationHelper=new NotificationHelper(this);
        notificationHelper.createChannels(message.getNotification().getTitle(),message.getNotification().getBody(),activityName);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("TAG", "onNewToken: "+token);
    }
}
