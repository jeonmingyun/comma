package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity {

    EditText m_owner_id, m_owner_password, m_owner_password_ck, m_owner_name, m_owner_tel, m_email;
    Button join, id_check;
    String owner_id, owner_password, owner_password_ck, owner_name, owner_tel, email;
    boolean checked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        m_owner_id = (EditText) findViewById(R.id.owner_id);
        m_owner_password = (EditText) findViewById(R.id.owner_password);
        m_owner_password_ck = (EditText) findViewById(R.id.owner_password_ck);
        m_owner_name = (EditText) findViewById(R.id.owner_name);
        m_owner_tel = (EditText) findViewById(R.id.owner_tel);
        m_email = (EditText) findViewById(R.id.email);
        join = (Button) findViewById(R.id.join);
        id_check = (Button)findViewById(R.id.id_check);


        m_owner_id.requestFocus();

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner_id = m_owner_id.getText().toString();
                owner_password = m_owner_password.getText().toString();
                owner_password_ck = m_owner_password_ck.getText().toString();
                owner_name = m_owner_name.getText().toString();
                owner_tel = m_owner_tel.getText().toString();
                email = m_email.getText().toString();
                MngrJoin mngrJoin = new MngrJoin(){
                    @Override
                    protected void onPostExecute(String data) {
                        super.onPostExecute(data);
                        if(checked == true) {
                            if (data.equals("1")) { // login success
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                };
                if(checked == true) {
                    if (owner_password.equals(owner_password_ck)) {
                        mngrJoin.execute("appregister", "owner_id=" + owner_id + "&owner_password=" + owner_password + "&owner_name=" + owner_name + "&owner_tel=" + owner_tel + "&email=" + email);
                    }
                }else if(checked == false){
                    if (owner_id.equals("")) {
                        Toast.makeText(SignUpActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                        m_owner_id.requestFocus();
                    } else if (owner_password.equals("")) {
                        Toast.makeText(SignUpActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                        m_owner_password.requestFocus();
                    } else if (owner_name.equals("")) {
                        Toast.makeText(SignUpActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                        m_owner_name.requestFocus();
                    } else if (owner_tel.equals("")) {
                        Toast.makeText(SignUpActivity.this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                        m_owner_tel.requestFocus();
                    } else if (email.equals("")) {
                        Toast.makeText(SignUpActivity.this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show();
                        m_email.requestFocus();
                    } else if (checked == false){
                        Toast.makeText(SignUpActivity.this, "아이디 중복 확인을 해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                    if(!owner_password.equals(owner_password_ck)){
                        Toast.makeText(SignUpActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show();
                        m_owner_password_ck.setText("");
                        m_owner_password_ck.requestFocus();
                    }
                }

<<<<<<< HEAD
//                MngrJoin id_ck = new MngrJoin() {
//                    @Override
//                    protected void onPostExecute(String data) {
//                        super.onPostExecute(data);
//
//                        if( data.equals("1")) { // login success
//
//                        }
//                    }
//                };
//
//                id_ck.execute();
            }
        });

        id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner_id = m_owner_id.getText().toString();

                MngrJoin check = new MngrJoin() {
                    @Override
                    protected  void onPostExecute(String data){
                        super.onPostExecute(data);

                        if(data.equals("1")){
                            Toast.makeText(SignUpActivity.this, "사용 가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            checked = true;
                        }else if(data.equals("0")){
                            Toast.makeText(SignUpActivity.this, "사용할 수 없는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                            m_owner_id.setText("");
                            m_owner_id.requestFocus();
                        }
                    }
                };
                check.execute("id_check", "owner_id="+ owner_id);
            }
        });
    }

    public class MngrJoin extends AsyncTask<String, Void, String> {
        String ip ="39.127.7.46"; //학교 IP번호
        String path = "http://"+ip+":8080/"; // 연결할 jsp주소

        @Override
        protected String doInBackground(String... strings) {
            path += strings[0];
            String param = strings[1];
            String data ="";

            try {
                /* 서버연결 */
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.connect();

                /* 안드로이드 -> 서버 파라메터값  전달 */
                OutputStream os = conn.getOutputStream();
                os.write(param.getBytes("utf-8"));
                os.flush();
                os.close();

                /* 서버 -> 안드로이드 파라메터값 전달 */
                InputStream is = null;
                BufferedReader br = null;

                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is), 8*1024);
                StringBuffer sBuff = new StringBuffer();
                String line = null;

                while ((line = br.readLine()) != null) {
                    sBuff.append(line +"\n");
                }
                data = sBuff.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }


    }
=======
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
>>>>>>> e05e5ace8f07adce9f1425e9a9d311d5a91a7b4d
}
