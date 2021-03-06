package ticketzone.org.com.app_mngr.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.JsonArrayTask;
import ticketzone.org.com.app_mngr.Task.NetworkTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

public class LoginActivity extends AppCompatActivity {

    private DBOpenHelper mDBHelper;
    EditText et_owner_id, et_owner_password;
    Button login, sign_up;
    String owner_id, owner_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBHelper = new DBOpenHelper(this);

//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("ticketzone.org.com.app_mngr", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

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
                    ProgressDialog asyncDialog;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        asyncDialog = new ProgressDialog(LoginActivity.this);
                        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        asyncDialog.setMessage("로딩중입니다..");
                        asyncDialog.show();
                    }

                    @Override
                    protected void onPostExecute(String data) {
                        super.onPostExecute(data);
                        mDBHelper.deleteAllTable();

                        if( data.equals("1")) { // login success
                            JsonArrayTask jat = new JsonArrayTask("mngr_db_login"){
                                @Override
                                protected void onPostExecute(JSONArray jsonArray) {
                                    super.onPostExecute(jsonArray);
                                    try{
                                        Log.e("insertOwner", jsonArray.get(0).toString());
                                        Log.e("insertCategorie", jsonArray.get(1).toString());
                                        Log.e("insertStoreMenu", jsonArray.get(3).toString());
                                        Log.e("insertStore", jsonArray.get(2).toString());
                                        Log.e("abc", jsonArray.get(4).toString());
                                        mDBHelper.insertOwner(new JSONArray(jsonArray.get(0).toString()));
                                        mDBHelper.insertCategorie(new JSONArray(jsonArray.get(1).toString()));
                                        mDBHelper.insertStoreMenu(new JSONArray(jsonArray.get(3).toString()));
                                        mDBHelper.insertStore(new JSONArray(jsonArray.get(2).toString()));
                                        mDBHelper.insertTicket(new JSONArray(jsonArray.get(4).toString()));

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            //                            //owner_id값 송신
            //                            intent.putExtra("id",owner_id);
                                        startActivity(intent);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }

                                    asyncDialog.dismiss(); // loading dialog 닫기

                                }
                            };
                            SendDataSet sds = new SendDataSet("owner_id", owner_id);
                            jat.execute(sds);

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

//                LoadingTask load = new LoadingTask();
//                load.execute();
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

    private class LoadingTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog;

        @Override
        protected void onPreExecute() {
            asyncDialog = new ProgressDialog(LoginActivity.this);
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            asyncDialog.setMessage("로딩중입니다..");
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    asyncDialog.setProgress(i * 5);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }

}
