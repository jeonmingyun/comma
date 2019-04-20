package com.org.ticketzone.app_mem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btn_str = btn.getText().toString();
                NetworkTask networkTask = new NetworkTask("test") {
                    @Override
                    protected void onPostExecute(String data) {
                        super.onPostExecute(data);

                        if(data == "1") {

                        }
                    }
                }
            }
        });
    }
}
