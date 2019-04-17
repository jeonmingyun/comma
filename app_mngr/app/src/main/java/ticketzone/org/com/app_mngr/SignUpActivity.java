package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class SignUpActivity extends AppCompatActivity {

    EditText owner_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        owner_id = (EditText)findViewById(R.id.owner_id);
        owner_id.requestFocus();

//        LoginActivity.MngrLoginTask mngrLoginTask = new LoginActivity.MngrLoginTask();
////                /* DB 대조 */
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("tel", tel);
////                params.put("person", person);
////
//        mngrLoginTask.execute();
    }

//    public class MngrLoginTask extends AsyncTask<String, Void, String> {
//        String ip ="39.127.7.42"; //학교 IP번호
//        String path = "http://"+ip+"/login"; // 연결할 jsp주소
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
