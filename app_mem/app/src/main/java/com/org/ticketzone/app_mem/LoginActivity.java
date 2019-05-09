package com.org.ticketzone.app_mem;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private SessionCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
//        getKeyHash();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            requestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    private void requestMe() {
        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("kakao_account.birthday");
        keys.add("kakao_account.gender");
        keys.add("kakao_account.age_range");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            // 사용자 정보 요청이 성공한 경우로 사용자 정보 객체를 받습니다.
            @Override
            public void onSuccess(MeV2Response response) {
                Log.e("success", "아히아허이하");
                Log.d("user id : ", response.getId()+"");
                Log.d("user getNickname : ", response.getNickname()+"");
                Log.d("user getBirthday : ", response.getKakaoAccount().getBirthday()+"");
                Log.d("user getGender : ", response.getKakaoAccount().getGender()+"");
                Log.d("user age_range : ", response.getKakaoAccount().getAgeRange()+"");

                JsonArrayTask jat = new JsonArrayTask("mem_db_login"){
                    @Override
                    protected void onPostExecute(JSONArray jsonArray) {
                        super.onPostExecute(jsonArray);

                        try {
                            Log.e("index 0 ", jsonArray.get(0).toString());
                            Log.e("index 1 ", jsonArray.get(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                SendDataSet sds = new SendDataSet("member_tel", "010-1234-5678");
                jat.execute(sds);


                redirectMainActivity();
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

//            @Override
//            public void onNotSignedUp() {
//                showSignup();
//            }
        });
    }
    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //디버그 키해시 구하기
    private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}
