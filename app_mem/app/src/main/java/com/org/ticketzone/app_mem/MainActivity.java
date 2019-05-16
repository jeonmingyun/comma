package com.org.ticketzone.app_mem;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.org.ticketzone.app_mem.db.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ListView listview = null;
    private DBOpenHelper mDBHelper;
    private List storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar();
        tabHost();
    }

    // store list 생성
    protected void storeList() {
        Cursor cursor = mDBHelper.selectAllStore();
        storeList = new ArrayList();

        if(cursor.getCount() == 0) { // not found data
            Toast.makeText(this, "not found data", Toast.LENGTH_SHORT).show();
        }

        while(cursor.moveToNext()) {

        }
    }

    public void onClickLogout() {
        UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                redirectLoginActivity();
            }
        });
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    // tagHost 화면 Layout 바꿔끼우기
    protected void tabHost() {
        TabHost host=(TabHost)findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("store list");
        spec.setIndicator("store list");
        spec.setContent(R.id.store_list);
        host.addTab(spec);

        spec = host.newTabSpec("categorie");
        spec.setIndicator("categorie");
        spec.setContent(R.id.categorie);
        host.addTab(spec);
    }

    // menu toolbar
    protected void toolbar() {
        toolbar = findViewById(R.id.toolbar);
        dlDrawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);

        final String[] items = {"홈","logout"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        listview = findViewById(R.id.drawer);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position){
                    case 0:
                        Intent homeIntent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(homeIntent);
                        break;
                    case 1:
                        onClickLogout();
                        break;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        getMenuInflater().inflate(R.menu.main_navigation_menu, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);

        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.logout){
//            Intent logoutIntent = new Intent(this, LoginActivity.class);
//            startActivity(logoutIntent);
//        }
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
