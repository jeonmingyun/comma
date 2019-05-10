package com.org.ticketzone.app_mem.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.org.ticketzone.app_mem.R;
import com.org.ticketzone.app_mem.db.DBOpenHelper;

import java.util.ArrayList;

public class DBActivity  extends AppCompatActivity {

    private static final String TAG = "DBActivity";
    private DBOpenHelper mDBHelper;
    private Cursor cursor;
    private EditText et_tel, et_name, et_birth;
    private Button add_data, view_list, upd_data, del_data;
    private String tel, name, birth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        mDBHelper = new DBOpenHelper(this);

        et_tel = (EditText) findViewById(R.id.m_tel);
        et_name = (EditText) findViewById(R.id.m_name);
        et_birth = (EditText) findViewById(R.id.m_birth);
        add_data = (Button) findViewById(R.id.add_data);
        upd_data = (Button) findViewById(R.id.upd_data);
        del_data = (Button) findViewById(R.id.del_data);
        view_list = (Button) findViewById(R.id.view_list);

        insertMember();
        selectAllMember();
        updateMember();
        deleteMember();
    }

    public void showList(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void selectAllMember() {
        view_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = mDBHelper.selectAllMember();
                if(cursor.getCount() == 0) { // not found data
                    showList("error", "not found data");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(cursor.moveToNext()) { // found data
                    buffer.append("tel : " + cursor.getString(0)+"\n");
                    buffer.append("name : " + cursor.getString(1)+"\n");
                    buffer.append("birth : " + cursor.getString(2)+"\n\n");
                }

                showList("member", buffer.toString());
            }
        });
    }

    public void insertMember() {
        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel = et_tel.getText().toString();
                name = et_name.getText().toString();
                birth = et_birth.getText().toString();

                boolean result = mDBHelper.insertMember(tel, name, birth, null,null);

                if(result)
                    Toast.makeText(DBActivity.this, "success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DBActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMember() {
        upd_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel = et_tel.getText().toString();
                name = et_name.getText().toString();
                birth = et_birth.getText().toString();

                boolean result = mDBHelper.updateMember(tel,name,birth, null,null);

                if(result)
                    Toast.makeText(DBActivity.this, "success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DBActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteMember() {
        del_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tel = et_tel.getText().toString();

                boolean result = mDBHelper.deleteMember(tel);

                if(result)
                    Toast.makeText(DBActivity.this, "success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DBActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
