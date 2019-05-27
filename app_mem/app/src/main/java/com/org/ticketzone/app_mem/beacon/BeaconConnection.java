package com.org.ticketzone.app_mem.beacon;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.org.ticketzone.app_mem.activity.MainActivity;

import java.util.List;
import java.util.UUID;

public class BeaconConnection extends Activity {

    // 비콘
    private BeaconManager beaconManager;
    private BeaconRegion region;
    private boolean isConnected;

    public BeaconConnection(final Context context) {
        // 비콘
        // 비콘의 수신 범위를 갱신 받음
        beaconManager = new BeaconManager(context);

            beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener(){
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List< Beacon > list) {
                if(!list.isEmpty()){
                    Beacon nearestBeacon = list.get(0);
                    Log.e("Airport", "Nearest places: " + nearestBeacon.getRssi());

                    if(!isConnected && nearestBeacon.getRssi() > -70){
                        isConnected = true;
                        Toast.makeText(context, "버튼활성화", Toast.LENGTH_SHORT);
                        //tagBtn.setEnabled(true);
                        Log.e("test", "dddd");

                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        dialog.setTitle("알림")
                                . setMessage("비콘이 연결되었습니다.")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .create().show();
                    }else if(isConnected && nearestBeacon.getRssi() < -70){
                        Toast.makeText(context, "연결이 끊어졌습니다.", Toast.LENGTH_SHORT);
                        isConnected = true;
                        //tagBtn.setEnabled(false);
                        Log.e("test", "ddff");
                    }
                }
            }
        });

        region = new BeaconRegion("ranged region",
                                  UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935"), 40001, 15383);
        //비콘 //
    }

    @Override
    protected void onResume(){
        super.onResume();

        //블루투스 권환 승낙 및 블루투스 활성화
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause(){
        //beaconManager.stopRanging(region);
        super.onPause();
    }
}
