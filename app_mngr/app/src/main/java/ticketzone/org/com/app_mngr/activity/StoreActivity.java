package ticketzone.org.com.app_mngr.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ticketzone.org.com.app_mngr.R;
import ticketzone.org.com.app_mngr.Task.JsonArrayTask;
import ticketzone.org.com.app_mngr.Task.SendDataSet;
import ticketzone.org.com.app_mngr.db.DBOpenHelper;
import ticketzone.org.com.app_mngr.vo.NumberTicketVO;

public class StoreActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private LineChart lineChart;
    private BarChart chart;
    private PieChart pieChart;
    private List<Entry> entries;
    private List<NumberTicketVO> waitList, absenceList;
    private TextView store_name, wait_count, t_wait, t_success, t_absence, t_cancel, s_date;
    private DBOpenHelper mDBHelper;
    private ImageView storeimg;
    private String license_number = "";
    private ImageButton prev, next;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBHelper = new DBOpenHelper(this);
        setContentView(R.layout.activity_store);
        store_name = findViewById(R.id.store_name);
        storeimg = findViewById(R.id.storeimg);
        wait_count = findViewById(R.id.wait_count);
        t_wait = findViewById(R.id.t_wait);
        t_absence = findViewById(R.id.t_absence);
        t_cancel = findViewById(R.id.t_cancel);
        t_success = findViewById(R.id.t_success);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        s_date = findViewById(R.id.s_date);

        mSwipeRefreshLayout = findViewById(R.id.swipe_layout);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x00FFFFFF));
        getSupportActionBar().setTitle("번호요");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TabHost
        final TabHost host=(TabHost)findViewById(R.id.store);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator("메인");
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator("대기 현황");
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec = host.newTabSpec("tab3");
        spec.setIndicator("통계");
        spec.setContent(R.id.tab_content3);
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


        /*이미지 불러오기*/
        Intent intent = getIntent();
        String s_name = intent.getExtras().getString("store_name");
        String url = "http://15.164.115.73:8080/resources/img/";

        /*이미지 적용*/
        Cursor cursor = mDBHelper.selectStore(s_name);
        while(cursor.moveToNext()){
            store_name.setText(cursor.getString(8));
            license_number = cursor.getString(0);
            Glide.with(this).load(url + cursor.getString(11) + "/" + cursor.getString(10) + "_" + cursor.getString(12)).centerCrop().into(storeimg);
        }
        /*대기인원 확인*/
        Cursor cursor1 = mDBHelper.selectWating(license_number);
        while(cursor1.moveToNext()){
            wait_count.setText(cursor1.getString(0));
        }
        /*테이블 채우는 작업*/
        Cursor ticket_wait = mDBHelper.t_wait(license_number);
        ticket_wait.moveToNext();
        t_wait.setText(ticket_wait.getString(0));

        Cursor ticket_success = mDBHelper.t_success(license_number);
        ticket_success.moveToNext();
        t_success.setText(ticket_success.getString(0));

        Cursor ticket_absence = mDBHelper.t_absence(license_number);
        ticket_absence.moveToNext();
        t_absence.setText(ticket_absence.getString(0));

        Cursor ticket_cancel = mDBHelper.t_cancel(license_number);
        ticket_cancel.moveToNext();
        t_cancel.setText(ticket_cancel.getString(0));

        lineChart = findViewById(R.id.lineChart);
        /* 라인차트 */
        SimpleDateFormat format2 = new SimpleDateFormat ( "yyyy/MM/dd");
        Date time = new Date();
        String time2 = format2.format(time);
        s_date.setText(time2);
        entries = new ArrayList<>();
        final String a_date = s_date.getText().toString().replaceAll("/","").substring(0,8);
        entries.add(new Entry(12, 14));
        entries.add(new Entry(14, 20));
        entries.add(new Entry(16, 12));
        entries.add(new Entry(18, 18));
        entries.add(new Entry(20, 14));
        entries.add(new Entry(22, 8));
//        }
        IAxisValueFormatter xformater = new IAxisValueFormatter() {
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

        chart = (BarChart) findViewById(R.id.chart);

        ArrayList<BarEntry> bargroup1 = new ArrayList<>();
            bargroup1.add(new BarEntry(0f, 8f));
            bargroup1.add(new BarEntry(1f, 2f));
            bargroup1.add(new BarEntry(2f, 5f));
            bargroup1.add(new BarEntry(3f, 20f));
            bargroup1.add(new BarEntry(4f, 15f));
            bargroup1.add(new BarEntry(5f, 19f));

        ArrayList<BarEntry> bargroup2 = new ArrayList<>();
        bargroup2.add(new BarEntry(0f, 6f));
        bargroup2.add(new BarEntry(1f, 10f));
        bargroup2.add(new BarEntry(2f, 5f));
        bargroup2.add(new BarEntry(3f, 25f));
        bargroup2.add(new BarEntry(4f, 4f));
        bargroup2.add(new BarEntry(5f, 17f));

        BarDataSet barDataSet1 = new BarDataSet(bargroup1,"Bar Group 1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet2 = new BarDataSet(bargroup2,"Bar Group 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);


        final ArrayList<String> labels = new ArrayList<String>();
            labels.add("2011");
            labels.add("2012");
            labels.add("2013");
            labels.add("2014");
            labels.add("2015");
            labels.add("2016");


        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(barDataSet1);
            dataSets.add(barDataSet2);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels.get((int) value);
            }
        });
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(13.0f);
        xAxis.setValueFormatter(xformater);
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
                entries.add(new Entry(12, 14-2));
                entries.add(new Entry(14, 20-4));
                entries.add(new Entry(16, 12-2));
                entries.add(new Entry(18, 18-2));
                entries.add(new Entry(20, 14-4));
                entries.add(new Entry(22, 8+4));
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

                s_date.setText(Integer.toString(c_date).substring(0,4) + "/" + Integer.toString(c_date).substring(4,6) + "/" +  Integer.toString(c_date).substring(6,8));
            }
        });
        //증가
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                entries.removeAll(entries);
                int c_date = Integer.parseInt(s_date.getText().toString().replaceAll("/","").substring(0,8)) +1;
                entries.add(new Entry(12, 14+2));
                entries.add(new Entry(14, 20+4));
                entries.add(new Entry(16, 12+2));
                entries.add(new Entry(18, 18+2));
                entries.add(new Entry(20, 14+4));
                entries.add(new Entry(22, 8-4));
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

                s_date.setText(Integer.toString(c_date).substring(0,4) + "/" + Integer.toString(c_date).substring(4,6) + "/" +  Integer.toString(c_date).substring(6,8));
            }
        });

        BarData data2 = new BarData(barDataSet1, barDataSet2);
        data2.setBarWidth(0.9f); // set custom bar width
        chart.setData(data2);
        chart.getLegend().setEnabled(false);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh

        pieChart = findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(34f,"dd"));
        yValues.add(new PieEntry(23f,"dw"));
        yValues.add(new PieEntry(14f,"de"));
        yValues.add(new PieEntry(35f,"ds"));
        yValues.add(new PieEntry(40f,"da"));
        yValues.add(new PieEntry(40f,"df"));

        Description description = new Description();
        description.setText("네넵");
        description.setTextSize(15);
        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "ddddd");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data1 = new PieData((dataSet));
        data1.setValueTextSize(10f);
        data1.setValueTextColor(Color.YELLOW);

        pieChart.setData(data1);


        waitList(); // 고객 대기현황 리스트 가져오기
        absenceList(); // 번호표 취소, 부재 고객 리스트 가져오기
        waitingTable(); // 고객 대기현황 표 생성
        absenceTable(); // 고객 대기현황 표 생성

    }

    private void absenceList() {
        Cursor cursor = mDBHelper.absence_list(license_number);
        NumberTicketVO numberTicketVO;
        absenceList = new ArrayList<>();

        while (cursor.moveToNext()) {
            numberTicketVO = new NumberTicketVO();
            numberTicketVO.setTicket_code(cursor.getString(0));
            numberTicketVO.setWait_number(cursor.getInt(1));
            numberTicketVO.setMember_id(cursor.getString(2));
            numberTicketVO.setThe_number(cursor.getInt(3));
            numberTicketVO.setTicket_status(cursor.getString(4));

            absenceList.add(numberTicketVO);
        }
    }

    private void waitList() {
        Cursor cursor = mDBHelper.wait_list(license_number);
        NumberTicketVO numberTicketVO;
        waitList = new ArrayList<>();

        while (cursor.moveToNext()) {
            numberTicketVO = new NumberTicketVO();
            numberTicketVO.setTicket_code(cursor.getString(0));
            numberTicketVO.setWait_number(cursor.getInt(1));
            numberTicketVO.setMember_id(cursor.getString(2));
            numberTicketVO.setThe_number(cursor.getInt(3));
            numberTicketVO.setTicket_status(cursor.getString(4));

            waitList.add(numberTicketVO);
        }
    }

    private void absenceTable() {
        TableLayout tableLayout = findViewById(R.id.absence_table);
        TableRow tableRow;
        TextView wait_num, customer, count, status;

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ); // table row

        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.MATCH_PARENT
        ); // table cell
        cellParams.rightMargin=1;

        for ( int i = 0; i < absenceList.size(); i++) {
            tableRow = new TableRow(this);
            wait_num = new TextView(this);
            customer = new TextView(this);
            count = new TextView(this);
            status = new TextView(this);

            tableRow.setLayoutParams(tableRowParams);
            wait_num.setLayoutParams(cellParams);
            customer.setLayoutParams(cellParams);
            count.setLayoutParams(cellParams);
            status.setLayoutParams(cellParams);

            tableRow.setPadding(1,1,1,1);

            wait_num.setBackgroundColor(Color.WHITE);
            wait_num.setGravity(Gravity.CENTER);
            customer.setBackgroundColor(Color.WHITE);
            customer.setGravity(Gravity.CENTER);
            count.setBackgroundColor(Color.WHITE);
            count.setGravity(Gravity.CENTER);
            status.setBackgroundColor(Color.WHITE);
            status.setGravity(Gravity.CENTER);
            Log.e("ddd", absenceList.get(i)+"");

            wait_num.setText(absenceList.get(i).getTicket_code());
            customer.setText(absenceList.get(i).getMember_id());
            count.setText(absenceList.get(i).getThe_number()+"명");
            String string_status = "";
            if(absenceList.get(i).getTicket_status().equals("2")){
                string_status = "발급취소";
            } else if(absenceList.get(i).getTicket_status().equals("3")){
                string_status = "부재";
            }
            status.setText(string_status);

            tableRow.addView(wait_num);
            tableRow.addView(customer);
            tableRow.addView(count);
            tableRow.addView(status);

            tableLayout.addView(tableRow);
        }
    }

    private void waitingTable() {
        TableLayout tableLayout = findViewById(R.id.waiting_table);
        TableRow tableRow;
        TextView wait_num, customer, count, status;

        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ); // table row

        TableRow.LayoutParams cellParams = new TableRow.LayoutParams(
                0,
                TableRow.LayoutParams.MATCH_PARENT
        ); // table cell
        cellParams.rightMargin=1;

        for ( int i = 0; i < waitList.size(); i++) {
            tableRow = new TableRow(this);
            wait_num = new TextView(this);
            customer = new TextView(this);
            count = new TextView(this);
            status = new TextView(this);

            tableRow.setLayoutParams(tableRowParams);
            wait_num.setLayoutParams(cellParams);
            customer.setLayoutParams(cellParams);
            count.setLayoutParams(cellParams);
            status.setLayoutParams(cellParams);

            tableRow.setPadding(1,1,1,1);

            wait_num.setBackgroundColor(Color.WHITE);
            wait_num.setGravity(Gravity.CENTER);
            customer.setBackgroundColor(Color.WHITE);
            customer.setGravity(Gravity.CENTER);
            count.setBackgroundColor(Color.WHITE);
            count.setGravity(Gravity.CENTER);
            status.setBackgroundColor(Color.WHITE);
            status.setGravity(Gravity.CENTER);
            Log.e("ddd", waitList.get(i)+"");

            String ticket_status = "";
            wait_num.setText(waitList.get(i).getTicket_code());
            customer.setText(waitList.get(i).getMember_id());
            count.setText(waitList.get(i).getThe_number()+"명");
            if(waitList.get(i).getTicket_status().equals("0") || waitList.get(i).getTicket_status().equals("4")){
                ticket_status = "대기";
            }
            status.setText(ticket_status);

            tableRow.addView(wait_num);
            tableRow.addView(customer);
            tableRow.addView(count);
            tableRow.addView(status);

            tableLayout.addView(tableRow);
        }

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

    @Override
    public void onRefresh() {
        mDBHelper.refesh_ticket(license_number);
        JsonArrayTask jTask = new JsonArrayTask("wait_refresh") {
            @Override
            protected void onPostExecute(JSONArray jsonArray) {
                super.onPostExecute(jsonArray);
                try {
                    mDBHelper.insertTicket(new JSONArray(jsonArray.get(0).toString()));

                    /*대기인원 확인*/
                    Cursor cursor1 = mDBHelper.selectWating(license_number);
                    while (cursor1.moveToNext()) {
                        wait_count.setText(cursor1.getString(0));
                    }
                    Cursor ticket_wait = mDBHelper.t_wait(license_number);
                    ticket_wait.moveToNext();
                    t_wait.setText(ticket_wait.getString(0));

                    Cursor ticket_success = mDBHelper.t_success(license_number);
                    ticket_success.moveToNext();
                    t_success.setText(ticket_success.getString(0));

                    Cursor ticket_absence = mDBHelper.t_absence(license_number);
                    ticket_absence.moveToNext();
                    t_absence.setText(ticket_absence.getString(0));

                    Cursor ticket_cancel = mDBHelper.t_cancel(license_number);
                    ticket_cancel.moveToNext();
                    t_cancel.setText(ticket_cancel.getString(0));
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SendDataSet sds = new SendDataSet("license_number", license_number);
        jTask.execute(sds);

        mSwipeRefreshLayout.setRefreshing(false);
    }
}