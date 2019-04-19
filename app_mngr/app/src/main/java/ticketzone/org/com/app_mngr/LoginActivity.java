package ticketzone.org.com.app_mngr;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    EditText et_owner_id, et_owner_password;
    Button login, sign_up;
    String owner_id, owner_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
    //                MngrLoginTask mngrLoginTask = new MngrLoginTask();
    ////                /* DB 대조 */
    ////                Map<String, String> params = new HashMap<String, String>();
    ////                params.put("tel", tel);
    ////                params.put("person", person);
    ////
    //                mngrLoginTask.execute();
                NetworkTask networkTask = new NetworkTask("login") {
                    @Override
                    protected void onPostExecute(String data) {
                        super.onPostExecute(data);

                        if( data.equals("1")) { // login success
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if( data.equals("0")) { // pass wrong
                            Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            et_owner_password.setText("");
                            et_owner_password.requestFocus();
                        } else { // id wrong
                            Toast.makeText(LoginActivity.this, data+"아이디가 틀렸습니다.", Toast.LENGTH_SHORT).show();
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

//    public class MngrLoginTask extends AsyncTask<String, Void, String> {
//        String ip ="39.127.7.46"; //학교 IP번호
//        String path = "http://"+ip+":8080/login"; // 연결할 jsp주소
//
//        @Override
//        protected String doInBackground(String... strings) {
//            String param = "owner_id="+ owner_id +"&owner_password="+ owner_password +"";
//            String data ="";
//
//            try {
//                /* 서버연결 */
//                URL url = new URL(path);
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//                conn.setRequestMethod("POST");
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//                conn.connect();
//
//                /* 안드로이드 -> 서버 파라메터값  전달 */
//                OutputStream os = conn.getOutputStream();
//                os.write(param.getBytes("utf-8"));
//                os.flush();
//                os.close();
//
//                /* 서버 -> 안드로이드 파라메터값 전달 */
//                InputStream is = null;
//                BufferedReader br = null;
//
//                is = conn.getInputStream();
//                br = new BufferedReader(new InputStreamReader(is), 8*1024);
//                StringBuffer sBuff = new StringBuffer();
//                String line = null;
//
//                while ((line = br.readLine()) != null) {
//                    sBuff.append(line +"\n");
//                }
//                data = sBuff.toString().trim();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return data;
//        }
//
//        @Override
//        protected void onPostExecute(String data) {
//            super.onPostExecute(data);
//
//            if( data.equals("1")) { // login success
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            } else if( data.equals("0")) { // pass wrong
//                Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
//                et_owner_password.setText("");
//                et_owner_password.requestFocus();
//            } else { // id wrong
//                Toast.makeText(LoginActivity.this, data+"아이디가 틀렸습니다.", Toast.LENGTH_SHORT).show();
//                et_owner_id.setText("");
//                et_owner_password.setText("");
//                et_owner_id.requestFocus();
//            }
//        }
//    }
}
