package ticketzone.org.com.app_mngr.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.JsonArrayTask;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;

public class StoreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private List<Entry> entries;
    private TextView store_name;
    private DBOpenHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_store);
        store_name = findViewById(R.id.store_name);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TabHost
        TabHost host=(TabHost)findViewById(R.id.store);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.ic_launcher_background, null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);
        Intent intent = getIntent();
        String s_name = intent.getExtras().getString("store_name");
        Cursor cursor = mDBHelper.selectStore(s_name);
        while(cursor.moveToNext()){
            store_name.setText(cursor.getString(8));

        }




        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.ic_launcher_foreground, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.test, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        lineChart = (LineChart)findViewById(R.id.chart);
    }

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



}