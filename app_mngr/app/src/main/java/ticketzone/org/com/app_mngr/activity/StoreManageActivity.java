package ticketzone.org.com.app_mngr.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.NetworkTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.expandableRecyclerview.StoreMenuAdapter;
import ticketzone.org.com.app_mngr.expandableRecyclerview.StoreMenuItem;
import ticketzone.org.com.app_mngr.expandableRecyclerview.StoreMenuTitle;
import ticketzone.org.com.app_mngr.vo.StoreMenuVO;

public class StoreManageActivity extends AppCompatActivity {

    private TextView TimeText, TimeText2, store_intro_text, MaxNum;
    private int T;
    private int mHour, mMinute;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private DBOpenHelper mDBHelper;
    private ArrayList<StoreMenuVO> menuList;
    private String store_time, store_time2, license_number, max_number, title, store_intro;
    private ImageButton TimeUpdate_button, MaxnumUpdate_button, IntroUpdate_button, menu_update;
    private StoreMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_manage);
        mDBHelper = new DBOpenHelper(this);
        //TabHost 매장관리
        TimeText = findViewById(R.id.TimeText);
        TimeText2 = findViewById(R.id.TimeText2);
        store_intro_text = findViewById(R.id.store_intro_text);
        IntroUpdate_button = findViewById(R.id.IntroUpdate_button);
        TimeUpdate_button = findViewById(R.id.TimeUpdate_button);
        //TabHost 번호표 발급 설정
        MaxnumUpdate_button = findViewById(R.id.MaxnumUpdate_button);
        MaxNum = findViewById(R.id.MaxNum);

        TabHost();
        TimeSet();
        selectStoreMenu(license_number);// 메뉴 정보를 가져옴
        setMenuList(); // 메뉴 리스트 화면에 그리기
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        menuUpdClicked();
    }

    private void menuUpdClicked() {
        RecyclerView rv = findViewById(R.id.menu_list);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ddd", "dfdf");
            }
        });
//        menu_update = findViewById(R.id.menu_update);
//        Log.e("ddd1", menu_update.getHeight()+"");
//
//        menu_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("ddd2", "2");
////                Map<String, String> childmap = adapter.getChildItem();
////                Log.e("ddd", childmap.get("menu_code") + ", " + childmap.get("menu_name"));
//            }
//        });
    }

    private void setMenuList() {
        RecyclerView recyclerView = findViewById(R.id.menu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<StoreMenuTitle> menuTitles = new ArrayList<>();
        ArrayList<StoreMenuItem> menuItems = null;
        StoreMenuTitle menuTitle;
        StoreMenuVO storeMenuVO;
        Map<String,ArrayList<StoreMenuItem>> menuHash = new HashMap<>();
        Set set;// hashMap key 가져오기
        Iterator iterator;

        for (int i = 0; i < menuList.size(); i++) {
            String[] categorie;
            storeMenuVO = menuList.get(i);
            categorie = storeMenuVO.getMenu_name().split("-");

            if( menuHash.get(categorie[0]) == null) {
                menuItems = new ArrayList<>();
                menuHash.put(categorie[0], menuItems); // key : store license number, value : store menu items
            }

            menuItems = menuHash.get(categorie[0]);
            menuItems.add(new StoreMenuItem(storeMenuVO, this));// store menu item 추가
            menuHash.put(categorie[0], menuItems);
        }

        set = menuHash.keySet();// menuHash key 가져오기
        iterator = set.iterator(); // 반복문에서 set.size()와 비슷한 이유로 사용

        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            menuTitle = new StoreMenuTitle(key, menuHash.get(key));
            menuTitles.add(menuTitle);
        }

        StoreMenuAdapter adapter = new StoreMenuAdapter(menuTitles);
        recyclerView.setAdapter(adapter);

    }

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
        //TabHost
        final TabHost host=(TabHost)findViewById(R.id.storemanage);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("store_mngr");
        spec.setIndicator("매장관리", null);
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);
        Cursor cursor = mDBHelper.selectStore(s_name);
        while (cursor.moveToNext()){
            title = cursor.getString(8);
            store_intro = cursor.getString(9);
            license_number = cursor.getString(0);
            max_number = cursor.getString(2);
            store_time = cursor.getString(7).substring(0,5);//6,11
            store_time2 = cursor.getString(7).substring(6,11);
            TimeText.setText(store_time);
            TimeText2.setText(store_time2);
            store_intro_text.setText(store_intro);
        }

        IntroUpdate_button.setOnClickListener(new View.OnClickListener() {
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
                                        NetworkTask networkTask = new NetworkTask("store_intro"){
                                        };
                                        SendDataSet sds1 = new SendDataSet("license_number", license_number);
                                        SendDataSet sds2 = new SendDataSet("store_intro", store_intro_text.getText().toString());
                                        networkTask.execute(sds1,sds2);
                                        mDBHelper.updateStore_intro(license_number,store_intro_text.getText().toString());
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
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                alertDialog.show();
            }
        });
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
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                alertDialog.show();
            }
        });

        spec = host.newTabSpec("num_ticket");
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
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                alertDialog.show();
            }
        });

        spec = host.newTabSpec("menu");
        spec.setIndicator("메뉴",null);
        spec.setContent(R.id.menu);
        host.addTab(spec);
        //tabhost textcolor 변경
        for(int i = 0; i <host.getTabWidget().getChildCount(); i++){
            View tabView = host.getTabWidget().getChildAt(i);
            TextView tv = tabView.findViewById(android.R.id.title);
            tv.setTextColor(Color.WHITE);
        }
        host.getTabWidget().getChildAt(host.getCurrentTab())
                .setBackgroundResource(R.drawable.selected_border);
        View tabView = host.getTabWidget().getChildAt(0);
        TextView tv = tabView.findViewById(android.R.id.title);
        tv.setTextColor(Color.rgb(113,141,255));

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for(int i = 0; i < host.getTabWidget().getChildCount(); i++){
                    host.getTabWidget().getChildAt(i)
                            .setBackgroundResource(R.drawable.non_selected_border);
                    View tabView = host.getTabWidget().getChildAt(i);
                    TextView tv = tabView.findViewById(android.R.id.title);
                    tv.setTextColor(Color.WHITE);
                }

                host.getTabWidget().getChildAt(host.getCurrentTab())
                        .setBackgroundResource(R.drawable.selected_border);
                View tabView = host.getTabWidget().getChildAt(host.getCurrentTab());
                TextView tv = tabView.findViewById(android.R.id.title);
                tv.setTextColor(Color.rgb(113,141,255));
            }
        });
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
