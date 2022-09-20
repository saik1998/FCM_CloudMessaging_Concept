package com.firstapp.fcm_cloudmessaging_concept.alltopics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firstapp.fcm_cloudmessaging_concept.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SendNotificationEveryOneActivity extends AppCompatActivity {
    EditText title,body;
    String titleStr,bodyStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification_every_one);
        title=findViewById(R.id.title_edt);
        body=findViewById(R.id.body_edt);
    }
    public void sendAll(View view) {
        titleStr=title.getText().toString();
        bodyStr=body.getText().toString();

        FCMSender notificationsSender=new FCMSender("/topics/all",titleStr,bodyStr,getApplicationContext(),SendNotificationEveryOneActivity.this);
        notificationsSender.SendNotifications();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSubscribe();
        getNotification();
    }

    private void getSubscribe() {
        FirebaseMessaging.getInstance().subscribeToTopic("all")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                        Log.d("TAG", msg);
                        Toast.makeText(SendNotificationEveryOneActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getNotification() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();

                // Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d("TAG", msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();


            }
        });
    }


}