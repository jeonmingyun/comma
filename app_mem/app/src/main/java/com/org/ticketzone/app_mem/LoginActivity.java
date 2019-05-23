package com.org.ticketzone.app_mem;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.org.ticketzone.app_mem.Task.JsonArrayTask;
import com.org.ticketzone.app_mem.Task.SendDataSet;
import com.org.ticketzone.app_mem.db.DBOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private SessionCallback callback;
    private DBOpenHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBHelper = new DBOpenHelper(this);

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
                final String ID = response.getId()+"";
                final String NICKNAME = response.getNickname();
                final String BIRTH = response.getKakaoAccount().getBirthday()+"";
                final String GENDER = response.getKakaoAccount().getGender()+"";
                final String APP_RANGE = response.getKakaoAccount().getAgeRange()+"";

                JsonArrayTask jat = new JsonArrayTask("mem_db_login"){
                    @Override
                    protected void onPostExecute(JSONArray jsonArray) {
                        super.onPostExecute(jsonArray);
                        try {
//                            Log.e("idx 3", jsonArray.get(5).toString());
                            mDBHelper.insertMember(ID, NICKNAME, BIRTH, GENDER, APP_RANGE);
                            mDBHelper.insertOwner(new JSONArray(jsonArray.get(0).toString()));
                            mDBHelper.insertCategorie(new JSONArray(jsonArray.get(1).toString()));
                            mDBHelper.insertStoreMenu(new JSONArray(jsonArray.get(4).toString()));
                            mDBHelper.insertStore(new JSONArray(jsonArray.get(3).toString()));
                            mDBHelper.insertCoordinates(new JSONArray(jsonArray.get(2).toString()));
                            mDBHelper.insertTicket(new JSONArray(jsonArray.get(5).toString()));
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
        final Intent intent = new Intent(this, LoginActivity.class);
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
