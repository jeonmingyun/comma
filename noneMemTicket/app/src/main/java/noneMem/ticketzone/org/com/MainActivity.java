package noneMem.ticketzone.org.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button tel1;
    private Button tel2;
    private Button tel3;
    private Button tel4;
    private Button tel5;
    private Button tel6;
    private Button tel7;
    private Button tel8;
    private Button tel9;
    private Button del;
    private Button tel0;
    private Button send;
    private TextView teltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tel0 = findViewById(R.id.tel0);
        tel1 = findViewById(R.id.tel1);
        tel2 = findViewById(R.id.tel2);
        tel3 = findViewById(R.id.tel3);
        tel4 = findViewById(R.id.tel4);
        tel5 = findViewById(R.id.tel5);
        tel6 = findViewById(R.id.tel6);
        tel7 = findViewById(R.id.tel7);
        tel8 = findViewById(R.id.tel8);
        tel9 = findViewById(R.id.tel9);
        del = findViewById(R.id.del);
        send = findViewById(R.id.send);
        teltext = findViewById(R.id.teltext);

    }
}
