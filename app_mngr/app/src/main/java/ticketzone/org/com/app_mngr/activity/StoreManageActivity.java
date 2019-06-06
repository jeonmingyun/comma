package ticketzone.org.com.app_mngr.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ticketzone.org.com.app_mngr.R;

public class StoreManageActivity extends AppCompatActivity {

    private TextView TimeText;
    private TextView TimeText2;
    private int T;
    private int mHour, mMinute;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage);

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
        //TabHost
        TabHost host=(TabHost)findViewById(R.id.storemanage);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("매장관리");
        spec.setIndicator("매장관리", null);
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("번호표 발급 설정");
        spec.setIndicator("번호표 발급 설정", null);
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("메뉴설정");
        spec.setIndicator("메뉴설정",null);
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);
    }

    private void TimeSet(){
        TimeText = findViewById(R.id.TimeText);
        TimeText2 = findViewById(R.id.TimeText2);

        Calendar cal = new GregorianCalendar();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        TimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(StoreManageActivity.this, mTimeSetListener, mHour, mMinute, false).show();
                T = 1;
            }
        });

        TimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(StoreManageActivity.this, mTimeSetListener, mHour, mMinute, false).show();
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
