package ticketzone.org.com.app_mngr;


import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.fragment.StoreItemAdapter;
import ticketzone.org.com.app_mngr.vo.StoreVO;

public class MainActivity extends AppCompatActivity {
//    private ActivityManager am = ActivityManager.getInstance();
    private DBOpenHelper mDBHelper;
    private Toolbar toolbar;
    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle dtToggle;
    private ListView listview = null;
    private ViewPager storeViewPager;
    private FragmentStatePagerAdapter storeAdapter;
    private ArrayList<StoreVO> storeList;
//    private FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBOpenHelper(this);
//        am.addActivity(this);

        selectAllStore();
        Log.e("storeList cnt", storeList.size()+"");
        //Store view pager
        storeViewPager = findViewById(R.id.store_view_pager);
        storeAdapter = new StoreItemAdapter(getSupportFragmentManager(), storeList);
        storeViewPager.setAdapter(storeAdapter);
//        adapterViewPager = new MyPagerAdater(getSupportFragmentManager());
//        vpPager.setAdapter(adapterViewPager);
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(storeViewPager);

        //menu toolbar
        toolbar = findViewById(R.id.toolbar);
        dlDrawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);

        final String[] items = {"홈", "지도", "logout"};
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
                        Intent logoutIntent = new Intent(v.getContext(), LoginActivity.class);
                        mDBHelper.deleteAllTable();
                        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        am.finishAllActivity();
                        startActivity(logoutIntent);
                        break;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
    }

    private void selectAllStore() {
        Cursor cursor = mDBHelper.selectAllStore();
        storeList = new ArrayList<>();
        StoreVO storeVO;

        if(cursor.getCount() == 0) {
            Toast.makeText(this, "no store data", Toast.LENGTH_SHORT).show();
        }

        while(cursor.moveToNext()) {
            storeVO = new StoreVO();
            Log.e("setStore_name", cursor.getString(8));
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

            storeList.add(storeVO);
        }

    }

    //스와이프
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
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent logoutIntent = new Intent(this, LoginActivity.class);
            startActivity(logoutIntent);
        }
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

