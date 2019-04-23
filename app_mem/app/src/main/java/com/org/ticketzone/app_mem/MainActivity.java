package com.org.ticketzone.app_mem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText request, response;
    Button submit;
    String s_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = (EditText)findViewById(R.id.request);
        response = (EditText)findViewById(R.id.response);
        submit = (Button)findViewById(R.id.submits);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_request = request.getText().toString();

                Log.e("1", s_request);
                NetworkTask nt = new NetworkTask("test") {
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        response.setText(s);
                    }
                };

                SendDataSet sds = new SendDataSet("owner_id", s_request);
                nt.execute(sds);
            }
        });
    }
}
