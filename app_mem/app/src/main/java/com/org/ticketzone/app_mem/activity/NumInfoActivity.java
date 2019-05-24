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
import com.org.ticketzone.app_mem.vo.NumberTicketVO;

import java.util.ArrayList;

public class NumInfoActivity extends AppCompatActivity {

    private Button CancelButton;
    private TextView NowEnter;
    private NumberTicketVO numberTicketVO;
    private DBOpenHelper mDBHelper;
    private TextView MyNumber;
    private TextView storeName;
    private TextView Time;

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

        int wait_number = numberTicketVO.getWait_number(); // 현재 입장한 팀
        int the_number = numberTicketVO.getThe_number(); //내 대기순번
        String ticket_code = numberTicketVO.getTicket_code(); // 발급받은 시간

        NowEnter.setText(wait_number+"");
        MyNumber.setText(the_number+"");
        Time.setText(ticket_code);

        Intent intent = getIntent();
        String storename = intent.getExtras().getString("storename");
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