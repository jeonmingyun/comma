package ticketzone.org.com.app_mngr.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.NetworkTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

public class StoreManageActivity extends AppCompatActivity {

    private TextView TimeText;
    private TextView TimeText2;
    private int T;
    private int mHour, mMinute;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private DBOpenHelper mDBHelper;
    private TextView storename;
    private String store_time;
    private String store_time2;
    private Button TimeUpdate_button;
    private Button MaxnumUpdate_button;
    private TextView MaxNum;
    private String license_number;
    private String max_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage);
        mDBHelper = new DBOpenHelper(this);
        //TabHost 매장관리
        TimeText = findViewById(R.id.TimeText);
        TimeText2 = findViewById(R.id.TimeText2);
        TimeUpdate_button = findViewById(R.id.TimeUpdate_button);
        //TabHost 번호표 발급 설정
        MaxnumUpdate_button = findViewById(R.id.MaxnumUpdate_button);
        MaxNum = findViewById(R.id.MaxNum);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TabHost();
        TimeSet();

    }

    //뒤로가기 기능
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void TabHost(){
        Intent intent = getIntent();
        String s_name = intent.getExtras().getString("store_name");
        storename = findViewById(R.id.storename);
        //TabHost
        TabHost host=(TabHost)findViewById(R.id.storemanage);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("매장관리");
        spec.setIndicator("매장관리", null);
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);
        Cursor cursor = mDBHelper.selectStore(s_name);
        while (cursor.moveToNext()){
            storename.setText(cursor.getString(8));
            license_number = cursor.getString(0);
            max_number = cursor.getString(2);
            store_time = cursor.getString(7).substring(0,5);//6,11
            store_time2 = cursor.getString(7).substring(6,11);
            TimeText.setText(store_time);
            TimeText2.setText(store_time2);
        }
        //Task 동기화 해야함
        TimeUpdate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StoreManageActivity.this);
                alertDialogBuilder.setTitle("수정");
                alertDialogBuilder
                        .setMessage("수정하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        NetworkTask networkTask = new NetworkTask("update_time"){
                                        };
                                        SendDataSet sds1 = new SendDataSet("license_number", license_number);
                                        SendDataSet sds2 = new SendDataSet("store_time", TimeText.getText().toString() + '-' + TimeText2.getText().toString());
                                       networkTask.execute(sds1,sds2);
                                        mDBHelper.updateStore_time(license_number,TimeText.getText().toString(),TimeText2.getText().toString());
                                        Toast.makeText(StoreManageActivity.this, "수정되었습니다.",Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        spec = host.newTabSpec("번호표 발급 설정");
        spec.setIndicator("번호표 발급 설정", null);
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);
        MaxNum.setText(max_number);
        //Task 동기화 해야함
        MaxnumUpdate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(StoreManageActivity.this);
                alertDialogBuilder.setTitle("수정");
                alertDialogBuilder
                        .setMessage("수정하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        NetworkTask networkTask = new NetworkTask("store_max"){
                                        };
                                        SendDataSet sds1 = new SendDataSet("license_number", license_number);
                                        SendDataSet sds2 = new SendDataSet("max_number", MaxNum.getText().toString());
                                        networkTask.execute(sds1,sds2);
                                        mDBHelper.updateStore_maxnum(license_number,MaxNum.getText().toString());
                                        Toast.makeText(StoreManageActivity.this, "수정되었습니다.",Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        spec = host.newTabSpec("메뉴설정");
        spec.setIndicator("메뉴설정",null);
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);
    }

    private void TimeSet(){
        Calendar cal = new GregorianCalendar();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(StoreManageActivity.this, mTimeSetListener, mHour, mMinute, true).show();
                T = 1;
            }
        });

        TimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(StoreManageActivity.this, mTimeSetListener, mHour, mMinute, true).show();
                T = 2;
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMinute = minute;

                UpdateNow();
            }
        };
    }

    private void UpdateNow() {
        if(T == 1) {
            TimeText.setText(String.format("%02d:%02d", mHour, mMinute));
        }else if(T == 2){
            TimeText2.setText(String.format("%02d:%02d", mHour, mMinute));
        }
    }
}
