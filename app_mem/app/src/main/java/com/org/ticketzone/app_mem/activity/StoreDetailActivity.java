package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuAdapter;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuItem;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuTitle;
import com.org.ticketzone.app_mem.task.NetworkTask;
import com.org.ticketzone.app_mem.task.SendDataSet;
import com.org.ticketzone.app_mem.vo.StoreMenuVO;
import com.org.ticketzone.app_mem.vo.StoreVO;

import java.util.ArrayList;

public class StoreDetailActivity extends AppCompatActivity {

    private TextView store_name, store_tel, store_time, address_name, store_intro;
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

        store_tel = findViewById(R.id.store_tel);
        store_time = findViewById(R.id.store_time);
        address_name = findViewById(R.id.address_name);
        store_intro = findViewById(R.id.store_intro);

        Intent intent = getIntent();
        license_number = intent.getExtras().getString("license_number");

        tabHost();
        selectStore(license_number);
        selectStoreMenu(license_number);
        setStoreDetail();
        setMenuList();
        Log.e("menu", menuList.toString());

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

    private void setMenuList() {
        RecyclerView recyclerView = findViewById(R.id.menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MenuTitle> menuTitles = new ArrayList<>();
        ArrayList<MenuItem> menuItems;
        MenuTitle menuTitle;
        StoreMenuVO storeMenuVO;

        for (int i = 0; i < menuList.size(); i++) {
            String[] categorie;
            storeMenuVO = menuList.get(i);
            categorie = storeMenuVO.getMenu_name().split("-");

//            menuItems = new ArrayList<>();
//            menuItems.add(new MenuItem(storeMenuVO));

        }
//        menuTitle = new MenuTitle("A", menuItems);
//        menuTitles.add(menuTitle);

        for ( int i = 0; i < 3; i++) {

        }
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("1"));
        menuItems.add(new MenuItem("2"));
        menuItems.add(new MenuItem("3"));

        menuTitle = new MenuTitle("A", menuItems);
        menuTitles.add(menuTitle);

        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("1"));
        menuItems.add(new MenuItem("2"));
        menuItems.add(new MenuItem("3"));

        menuTitle = new MenuTitle("B", menuItems);
        menuTitles.add(menuTitle);

        MenuAdapter adapter = new MenuAdapter(menuTitles);
        recyclerView.setAdapter(adapter);
    }

    private void  setStoreDetail() {
        store_tel.setText(storeVO.getStore_tel());
        store_time.setText(storeVO.getStore_time());
        address_name.setText(storeVO.getAddress_name());
        store_intro.setText(storeVO.getStore_intro());
    }

    // tagHost 화면 Layout 바꿔끼우기
    private void tabHost() {
        TabHost host= findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("detail");
        spec.setIndicator("상세 정보");
        spec.setContent(R.id.detail);
        host.addTab(spec);

        spec = host.newTabSpec("menu");
        spec.setIndicator("메뉴");
        spec.setContent(R.id.menu);
        host.addTab(spec);

        spec = host.newTabSpec("graph");
        spec.setIndicator("통계");
        spec.setContent(R.id.graph);
        host.addTab(spec);
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
