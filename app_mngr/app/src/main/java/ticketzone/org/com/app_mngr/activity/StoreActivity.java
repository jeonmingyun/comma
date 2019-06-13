package ticketzone.org.com.app_mngr.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.JsonArrayTask;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

public class StoreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private List<Entry> entries;
    private TextView store_name, wait_count, t_wait, t_success, t_absence, t_cancel;
    private DBOpenHelper mDBHelper;
    private ImageView storeimg;
    private String license_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_store);
        store_name = findViewById(R.id.store_name);
        storeimg = findViewById(R.id.storeimg);
        wait_count = findViewById(R.id.wait_count);
        t_wait = findViewById(R.id.t_wait);
        t_absence = findViewById(R.id.t_absence);
        t_cancel = findViewById(R.id.t_cancel);
        t_success = findViewById(R.id.t_success);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TabHost
        TabHost host=(TabHost)findViewById(R.id.store);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator("메인");
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator("대기 현황");
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator("통계");
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        /*이미지 불러오기*/
        Intent intent = getIntent();
        String s_name = intent.getExtras().getString("store_name");
        String url = "http://15.164.115.73:8080/resources/img/";

        /*이미지 적용*/
        Cursor cursor = mDBHelper.selectStore(s_name);
        while(cursor.moveToNext()){
            store_name.setText(cursor.getString(8));
            license_number = cursor.getString(0);
            Glide.with(this).load(url + cursor.getString(11) + "/" + cursor.getString(10) + "_" + cursor.getString(12)).into(storeimg);
        }
        /*대기인원 확인*/
        Cursor cursor1 = mDBHelper.selectWating(license_number);
        while(cursor1.moveToNext()){
            wait_count.setText(cursor1.getString(0));
        }
        /*테이블 채우는 작업*/
        Cursor cursor2 = mDBHelper.ticketStatus(license_number);
        while (cursor2.moveToNext()){
            t_wait.setText(cursor2.getString(0));
            t_success.setText(cursor2.getString(1));
            t_cancel.setText(cursor2.getString(2));
            t_absence.setText(cursor2.getString(3));
        }

        lineChart = (LineChart)findViewById(R.id.chart);




        waitingTable(); // 고객 대기현황 표 생성
    }

    private void waitingTable() {
        TableLayout tableLayout = findViewById(R.id.waiting_table);
        TableRow tableRow;
        TextView wait_num, customer, count, status;

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.MATCH_PARENT
        );
//        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );


        for ( int i = 0; i < 3; i++) {
            tableRow = new TableRow(this);
            tableRow.setLayoutParams(tableRowParams);

            wait_num = new TextView(this);
            wait_num.setLayoutParams(cellParams);

            customer = new TextView(this);
            wait_num.setLayoutParams(cellParams);

            count = new TextView(this);
            wait_num.setLayoutParams(cellParams);

            status = new TextView(this);
            wait_num.setLayoutParams(cellParams);

            wait_num.setText(i);
            customer.setText("11");
            count.setText("11");
            status.setText("11");

            tableRow.addView(wait_num);
            tableRow.addView(customer);
            tableRow.addView(count);
            tableRow.addView(status);

            tableLayout.addView(tableRow);
        }

    }

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

}