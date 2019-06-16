package ticketzone.org.com.app_mngr.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.NetworkTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.fragment.StoreItemAdapter;
import ticketzone.org.com.app_mngr.fragment.StoreItemFragment;
import ticketzone.org.com.app_mngr.vo.StoreVO;

public class MainActivity extends AppCompatActivity {
    private DBOpenHelper mDBHelper;
    private Toolbar toolbar;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private ListView listview = null;
    private ViewPager storeViewPager;
    private FragmentStatePagerAdapter storeAdapter;
    private ArrayList<String> storeList;
    private StoreVO storeVO;
    private TextView store_name;
    private Button success_btn;
    private Switch switchView;
    private ArrayList<String> license_number;
    private int viewPager_position = 0;
    private String member_id;
    private String toast_message;
//    private FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBOpenHelper(this);


        success_btn = findViewById(R.id.success_btn);
        selectAllStore();
        setStoreViewPager(); //Store view pager;

        success_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {
                    }

                    @Override
                    public void onPageSelected(int i) {
                        Log.e("view pager selected", storeViewPager.getCurrentItem()+"");

                        viewPager_position = i;

                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                Cursor cursor = mDBHelper.FCM_list(license_number.get(viewPager_position));
                while (cursor.moveToNext()){
                    member_id = cursor.getString(0);
                }
                NetworkTask networkTask = new NetworkTask("success_ticket"){
                };
                SendDataSet sds = new SendDataSet("license_number", license_number.get(viewPager_position));

                networkTask.execute(sds);
                mDBHelper.successTicket(license_number.get(viewPager_position));
                mDBHelper.successStatus(license_number.get(viewPager_position));
                Log.e("2",license_number.get(viewPager_position));
                Toast.makeText(MainActivity.this, "다음고객님을 호출했습니다.", Toast.LENGTH_SHORT).show();
                networkTask = new NetworkTask("/mem_send_fcm") {
                };

                SendDataSet sds2 = new SendDataSet("member_id", member_id);
                networkTask.execute(sds2,sds);

            }
        });

        //menu toolbar
        toolbar = findViewById(R.id.toolbar);
        dlDrawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);

        final String[] items = {"홈", "지도", "매장관리", "logout"};
        ArrayAdapter adater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        listview = findViewById(R.id.drawer);
        listview.setAdapter(adater);

        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent homeIntent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(homeIntent);
                        break;
                    case 1:
                        Intent mapIntent = new Intent(v.getContext(), MapsActivity2.class);
                        startActivity(mapIntent);
                        break;
                    case 2:
                        final List<String> ListItems = new ArrayList<>();
                        Cursor cursor = mDBHelper.selectAllStore();
                        while(cursor.moveToNext()){
                            ListItems.add(cursor.getString(8));
                        }
                        final String[] items2 = new String[ListItems.size()];
                        for(int i=0; i<ListItems.size(); i++){
                            items2[i] = ListItems.get(i);
                        }
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("매장");
                        alertDialogBuilder.setItems(items2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Intent storemanageintent = new Intent(MainActivity.this, StoreManageActivity.class);
                                storemanageintent.putExtra("store_name",items2[id]);
                                startActivity(storemanageintent);
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.show();
                        break;
                    case 3:
                        mDBHelper.deleteAllTable();
                        Intent logoutIntent = new Intent(v.getContext(), LoginActivity.class);
                        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(logoutIntent);
                        break;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });


    }



//    //스와이프
//    private static class MyPagerAdater extends FragmentPagerAdapter {
//        private static int NUM_ITEMS = 3;
//
//        public MyPagerAdater(FragmentManager fragmentManager) {
//            super(fragmentManager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return Frag1.newInstance(0, "Page # 1");
//                case 1:
//                    return Frag2.newInstance(1, "Page # 2");
//                case 2:
//                    return Frag3.newInstance(2, "Page # 3");
//                default:
//                    return null;
//            }
//        }
//
//        while(cursor.moveToNext()) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                storeVO = new StoreVO();
//                storeVO.setLicense_number(cursor.getString(0));
//                storeVO.setR_name(cursor.getString(1));
//                storeVO.setMax_number(cursor.getString(2));
//                storeVO.setStore_status(cursor.getInt(3));
//                storeVO.setCate_code(cursor.getString(4));
//                storeVO.setOwner_id(cursor.getString(5));
//                storeVO.setStore_name(cursor.getString(6));
//                storeVO.setStore_tel(cursor.getString(7));
//                storeVO.setStore_time(cursor.getString(8));
//                storeVO.setStore_intro(cursor.getString(9));
//                storeVO.setAddress_name(cursor.getString(10));
//
//                String storeJson = mapper.writeValueAsString(storeVO); // jackson : Object to Json
//                storeList.add(storeJson);
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        @Override
//        public int getCount() {
//            return NUM_ITEMS;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position){
//            return "Page " + position;
//        }
//    }


    private void selectAllStore() {
        Cursor cursor = mDBHelper.selectAllStore();
        StoreVO storeVO;
        storeList = new ArrayList<>();
        license_number = new ArrayList<>();
        int i =0;
        while(cursor.moveToNext()) {
            try {

                ObjectMapper mapper = new ObjectMapper();
                storeVO = new StoreVO();

                Log.e("setLicense_number", cursor.getString(0));
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

                Log.e("ia", i + "늘어남?");
                String storeJson = mapper.writeValueAsString(storeVO); // jackson : Object to Json
                Log.e("store", storeJson);
                license_number.add(cursor.getString(0));
                storeList.add(storeJson);
                i++;
//                storeList.add(storeVO);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setStoreViewPager() {
        storeViewPager = findViewById(R.id.store_view_pager);
        storeAdapter = new StoreItemAdapter(getSupportFragmentManager(), storeList);
        storeViewPager.setAdapter(storeAdapter);
        storeViewPager.setOffscreenPageLimit(6);
        storeViewPager.setPageMargin(70);
        storeViewPager.setPageTransformer(false, new ViewPager.PageTransformer(){

            @Override
            public void transformPage(View page, float position) {
                int pageWidth = storeViewPager.getMeasuredWidth() - storeViewPager.getPaddingLeft() - storeViewPager.getPaddingRight();
                int pageHeight = storeViewPager.getHeight();
                int paddingLeft = storeViewPager.getPaddingLeft();
                float transformPos = (float) (page.getLeft() - (storeViewPager.getScrollX() + paddingLeft)) / pageWidth;
                final float normalizedposition = Math.abs(Math.abs(transformPos) - 1);
                page.setAlpha(normalizedposition + 0.5f);

                if(transformPos < -1){
                    page.setTranslationY(0);
                }else if(transformPos <= 1){
                    page.setTranslationY(-pageHeight / 20);
                }else {
                    page.setTranslationY(0);
                }
            }
        });

        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(storeViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_navigation_menu, menu);
        return true;
}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.logout) {
//            Intent logoutIntent = new Intent(this, LoginActivity.class);
//            startActivity(logoutIntent);
//        }
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

