package ticketzone.org.com.app_mngr;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.vo.StoreVO;

public class StoreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private DBOpenHelper mDBHelper;
    private ActivityManager am = ActivityManager.getInstance();
    Button issue_ticket;
    EditText et_member_tel, et_the_number;
    String member_tel, the_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        am.addActivity(this);
        setContentView(R.layout.activity_store);
        mDBHelper = new DBOpenHelper(this);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabHost host=(TabHost)findViewById(R.id.store);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.ic_launcher_background, null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);
//        Intent intent = getIntent();
//        String name = intent.getExtras().getString("store_name");
//        TextView store_name = (TextView)findViewById(R.id.store_name);
//        store_name.setText(name);
        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.ic_launcher_foreground, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.test, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        et_member_tel = (EditText) findViewById(R.id.member_tel);
        et_the_number = (EditText) findViewById(R.id.the_number);
        issue_ticket = (Button)findViewById(R.id.issue_ticket);

        issue_ticket.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                member_tel = et_member_tel.getText().toString();
                the_number = et_the_number.getText().toString();

                NetworkTask networkTask = new NetworkTask("M_issue_ticket"){

                };
                SendDataSet sds1 = new SendDataSet("member_tel", member_tel);
                SendDataSet sds2 = new SendDataSet("the_number", the_number);
                networkTask.execute(sds1,sds2);

            }
        });


//        lineChart = (LineChart)findViewById(R.id.chart);
//
//        List<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(2,1));
//        entries.add(new Entry(3,2));
//        entries.add(new Entry(4,0));
//        entries.add(new Entry(5,4));
//        entries.add(new Entry(6,3));
//
//        LineDataSet lineDataSet = new LineDataSet(entries, "속성명1");
//        lineDataSet.setLineWidth(2);;
//        lineDataSet.setCircleRadius(6);
//        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
//        lineDataSet.setCircleHoleColor(Color.BLUE);
//        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
//        lineDataSet.setDrawCircleHole(true);
//        lineDataSet.setDrawCircles(true);
//        lineDataSet.setDrawHorizontalHighlightIndicator(false);
//        lineDataSet.setDrawHighlightIndicators(false);
//        lineDataSet.setDrawValues(false);
//
//        LineData lineData = new LineData(lineDataSet);
//        lineChart.setData(lineData);
//
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextColor(Color.BLACK);
//        xAxis.enableGridDashedLine(8, 24, 0);
//
//        YAxis yLAxis = lineChart.getAxisLeft();
//        yLAxis.setTextColor(Color.BLACK);
//
//        YAxis yRAxis = lineChart.getAxisRight();
//        yRAxis.setDrawLabels(false);
//        yRAxis.setDrawAxisLine(false);
//        yRAxis.setDrawGridLines(false);
//
//        Description description = new Description();
//        description.setText("");
//
//        lineChart.setDoubleTapToZoomEnabled(false);
//        lineChart.setDrawGridBackground(false);
//        lineChart.setDescription(description);
//        lineChart.animateY(2000, Easing.EaseInCubic);
//        lineChart.invalidate();
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
