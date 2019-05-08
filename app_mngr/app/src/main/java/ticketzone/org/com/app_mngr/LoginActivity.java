package ticketzone.org.com.app_mngr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    EditText et_owner_id, et_owner_password;
    Button login, sign_up;
    String owner_id, owner_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            PackageInfo info = getPackageManager().getPackageInfo("ticketzone.org.com.app_mngr", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        et_owner_id = (EditText) findViewById(R.id.owner_id);
        et_owner_password = (EditText) findViewById(R.id.owner_password);
        login = (Button) findViewById(R.id.login);
        sign_up = (Button)findViewById(R.id.sign_up);

        et_owner_id.requestFocus();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner_id = et_owner_id.getText().toString();
                owner_password = et_owner_password.getText().toString();

                NetworkTask networkTask = new NetworkTask("login") {
                    @Override
                    protected void onPostExecute(String data) {
                        super.onPostExecute(data);
                        Log.d(getClass().getName(), data);
                        if( data.equals("1")) { // login success
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            //owner_id값 송신
//                            intent.putExtra("id",owner_id);
                            startActivity(intent);
                        } else if( data.equals("0")) { // pass wrong
                            Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            et_owner_password.setText("");
                            et_owner_password.requestFocus();
                        } else { // id wrong
                            Toast.makeText(LoginActivity.this, "아이디가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            et_owner_id.setText("");
                            et_owner_password.setText("");
                            et_owner_id.requestFocus();
                        }
                    }
                };

                SendDataSet sds1 = new SendDataSet("owner_id", owner_id);
                SendDataSet sds2 = new SendDataSet("owner_password", owner_password);

                networkTask.execute(sds1, sds2);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}
