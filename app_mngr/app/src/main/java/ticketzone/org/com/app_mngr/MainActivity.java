package ticketzone.org.com.app_mngr;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.security.MessageDigest;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ListView listview = null;

    Button bt1,bt2,bt3;
    FragmentManager fm;
    FragmentTransaction tran;
    Frag1 frag1;
    Frag2 frag2;
    Frag3 frag3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        setFrag(0);

//        //owner_id값 수신
//        TextView tx1 = findViewById(R.id.textView1);
//        Intent intent = getIntent();
//        String id = intent.getExtras().getString("id");
//        tx1.setText(id);

        //menu toolbar
        toolbar = findViewById(R.id.toolbar);
        dlDrawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);

        final String[] items = {"홈","지도","logout"};
        ArrayAdapter adater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        listview = findViewById(R.id.drawer);
        listview.setAdapter(adater);

        listview.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                switch (position){
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
                        startActivity(logoutIntent);
                        break;
                }

                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.bt1:
                setFrag(0);
                break;
            case R.id.bt2:
                setFrag(1);
                break;
            case  R.id.bt3:
                setFrag(2);
                break;
        }
    }

    public void setFrag(int n) {
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        switch (n){
            case 0:
                tran.replace(R.id.main_frame, frag1);
                tran.commit();
                break;
            case 1:
                tran.replace(R.id.main_frame, frag2);
                tran.commit();
                break;
            case 2:
                tran.replace(R.id.main_frame, frag3);
                tran.commit();
                break;
        }
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
        int id = item.getItemId();

        if(id == R.id.logout){
            Intent logoutIntent = new Intent(this, LoginActivity.class);
            startActivity(logoutIntent);
        }
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        getSupportActionBar().setTitle("번호요");
//        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        getSupportActionBar().setLogo(R.drawable.ic_launcher_foreground);
////        getSupportActionBar().setDisplayShowHomeEnabled(true);
////        getSupportActionBar().setDisplayUseLogoEnabled(true);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.main_navigation_menu, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//
//        if(id == android.R.id.home){
//            Intent homeIntent = new Intent(this, MainActivity.class);
//            startActivity(homeIntent);
//        }
//
//        if(id == R.id.app_bar_search){
//            Toast.makeText(this, "검색 클릭", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//
//        if(id == R.id.logout){
//            Intent logoutIntent = new Intent(this, LoginActivity.class);
//            startActivity(logoutIntent);
//        }
//        return super.onOptionsItemSelected(item);
//    }

