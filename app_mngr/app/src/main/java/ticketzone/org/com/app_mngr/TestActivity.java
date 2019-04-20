package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

public class TestActivity extends AppCompatActivity {

    EditText id, pass, result;
    Button submit;
    String sid, spass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        id = (EditText) findViewById(R.id.id);
        pass = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sid = id.getText().toString();
                spass = pass.getText().toString();

                JsonArrayTask jaTask = new JsonArrayTask("json_test") {
                    @Override
                    protected void onPostExecute(JSONArray data) {
                        super.onPostExecute(data);

                        if( data != null) { // login success
                            Toast.makeText(TestActivity.this, "success"+data, Toast.LENGTH_LONG).show();
                        }  else { // id wrong
                            Toast.makeText(TestActivity.this, "fail"+data, Toast.LENGTH_LONG).show();
                        }
                    }
                };

                SendDataSet sds1 = new SendDataSet("owner_id", sid);
                SendDataSet sds2 = new SendDataSet("owner_password", spass);

                jaTask.execute(sds1, sds2);            }
        });

    }
}
