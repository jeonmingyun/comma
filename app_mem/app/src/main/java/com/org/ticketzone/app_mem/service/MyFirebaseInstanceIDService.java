package com.org.ticketzone.app_mem.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG="FMService";
    // [START send_token]
    @Override
    public void onNewToken(String s) {
        // 설치할때 여기서 토큰을 자동으로 만들어 준다
        super.onNewToken(s);

        // 생성한 토큰을 서버로 날려서 저장하기 위해서 만든거
        sendRegistrationToServer(s);
    }

    // server로 token 전달
    private void sendRegistrationToServer(String token) {
        // OKHTTP를 이용해 웹서버로 토큰값을 날려준다.
//        if( token != null) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("Token", token)
                    .build();

            //request
            Request request = new Request.Builder()
                    .url("http://15.164.115.73:8080/mem_set_fcm_token")
                    .post(body)
                    .build();

            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, remoteMessage.getData()+"");

            Log.d(TAG, remoteMessage.getNotification().getBody() +"// "
                    +remoteMessage.getNotification().getTitle());

            sendNotification(remoteMessage.getNotification());
        }
    }

    private void sendNotification(RemoteMessage.Notification notification) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = getString(R.string.default_notification_channel_id);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(notification.getTitle())
                        .setContentText(notification.getBody())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }
}
