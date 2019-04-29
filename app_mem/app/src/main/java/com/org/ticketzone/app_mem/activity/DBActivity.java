package com.org.ticketzone.app_mem.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;

import java.util.ArrayList;

public class DBActivity  extends AppCompatActivity {

    private static final String TAG = "DBActivity";
    private DBOpenHelper mDBOpenHelper;
    private Cursor cursor;
//    private InfoClass mInfoClass;
//    private ArrayList<infoclass> mInfoArray;
//    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);


    }
}
