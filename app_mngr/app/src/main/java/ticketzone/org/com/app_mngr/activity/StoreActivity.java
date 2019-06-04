package ticketzone.org.com.app_mngr.activity;

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

public class StoreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private List<Entry> entries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

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

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.ic_launcher_foreground, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.test, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

        lineChart = (LineChart)findViewById(R.id.chart);
        JsonArrayTask jat = new JsonArrayTask("m_chart"){
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);
                try {

                    JSONArray jarr;
                    JSONObject jobj;
                    jarr = new JSONArray(jsonArray.get(0).toString());
                    for(int i=0; i<jarr.length(); i++){
                        Log.e("test", jarr.get(i).toString());
                        jobj = new JSONObject(jarr.get(i).toString());
                        entries = new ArrayList<>();
                        entries.add(new Entry(Integer.parseInt(jobj.getString("ticket_reg")),Integer.parseInt(jobj.getString("the_number"))));

                    }
                    LineDataSet lineDataSet = new LineDataSet(entries, "속성명1");
                    lineDataSet.setLineWidth(2);
                    lineDataSet.setCircleRadius(6);
                    lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));
                    lineDataSet.setCircleHoleColor(Color.BLUE);
                    lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));
                    lineDataSet.setDrawCircleHole(true);
                    lineDataSet.setDrawCircles(true);
                    lineDataSet.setDrawHorizontalHighlightIndicator(false);
                    lineDataSet.setDrawHighlightIndicators(false);
                    lineDataSet.setDrawValues(false);

                    LineData lineData = new LineData(lineDataSet);
                    lineChart.setData(lineData);

                    XAxis xAxis = lineChart.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    xAxis.setTextColor(Color.BLACK);

                    xAxis.enableGridDashedLine(8, 24, 0);

                    YAxis yLAxis = lineChart.getAxisLeft();
                    yLAxis.setTextColor(Color.BLACK);

                    YAxis yRAxis = lineChart.getAxisRight();
                    yRAxis.setDrawLabels(false);
                    yRAxis.setDrawAxisLine(false);
                    yRAxis.setDrawGridLines(false);

                    Description description = new Description();
                    description.setText("시");
                    description.setTextSize(20);

                    lineChart.setDoubleTapToZoomEnabled(false);
                    lineChart.setDrawGridBackground(false);
                    lineChart.setDescription(description);
                    lineChart.animateY(2000, Easing.EaseInCubic);
                    lineChart.invalidate();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
        jat.execute();






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