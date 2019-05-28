package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;
import com.org.ticketzone.app_mem.task.NetworkTask;
import com.org.ticketzone.app_mem.task.SendDataSet;
import com.org.ticketzone.app_mem.vo.StoreMenuVO;
import com.org.ticketzone.app_mem.vo.StoreVO;

import java.util.ArrayList;

public class StoreDetailActivity extends AppCompatActivity {

    private TextView store_name;
    private ImageView store_img;
    private Button issue_btn;
    private DBOpenHelper mDBHelper;
    private StoreVO storeVO;
    private ArrayList<StoreMenuVO> menuList;
    private String license_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        mDBHelper = new DBOpenHelper(this);

        store_name = findViewById(R.id.store_name);
        store_img = findViewById(R.id.store_img);
        issue_btn = findViewById(R.id.issue_btn);

        Intent intent = getIntent();
        license_number = intent.getExtras().getString("license_number");

        selectStore(license_number);
        selectStoreMenu(license_number);
        Log.e("dddd", menuList.toString());
        store_name.setText(storeVO.getStore_name());
//        store_img.setImageURI();

        issue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText ET = new EditText(StoreDetailActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(StoreDetailActivity.this);
                dialog.setTitle("인원 수 설정" + storeVO.getStore_name());
                dialog.setMessage("인원 수");
                dialog.setView(ET);

                // 확인 버튼 이벤트
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputValue = ET.getText().toString();
                        Cursor cursor = mDBHelper.selectAllMember();
                        String member_id = "";

                        while(cursor.moveToNext()){
                            member_id = cursor.getString(0);
                        }

                        NetworkTask networkTask = new NetworkTask("Mem_issue_ticket") {

                        };
                        SendDataSet sds1 = new SendDataSet("member_id", member_id);
                        SendDataSet sds2 = new SendDataSet("the_number", inputValue);
                        SendDataSet sds3 = new SendDataSet("license_number", license_number);

                        networkTask.execute(sds1, sds2, sds3);
                        Toast.makeText(StoreDetailActivity.this, inputValue + "명 입력되었습니다.", Toast.LENGTH_SHORT).show();
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

    // 메뉴 정보
    private void selectStoreMenu(String license_number) {
        menuList = new ArrayList<>();
        StoreMenuVO storeMenuVO;
        Cursor cursor = mDBHelper.selectStoreMenu(license_number);

        while(cursor.moveToNext()) {
            storeMenuVO = new StoreMenuVO();
            storeMenuVO.setMenu_code(cursor.getString(0));
            storeMenuVO.setMenu_name(cursor.getString(1));
            storeMenuVO.setMenu_price(cursor.getString(2));
            storeMenuVO.setStore_note(cursor.getString(3));
            menuList.add(storeMenuVO);
        }
    }

    // 가게 정보
    private void selectStore(String license_number) {
        storeVO = new StoreVO();
        Cursor cursor = mDBHelper.selectStore(license_number);

        cursor.moveToNext();
        storeVO.setLicense_number(cursor.getString(0));
        storeVO.setR_name(cursor.getString(1));
        storeVO.setMax_number(cursor.getString(2));
        storeVO.setStore_status(cursor.getInt(3));
        storeVO.setCate_code(cursor.getString(4));
        storeVO.setOwner_id(cursor.getString(5));
        storeVO.setStore_tel(cursor.getString(6));
        storeVO.setStore_time(cursor.getString(7));
        storeVO.setStore_name(cursor.getString(8));
        storeVO.setStore_intro(cursor.getString(9));
        storeVO.setAddress_name(cursor.getString(10));
    }
}
