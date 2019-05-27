package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

public class NumInfoActivity extends AppCompatActivity {

    private Button CancelButton;
    private TextView NowEnter;
    private NumberTicketVO numberTicketVO;
    private DBOpenHelper mDBHelper;
    private TextView MyNumber;
    private TextView storeName;
    private TextView Time;
    private TextView the_number;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_info);
        mDBHelper = new DBOpenHelper(this);

        selectAllTicket(); // Ticket 정보

        CancelButton = (Button)findViewById(R.id.CancelButton);
        NowEnter = (TextView)findViewById(R.id.nowEnter);
        Time = (TextView)findViewById(R.id.time);
        MyNumber = findViewById(R.id.myNumber);
        storeName = findViewById(R.id.storeName);
        the_number = findViewById(R.id.the_number);
        int wait_number = 0;
        int the_number2 = 0;
        String ticket_number = "";
        code = "";
        Intent intent = getIntent();
        String storename = intent.getExtras().getString("storename");
        final String member_id = intent.getExtras().getString("member_id");
        final String license = intent.getExtras().getString("license");


        Cursor cursor = mDBHelper.MyTicket(member_id,license);

        while(cursor.moveToNext()) {
            ticket_number = cursor.getString(0);
            code = cursor.getString(0);
            wait_number = cursor.getInt(1);
            the_number2 = cursor.getInt(2);
            ticket_number = ticket_number.substring(18);
        }
        NowEnter.setText(wait_number + "명");
        MyNumber.setText(ticket_number + "번");
        Time.setText(code + "테스트");
        the_number.setText(the_number2 + "명");


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
        numberTicketVO.setTicket_status(cursor.getInt(5));
    }

}