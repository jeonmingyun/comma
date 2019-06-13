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
import android.text.style.BackgroundColorSpan;
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

import javax.crypto.BadPaddingException;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.JsonArrayTask;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.db.DBTable;
import ticketzone.org.com.app_mngr.vo.NumberTicketVO;

public class StoreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private List<Entry> entries;
    private List<NumberTicketVO> waitList, absenceList;
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
        Cursor ticket_wait = mDBHelper.t_wait(license_number);
        ticket_wait.moveToNext();
        t_wait.setText(ticket_wait.getString(0));

        Cursor ticket_success = mDBHelper.t_success(license_number);
        ticket_success.moveToNext();
        t_success.setText(ticket_success.getString(0));

        Cursor ticket_absence = mDBHelper.t_absence(license_number);
        ticket_absence.moveToNext();
        t_absence.setText(ticket_absence.getString(0));

        Cursor ticket_cancel = mDBHelper.t_cancel(license_number);
        ticket_cancel.moveToNext();
        t_cancel.setText(ticket_cancel.getString(0));

        lineChart = findViewById(R.id.chart);

        waitList(); // 고객 대기현황 리스트 가져오기
        absenceList(); // 번호표 취소, 부재 고객 리스트 가져오기
        waitingTable(); // 고객 대기현황 표 생성
        absenceTable(); // 고객 대기현황 표 생성

    }

    private void absenceList() {
        Cursor cursor = mDBHelper.absence_list(license_number);
        NumberTicketVO numberTicketVO;
        absenceList = new ArrayList<>();

        while (cursor.moveToNext()) {
            numberTicketVO = new NumberTicketVO();
            numberTicketVO.setTicket_code(cursor.getString(0));
            numberTicketVO.setWait_number(cursor.getInt(1));
            numberTicketVO.setMember_id(cursor.getString(2));
            numberTicketVO.setThe_number(cursor.getInt(3));
            numberTicketVO.setString_status(cursor.getString(4));

            absenceList.add(numberTicketVO);
        }
    }

    private void waitList() {
        Cursor cursor = mDBHelper.wait_list(license_number);
        NumberTicketVO numberTicketVO;
        waitList = new ArrayList<>();

        while (cursor.moveToNext()) {
            numberTicketVO = new NumberTicketVO();
            numberTicketVO.setTicket_code(cursor.getString(0));
            numberTicketVO.setWait_number(cursor.getInt(1));
            numberTicketVO.setMember_id(cursor.getString(2));
            numberTicketVO.setThe_number(cursor.getInt(3));
            numberTicketVO.setString_status(cursor.getString(4));

            waitList.add(numberTicketVO);
        }
    }

    private void absenceTable() {
        TableLayout tableLayout = findViewById(R.id.absence_table);
        TableRow tableRow;
        TextView wait_num, customer, count, status;

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ); // table row

        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.MATCH_PARENT
        ); // table cell
        cellParams.rightMargin=1;

        for ( int i = 0; i < absenceList.size(); i++) {
            tableRow = new TableRow(this);
            wait_num = new TextView(this);
            customer = new TextView(this);
            count = new TextView(this);
            status = new TextView(this);

            tableRow.setLayoutParams(tableRowParams);
            wait_num.setLayoutParams(cellParams);
            customer.setLayoutParams(cellParams);
            count.setLayoutParams(cellParams);
            status.setLayoutParams(cellParams);

            tableRow.setPadding(1,1,1,1);

            wait_num.setBackgroundColor(Color.WHITE);
            customer.setBackgroundColor(Color.WHITE);
            count.setBackgroundColor(Color.WHITE);
            status.setBackgroundColor(Color.WHITE);
            Log.e("ddd", absenceList.get(i)+"");

            wait_num.setText(absenceList.get(i).getTicket_code());
            customer.setText(absenceList.get(i).getMember_id());
            count.setText(absenceList.get(i).getThe_number()+"명");
            status.setText(absenceList.get(i).getString_status());

            tableRow.addView(wait_num);
            tableRow.addView(customer);
            tableRow.addView(count);
            tableRow.addView(status);

            tableLayout.addView(tableRow);
        }
    }

    private void waitingTable() {
        TableLayout tableLayout = findViewById(R.id.waiting_table);
        TableRow tableRow;
        TextView wait_num, customer, count, status;

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ); // table row

        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.MATCH_PARENT
        ); // table cell
        cellParams.rightMargin=1;

        for ( int i = 0; i < waitList.size(); i++) {
            tableRow = new TableRow(this);
            wait_num = new TextView(this);
            customer = new TextView(this);
            count = new TextView(this);
            status = new TextView(this);

            tableRow.setLayoutParams(tableRowParams);
            wait_num.setLayoutParams(cellParams);
            customer.setLayoutParams(cellParams);
            count.setLayoutParams(cellParams);
            status.setLayoutParams(cellParams);

            tableRow.setPadding(1,1,1,1);

            wait_num.setBackgroundColor(Color.WHITE);
            customer.setBackgroundColor(Color.WHITE);
            count.setBackgroundColor(Color.WHITE);
            status.setBackgroundColor(Color.WHITE);
            Log.e("ddd", waitList.get(i)+"");

            wait_num.setText(waitList.get(i).getTicket_code());
            customer.setText(waitList.get(i).getMember_id());
            count.setText(waitList.get(i).getThe_number()+"명");
            status.setText(waitList.get(i).getString_status());

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