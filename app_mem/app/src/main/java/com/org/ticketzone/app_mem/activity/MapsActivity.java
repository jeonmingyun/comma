package com.org.ticketzone.app_mem.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.org.ticketzone.app_mem.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient = null;
    private Marker currentMarker = null;
    private GoogleMap mMap = null;
    private Geocoder geocoder;
    private Button addressButton;
    private EditText editText;

    private static final String TAG = "googlemap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000; //1초
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500; //0.5초

    private AppCompatActivity mActivity;
    boolean askPermissionOnceAgain = false;
    boolean mRequestingLocationUpdates = false;
    Location mCurrentLocation;
    boolean mMoveMapByUser = true;
    boolean mMoveMapByAPI = true;
    LatLng currentPosition;

    LocationRequest locationRequest = new LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
            .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_maps);

        Log.d(TAG, "onCreate");
        mActivity = this;

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        editText = findViewById(R.id.editText);
        addressButton = findViewById(R.id.addressButton);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onResume(){
        super.onResume();

        if(mGoogleApiClient.isConnected()){
            Log.d(TAG, "onResume : call startLocationUpdates");
            if(!mRequestingLocationUpdates) startLocationUpdates();
        }

        // 앱 정보에서 퍼미션을 허가했는지를 다시 검사해봐야 한다.
        if(askPermissionOnceAgain){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                askPermissionOnceAgain = false;
                checkPermissions();
            }
        }
    }

    private void startLocationUpdates() {
        if(!checkLocationServicesStatus()){
            Log.d(TAG,"startLocationUpdates : call showDialogForLocationsServiceSetting");
            showDialogForLocationServiceSetting();
        }else{
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                Log.d(TAG,"startLocationupdates : 퍼미션 안가지고 있음");
                return;
            }
            Log.d(TAG,"startLocationUpdates : call FusedLocationApi.requestLocationUpdates");
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, (com.google.android.gms.location.LocationListener) this);
            mRequestingLocationUpdates = true;

            mMap.setMyLocationEnabled(true);
        }
    }

    private void stopLocationUpdates(){
        Log.d(TAG,"stopLocationUpdates : LocationServices.FusedLocationApi.removeLocationUpdates");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        mRequestingLocationUpdates = false;
    }

    private boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        Log.d(TAG, "onMapReady");
        mMap = googleMap;
        geocoder = new Geocoder(this);

        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에 지도의 초기위치를 서울로 이동
        setDefaultLocation();

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Log.d(TAG, "onMyLocationButtonClick : 위치에 따른 카메라 이동 활성화");
                mMoveMapByAPI = true;
                return true;
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.d(TAG, "onMapClick");
            }
        });
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {

            @Override
            public void onCameraMoveStarted(int i) {
                if (mMoveMapByUser == true && mRequestingLocationUpdates) {
                    Log.d(TAG, "onCameraMove : 위치에 따른 카메라 이동 비활성화");
                    mMoveMapByAPI = false;
                }
                mMoveMapByUser = true;
            }
        });
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

            }
        });

        // 맵 터치 이벤트 구현//
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//                MarkerOptions mOptions = new MarkerOptions();
//                //마커 타이틀
//                mOptions.title("마커 좌표");
//                Double latitude = point.latitude; //위도
//                Double longitude = point.longitude; //경도
//                //마커의 스니펫(간단한 텍스트) 설정
//                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
//                // LatLng: 위도 경도 쌍을 나타냄
//                mOptions.position(new LatLng(latitude, longitude));
//                //마커(핀) 추가
//                googleMap.addMarker(mOptions);
//            }
//        });

        //버튼 이벤트


        addressButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String str=editText.getText().toString();
                if(str.equals("")){
                    Toast.makeText(MapsActivity.this, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
                List<Address> addressList = null;
                try{
                    //editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str,
                            10); //str=주소, 10=최대 검색 결과 개수
                }catch (IOException e){
                    e.printStackTrace();
                }

                //System.out.println(addressList.get(0).toString());

                //콤마를 기준으로 split
                if(addressList != null) {
                    String[] splitStr = addressList.get(0).toString().split(",");
                    String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() - 2); //주소
                    System.out.println(address);

                    String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); //위도
                    String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); //경도
                    System.out.println(latitude);
                    System.out.println(longitude);

                    //좌표(위도, 경도) 생성
                    LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    // 마커 생성
                    MarkerOptions mOptions2 = new MarkerOptions();
                    mOptions2.title("search result");
                    mOptions2.snippet(address);
                    mOptions2.position(point);
                    // 마커 추가
                    mMap.addMarker(mOptions2);
                    //해당 좌표로 화면 줌
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
                }
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

    private void setDefaultLocation() {
        mMoveMapByUser = false;

        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인하세요";

        if(currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentMarker = mMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mMap.moveCamera(cameraUpdate);
    }

    @Override
    public void onLocationChanged(Location location) {

        currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

        Log.d(TAG,"onLocationChanged");

        String markerTitle = getCurrentAddress(currentPosition);
        String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                + "경도:" + String.valueOf(location.getLongitude());

        //현재 위치에 마커 생성하고 이동
        setCurrentLocation(location, markerTitle, markerSnippet);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mCurrentLocation = location;
    }

    private void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {
        mMoveMapByUser = false;

        if(currentMarker != null) currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);

        currentMarker = mMap.addMarker(markerOptions);

        if(mMoveMapByAPI){
            Log.d(TAG,"setCurrentLocation : mGoogleMap moveCamera "
                    + location.getLatitude() + " " + location.getLongitude());

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
            mMap.moveCamera(cameraUpdate);
        }
    }

    @Override
    protected  void onStart(){
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected() == false){
            Log.d(TAG,"onStart: mGoogleApiClient connect");
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop(){
        if(mRequestingLocationUpdates){
            Log.d(TAG,"onStop : call stopLocationUpdates");
            stopLocationUpdates();
        }
        if(mGoogleApiClient.isConnected()){
            Log.d(TAG,"onStop : mGoogleApiClient disconnect");
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private String getCurrentAddress(LatLng latLng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try{
            addresses = geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1);
        }catch (IOException ioException){
            //네트워크 문제
            Toast.makeText(this,"지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        }catch (IllegalArgumentException illegalArgumentException){
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";
        }

        if(addresses == null || addresses.size() == 0){
            Toast.makeText(this,"주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";
        }else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {

        if(mRequestingLocationUpdates == false){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }else {
                    Log.d(TAG, "onConnected : 퍼미션 가지고 있음");
                    Log.d(TAG, "onConnected : call startLocationUpdates");
                    startLocationUpdates();
                    mMap.setMyLocationEnabled(true);
                }
            }else {
                Log.d(TAG,"onConnected : call startLocationUpdates");
                startLocationUpdates();
                mMap.setMyLocationEnabled(true);
            }
        }
    }


    @Override
    public void onConnectionSuspended(int cause) {

        Log.d(TAG,"onConnectionSuspended");
        if(cause == CAUSE_NETWORK_LOST)
            Log.e(TAG,"onConnectionSuspended(): Google Play services" +
                    "connection lost. Cause: network lost.");
        else if(cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e(TAG,"onConnectionSuspended(): Google Play services" +
                    "connection lost. Cause: service disconnected");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG,"onConnectionFailed");
        setDefaultLocation();
    }

    //여기부터는 런타임 퍼미션 처리를 위한 메소드들
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions(){
        boolean fineLocationRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION);
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED && fineLocationRationale)
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
        else if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED && !fineLocationRationale){
            showDialogForPermissionSetting("퍼미션 거부 + Don't ask again(다시 묻지 않음) " +
                    "체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        }else if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"checkPermissions : 퍼미션 가지고 있음");

            if(mGoogleApiClient.isConnected() == false){
                Log.d(TAG,"checkPermissions : 퍼미션 가지고 있음");
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        if(permsRequestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.length > 0){
            boolean permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if(permissionAccepted){
                if(mGoogleApiClient.isConnected() == false){
                    Log.d(TAG,"onRequestPermissionResult : mGoogleApiClient connect");
                    mGoogleApiClient.connect();
                }

            }else {
                checkPermissions();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }

    private void showDialogForPermissionSetting(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + mActivity.getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(myAppSettings);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                finish();
            }
        });
        builder.create().show();
    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if(checkLocationServicesStatus()){
                    if(checkLocationServicesStatus()){
                        Log.d(TAG,"onActivityResult : 퍼미션 가지고 있음");

                        if(mGoogleApiClient.isConnected() == false){
                            Log.d(TAG,"onActivityResult : mGoogleApiClient connect");
                            mGoogleApiClient.connect();
                        }
                        return;
                    }
                }
                break;
        }
    }

}
