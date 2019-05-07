package com.org.ticketzone.app_mem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.org.ticketzone.app_mem.vo.MemberVO;

public class MainActivity extends AppCompatActivity {

    EditText request, response;
    Button submit;
    String s_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = (EditText)findViewById(R.id.request);
        response = (EditText)findViewById(R.id.response);
        submit = (Button)findViewById(R.id.submits);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkTask nt = new NetworkTask("test") {
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        Log.e("2", s+"");
                        response.setText(s);
                    }
                };

                SendDataSet sds = new SendDataSet("owner_id", s_request);
                nt.execute(sds);
            }
        });
    }

    public void onClickLogout(View view) {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                redirectLoginActivity();
            }
        });
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
