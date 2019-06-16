package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;
import com.org.ticketzone.app_mem.task.JsonArrayTask;
import com.org.ticketzone.app_mem.task.NetworkTask;
import com.org.ticketzone.app_mem.task.SendDataSet;
import com.org.ticketzone.app_mem.vo.NumberTicketVO;

import org.json.JSONArray;
import org.json.JSONException;

public class NumInfoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Button CancelButton;
    private TextView NowEnter;
    private NumberTicketVO numberTicketVO;
    private DBOpenHelper mDBHelper;
    private TextView MyNumber;
    private TextView storeName;
    private TextView Time;
    private TextView the_number;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_info);
        mDBHelper = new DBOpenHelper(this);

        CancelButton = findViewById(R.id.CancelButton);
        NowEnter = findViewById(R.id.nowEnter);
        Time = findViewById(R.id.time);
        MyNumber = findViewById(R.id.myNumber);
        storeName = findViewById(R.id.storeName);
        the_number = findViewById(R.id.the_number);

        mSwipeRefreshLayout = findViewById(R.id.swipe_layout);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        selectAllTicket(); // Ticket 정보

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int wait_number = 0;
        int the_number2 = 0;
        String ticket_number = "";
        String ticket_reg = "";
        String c_enter = "";
        Intent intent = getIntent();
        String storename = intent.getExtras().getString("storename");
        final String member_id = intent.getExtras().getString("member_id");
        final String license = intent.getExtras().getString("license");

        Cursor Now_Enter = mDBHelper.Current_Enter(license);
        while(Now_Enter.moveToNext()){
            c_enter = Now_Enter.getString(0);
        }
        c_enter = c_enter.substring(18);
        int current_en = Integer.parseInt(c_enter) -1;
        Log.e("c_enter", current_en + "번");

        Cursor cursor = mDBHelper.MyTicket(member_id,license);
        while(cursor.moveToNext()) {
            ticket_number = cursor.getString(0);
            code = cursor.getString(0);
            wait_number = cursor.getInt(1);
            the_number2 = cursor.getInt(2);
            ticket_reg = cursor.getString(6);
            ticket_number = ticket_number.substring(18);
            NowEnter.setText("0" + current_en + "번");
            MyNumber.setText(ticket_number + "번");
            Time.setText(ticket_reg);
            the_number.setText(the_number2 + "명");


        }



        storeName.setText(storename);

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(NumInfoActivity.this);
                dialog.setTitle("발급취소하시겠습니까?");

                // 확인 버튼 이벤트
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NetworkTask networkTask = new NetworkTask("TicketCancel"){

                        };
                        SendDataSet sds = new SendDataSet("member_id", member_id);
                        SendDataSet sds2 = new SendDataSet("license_number", license);
                        SendDataSet sds3 = new SendDataSet("ticket_code", code);
                        networkTask.execute(sds,sds2,sds3);
                        mDBHelper.cancelTicket(code , member_id , license);
                        mDBHelper.syncTicket(code,license);
                        Intent numInfoIntent = new Intent(NumInfoActivity.this, MainActivity.class);
                        startActivity(numInfoIntent);
                    }
                });

                //취소 버튼 이벤트
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });

    }




    private void selectAllTicket(){
        Cursor cursor = mDBHelper.selectAllTicket();
        numberTicketVO = new NumberTicketVO();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "not found data", Toast.LENGTH_SHORT).show();
        }

        cursor.moveToNext();

        numberTicketVO.setTicket_code(cursor.getString(0));
        numberTicketVO.setWait_number(cursor.getInt(1));
        numberTicketVO.setThe_number(cursor.getInt(2));
        numberTicketVO.setLicense_number(cursor.getString(3));
        numberTicketVO.setMember_id(cursor.getString(4));
        numberTicketVO.setTicket_status(cursor.getString(5));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                int wait_number = 0;
                int the_number2 = 0;
                String ticket_number = "";
                String ticket_reg = "";
                String c_enter = "";
                Intent intent = getIntent();
                String storename = intent.getExtras().getString("storename");
                final String member_id = intent.getExtras().getString("member_id");
                final String license = intent.getExtras().getString("license");

                AlertDialog.Builder dialog = new AlertDialog.Builder(NumInfoActivity.this);
                dialog.setTitle("발급취소하시겠습니까?");

                // 확인 버튼 이벤트
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NetworkTask networkTask = new NetworkTask("TicketCancel"){

                        };
                        SendDataSet sds = new SendDataSet("member_id", member_id);
                        SendDataSet sds2 = new SendDataSet("license_number", license);
                        SendDataSet sds3 = new SendDataSet("ticket_code", code);
                        networkTask.execute(sds,sds2,sds3);
                        mDBHelper.cancelTicket(code , member_id , license);
                        mDBHelper.syncTicket(code,license);
                        Intent numInfoIntent = new Intent(NumInfoActivity.this, MainActivity.class);
                        startActivity(numInfoIntent);
                    }
                });

                //취소 버튼 이벤트
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        JsonArrayTask task = new JsonArrayTask("my_ticket_refresh") {
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);
                Log.e("ddd", jsonArray.toString());

                try {
                    int wait_number = 0;
                    int the_number2 = 0;
                    String ticket_number = "";
                    String ticket_reg = "";
                    String c_enter = "";
                    Intent intent = getIntent();
                    String storename = intent.getExtras().getString("storename");
                    final String member_id = intent.getExtras().getString("member_id");
                    final String license = intent.getExtras().getString("license");

                    mDBHelper.updateTicket(new JSONArray(jsonArray.get(0).toString()));

                    Cursor Now_Enter = mDBHelper.Current_Enter(license);
                    while (Now_Enter.moveToNext()) {
                        c_enter = Now_Enter.getString(0);
                    }
                    c_enter = c_enter.substring(18);
                    int current_en = Integer.parseInt(c_enter) - 1;
                    Log.e("c_enter", current_en + "번");

                    Cursor cursor = mDBHelper.MyTicket(member_id, license);
                    while (cursor.moveToNext()) {
                        ticket_number = cursor.getString(0);
                        code = cursor.getString(0);
                        wait_number = cursor.getInt(1);
                        the_number2 = cursor.getInt(2);
                        ticket_reg = cursor.getString(6);
                        ticket_number = ticket_number.substring(18);
                        NowEnter.setText("0" + current_en + "번");
                        MyNumber.setText(ticket_number + "번");
                        Time.setText(ticket_reg);
                        the_number.setText(the_number2 + "명");
                    }

                    storeName.setText(storename);
                } catch (JSONException e ) {
                    e.printStackTrace();
                }

            }
        };

        SendDataSet license_number = new SendDataSet("license_number", numberTicketVO.getLicense_number());
        task.execute(license_number);



        mSwipeRefreshLayout.setRefreshing(false);
    }
}