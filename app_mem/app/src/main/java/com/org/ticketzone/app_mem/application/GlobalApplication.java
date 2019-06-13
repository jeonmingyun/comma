package com.org.ticketzone.app_mem.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.activity.MainActivity;

import java.util.List;
import java.util.UUID;

public class GlobalApplication extends Application {
    private static GlobalApplication instance = null;

    // 비콘
    private BeaconManager beaconManager;
    private BeaconManager beaconManager2;
    //
    public static GlobalApplication getGlobalApplicationContext() {
        if (instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    protected static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());

        // 비콘
        beaconManager = new BeaconManager(getGlobalApplicationContext());
        beaconManager2 = new BeaconManager(getGlobalApplicationContext());

        // Application 설치가 끝나면 Beacon Monitoring Service를 시작한다.
        // Application을 종료하더라도 Service가 계속 실행된다.
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady () {
                beaconManager.startMonitoring(new BeaconRegion(
                        "monitored region",
                        UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935"),
                        40001, 15400));

            }
        });
        beaconManager2.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager2.startMonitoring(new BeaconRegion(
                        "monitored region",
                        UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935"),
                        40001,15383
                ));
            }
        });
        // Android 단말이 Beacon 의 송신 범위에 들어가거나, 나왔을 때 체크
        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion region, List<Beacon> list) {
                showNotification("들어옴", "비콘 연결됨" + list.get(0).getRssi());
            }
            @Override
            public void onExitedRegion(BeaconRegion region) {
                showNotification("나감", "비콘 연결끊김");
            }
        });

    }

    // 비콘
    public void showNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(message)
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