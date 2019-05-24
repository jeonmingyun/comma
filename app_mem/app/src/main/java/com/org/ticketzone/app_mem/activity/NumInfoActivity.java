package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.org.ticketzone.app_mem.R;

public class NumInfoActivity extends AppCompatActivity {

    private Button CancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_info);

        Button CancelButton = (Button)findViewById(R.id.CancelButton);
        TextView storeName = findViewById(R.id.storeName);
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

}