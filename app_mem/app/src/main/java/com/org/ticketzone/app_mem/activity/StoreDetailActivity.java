package com.org.ticketzone.app_mem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuAdapter;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuItem;
import com.org.ticketzone.app_mem.expandableRecyclerview.MenuTitle;
import com.org.ticketzone.app_mem.task.NetworkTask;
import com.org.ticketzone.app_mem.task.SendDataSet;
import com.org.ticketzone.app_mem.vo.BeaconVO;
import com.org.ticketzone.app_mem.vo.StoreMenuVO;
import com.org.ticketzone.app_mem.vo.StoreVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class StoreDetailActivity extends AppCompatActivity {

    private TextView store_name, store_tel, store_time, address_name, store_intro, s_date;
    private ImageView store_img;
    private Button issue_btn;
    private ImageButton prev, next;
    private DBOpenHelper mDBHelper;
    private StoreVO storeVO;
    private ArrayList<StoreMenuVO> menuList;
    private ArrayList<BeaconVO> beaconList;
    private String license_number;
    private int getWidth =0;
    private int getHeight =0;
    private Date AddDate;
    private Date DelDate;

    // 비콘
    private BeaconManager beaconManager;
    private BeaconRegion region;
    private boolean isConnected;
    private int connect = 1;
    private int connect2=2;
    private String Minor = "";

    //chart
    private LineChart lineChart;
    private List<Entry> entries;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWidth = store_img.getWidth();
        getHeight = store_img.getHeight();
    }

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
        AddDate = new Date();
        DelDate = new Date();
        prev = findViewById(R.id.prev);
        s_date = findViewById(R.id.s_date);
        next = findViewById(R.id.next);
        beaconConnection();
        String imageUrl;

        Intent intent = getIntent();
        license_number = intent.getExtras().getString("license_number");

        tabHost();
        selectStore(license_number);
        selectStoreMenu(license_number);
        setStoreDetail();
        setMenuList();
        selectAllBeacon(); // beaconList

        Log.e("menu", menuList.toString());

        //서버 이미지 불러오기
        imageUrl = "http://15.164.115.73:8080/resources/img/" + storeVO.getImg_uploadpath() + "/" + storeVO.getImg_uuid() + "_" + storeVO.getImg_filename();
        Glide.with(this).load(imageUrl).centerCrop().into(store_img);
        store_name.setText(storeVO.getStore_name());
//        store_img.setImageURI();

        issue_btn.setText("발급불가");
        issue_btn.setEnabled(false);






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
        Map<String,ArrayList<MenuItem>> menuHash = new HashMap<>();
        Set set;// hashMap key 가져오기
        Iterator iterator;

        for (int i = 0; i < menuList.size(); i++) {
            String[] categorie;
            storeMenuVO = menuList.get(i);
            categorie = storeMenuVO.getMenu_name().split("-");
//            Log.e("ddd vo", storeMenuVO.toString());

            if( menuHash.get(categorie[0]) == null) {
                menuItems = new ArrayList<>();
                menuHash.put(categorie[0], menuItems); // key : store license number, value : store menu items
            }

            menuItems = menuHash.get(categorie[0]);
            menuItems.add(new MenuItem(storeMenuVO));// store menu item 추가
            menuHash.put(categorie[0], menuItems);

        }

        set = menuHash.keySet();// menuHash key 가져오기
        iterator = set.iterator();

        while(iterator.hasNext()) {
            String key = (String) iterator.next();
            menuTitle = new MenuTitle(key, menuHash.get(key));
            menuTitles.add(menuTitle);
        }

        MenuAdapter adapter = new MenuAdapter(menuTitles);
        recyclerView.setAdapter(adapter);
    }

    private void  setStoreDetail() {
        store_tel.setText(storeVO.getStore_tel());
        store_time.setText(storeVO.getStore_time());
        address_name.setText(storeVO.getAddress_name());
        store_intro.setText(storeVO.getStore_intro());
    }
    private void selectAllBeacon(){
        Cursor cursor = mDBHelper.selectAllBeacon();
        beaconList = new ArrayList<>();
        BeaconVO beaconVO;

        while (cursor.moveToNext()){
            beaconVO = new BeaconVO();
            beaconVO.setB_code(cursor.getString(0));
            beaconVO.setStore_name(cursor.getString(1));
            beaconVO.setLicense_number(cursor.getString(2));

            beaconList.add(beaconVO);
        }

    }

    // tagHost 화면 Layout 바꿔끼우기
    private void tabHost() {
        final TabHost host= findViewById(R.id.host);
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


        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy/MM/dd(E)");
        Date time = new Date();
        String time2 = format2.format(time);
        s_date.setText(time2);
        lineChart = findViewById(R.id.chart);

        entries = new ArrayList<>();
        final String a_date = s_date.getText().toString().replaceAll("/","").substring(0,8);

        Cursor cursor = mDBHelper.ChartTicket(a_date);
        while(cursor.moveToNext()){
            entries.add(new Entry(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1))));
        }
        IAxisValueFormatter xformatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int)value + "시";
            }
        };
        IAxisValueFormatter yformatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int)value + "명";
            }
        };
        IValueFormatter iValueFormatter = new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int)value + "명";
            }
        };
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(13.0f);
        xAxis.setValueFormatter(xformatter);
        YAxis yAxisRight = lineChart.getAxisRight();
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        yAxisLeft.setTextSize(13.0f);
        yAxisLeft.setValueFormatter(yformatter);
        LineDataSet dataset = new LineDataSet(entries, "명");
        dataset.setValueFormatter(iValueFormatter);
        LineData data = new LineData(dataset);
        dataset.setColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
        dataset.setCircleColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
        dataset.setValueTextSize(10.0f);
        dataset.setLineWidth(3);
        lineChart.setData(data);
        lineChart.animateY(1000);
        //감소
        prev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                entries.removeAll(entries);
                int c_date = Integer.parseInt(s_date.getText().toString().replaceAll("/","").substring(0,8)) -1;
                Cursor cursor2 = mDBHelper.ChartTicket(Integer.toString(c_date));
                while(cursor2.moveToNext()){
                    entries.add(new Entry(Integer.parseInt(cursor2.getString(0)), Integer.parseInt(cursor2.getString(1))));
                }
                IAxisValueFormatter xformatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return (int)value + "시";
                    }
                };
                IAxisValueFormatter yformatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return (int)value + "명";
                    }
                };
                IValueFormatter iValueFormatter = new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return (int)value + "명";
                    }
                };
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextSize(13.0f);
                xAxis.setValueFormatter(xformatter);
                YAxis yAxisRight = lineChart.getAxisRight();
                YAxis yAxisLeft = lineChart.getAxisLeft();
                yAxisRight.setDrawLabels(false);
                yAxisRight.setDrawAxisLine(false);
                yAxisRight.setDrawGridLines(false);
                yAxisLeft.setTextSize(13.0f);
                yAxisLeft.setValueFormatter(yformatter);
                LineDataSet dataset = new LineDataSet(entries, "명");
                dataset.setValueFormatter(iValueFormatter);
                LineData data = new LineData(dataset);
                dataset.setColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
                dataset.setCircleColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
                dataset.setValueTextSize(10.0f);
                dataset.setLineWidth(3);

                lineChart.setData(data);
                lineChart.animateY(1000);
                SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy/MM/dd(E)");
                Calendar cal = Calendar.getInstance();
                cal.setTime(DelDate);
                cal.add(Calendar.DATE, -1);
                String time1 = format1.format(cal.getTime());
                s_date.setText(Integer.toString(c_date).substring(0,4) + "/" + Integer.toString(c_date).substring(4,6) + "/" +  Integer.toString(c_date).substring(6,8) + time1.substring(10));
            }
        });
        //증가
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                entries.removeAll(entries);
                int c_date = Integer.parseInt(s_date.getText().toString().replaceAll("/","").substring(0,8)) +1;
                Cursor cursor2 = mDBHelper.ChartTicket(Integer.toString(c_date));
                while(cursor2.moveToNext()){
                    entries.add(new Entry(Integer.parseInt(cursor2.getString(0)), Integer.parseInt(cursor2.getString(1))));
                }
                IAxisValueFormatter xformatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return (int)value + "시";
                    }
                };
                IAxisValueFormatter yformatter = new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return (int)value + "명";
                    }
                };
                IValueFormatter iValueFormatter = new IValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                        return (int)value + "명";
                    }
                };
                XAxis xAxis = lineChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextSize(13.0f);
                xAxis.setValueFormatter(xformatter);
                YAxis yAxisRight = lineChart.getAxisRight();
                YAxis yAxisLeft = lineChart.getAxisLeft();
                yAxisRight.setDrawLabels(false);
                yAxisRight.setDrawAxisLine(false);
                yAxisRight.setDrawGridLines(false);
                yAxisLeft.setTextSize(13.0f);
                yAxisLeft.setValueFormatter(yformatter);
                LineDataSet dataset = new LineDataSet(entries, "명");
                dataset.setValueFormatter(iValueFormatter);
                LineData data = new LineData(dataset);
                dataset.setColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
                dataset.setCircleColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
                dataset.setValueTextSize(10.0f);
                dataset.setLineWidth(3);
                lineChart.setData(data);
                lineChart.animateY(1000);
                SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy/MM/dd(E)");
                Calendar cal = Calendar.getInstance();
                cal.setTime(AddDate);
                cal.add(Calendar.DATE, +1);
                String time1 = format1.format(cal.getTime());
                s_date.setText(Integer.toString(c_date).substring(0,4) + "/" + Integer.toString(c_date).substring(4,6) + "/" +  Integer.toString(c_date).substring(6,8) + time1.substring(10));
            }
        });



        // TabWidet의 background 설정
        for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
            View tabView = host.getTabWidget().getChildAt(i);

            tabView.setBackgroundResource(R.drawable.non_selected_border); // unselected
            tabView.getLayoutParams().height = 150;
        }
        host.getTabWidget().getChildAt(host.getCurrentTab())
                .setBackgroundResource(R.drawable.selected_border); // selected

        // TabWidet tab 클릭시 background 설정
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
                    host.getTabWidget().getChildAt(i)
                            .setBackgroundResource(R.drawable.non_selected_border); // unselected
                }
                host.getTabWidget().getChildAt(host.getCurrentTab())
                        .setBackgroundResource(R.drawable.selected_border); // selected

            }
        });
    }

    private void beaconConnection() {
        // 비콘
        // 비콘의 수신 범위를 갱신 받음
        beaconManager = new BeaconManager(StoreDetailActivity.this);
        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Beacon nearestBeacon = list.get(0);
                    Log.e("Airport3", "Nearest places: " + nearestBeacon.getRssi());


                    if (nearestBeacon.getRssi() >  -90) {
                        Toast.makeText(StoreDetailActivity.this, "버튼활성화", Toast.LENGTH_LONG);
                        //tagBtn.setEnabled(true);
                        Log.e("aaa", "Aaaaa");
                        connect = nearestBeacon.getMinor();
                        Minor = String.valueOf(connect);
                        String B_name[] =  new String[beaconList.size()];
                        String B_id[] = new String[beaconList.size()];

                        for(int i =0; i<beaconList.size(); i++){
                            B_name[i] = beaconList.get(i).getStore_name();
                            B_id[i] = beaconList.get(i).getB_code().substring(41);

                            if(B_name[i].equals(store_name.getText().toString()) && Minor.equals(B_id[i])){
                                issue_btn.setText("발급가능");
                                issue_btn.setEnabled(true);
                            }
                        }

                    } else if (nearestBeacon.getRssi() < -90) {
                        Toast.makeText(StoreDetailActivity.this, "연결이 끊어졌습니다.", Toast.LENGTH_SHORT);
                        connect = 0;
                    }
                }

            }

        });


        region = new BeaconRegion("ranged region",
                UUID.fromString("74278bda-b644-4520-8f0c-720eaf059935"), 40001, 15383);
        //비콘 //
    }

    @Override
    protected void onResume(){
        super.onResume();

        //블루투스 권환 승낙 및 블루투스 활성화
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });

    }

    @Override
    protected void onPause(){
        //beaconManager.stopRanging(region);
        super.onPause();
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
        storeVO.setImg_uuid(cursor.getString(10));
        storeVO.setImg_uploadpath(cursor.getString(11));
        storeVO.setImg_filename(cursor.getString(12));
        storeVO.setAddress_name(cursor.getString(13));
    }
}