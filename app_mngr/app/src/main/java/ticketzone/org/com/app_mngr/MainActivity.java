package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        dlDrawer = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.addDrawerListener(dtToggle);
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

