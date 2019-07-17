package noneMem.ticketzone.org.com;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

import noneMem.ticketzone.org.com.task.NetworkTask;
import noneMem.ticketzone.org.com.task.SendDataSet;

import static android.text.InputType.TYPE_CLASS_NUMBER;

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
    private TextView teltext, textview4;
    private String txt;

    private class CallButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String teltext_full = teltext.getText().toString();
            String teltext_arr[] = teltext_full.split("-"); // {010} {1234} {5648}

            if( teltext_arr.length == 1 && teltext_arr[0].length() == 3) { // tel 첫 3글자 입력
                teltext.append("-");
            }else if(teltext_arr.length == 2 && teltext_arr[1].length() == 4) { // tel 중간 4글자 입력
                teltext.append("-");
            }

            teltext.append(txt);

        }

        public void onClick() {
            String teltext_full = teltext.getText().toString();
            String teltext_arr[] = teltext_full.split("-"); // {010} {1234} {5648}

            if( teltext_arr.length == 1 && teltext_arr[0].length() == 3) { // tel 첫 3글자 입력
                teltext.append("-");
            }else if(teltext_arr.length == 2 && teltext_arr[1].length() == 4) { // tel 중간 4글자 입력
                teltext.append("-");
            }

            teltext.append(txt);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview4 = findViewById(R.id.textView4);
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

        final CallButtonClick m = new CallButtonClick();

        tel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel0.getText().toString(); // 0
                m.onClick();
            }
        });

        tel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel1.getText().toString();
                m.onClick();
            }
        });

        tel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel2.getText().toString();
                m.onClick();
            }
        });

        tel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel3.getText().toString();
                m.onClick();
            }
        });

        tel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel4.getText().toString();
                m.onClick();

            }
        });

        tel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel5.getText().toString();
                m.onClick();
            }
        });

        tel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel6.getText().toString();
                m.onClick();
            }
        });

        tel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel7.getText().toString();
                m.onClick();
            }
        });

        tel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel8.getText().toString();
                m.onClick();
            }
        });

        tel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt = tel9.getText().toString();
                m.onClick();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teltext_full = teltext.getText().toString();
                String teltext_arr[] = teltext_full.split("-"); // {010} {1234} {5648}

                if( !teltext_full.equals("")) {
                    if( teltext_arr.length == 2 && teltext_arr[1].length() == 1) { // tel 첫 3글자 뒤의 -지우기
                        teltext.setText(teltext_full.substring(0, teltext_full.length() - 2));
                    }else if(teltext_arr.length == 3 && teltext_arr[2].length() == 1) { // tel 중간 4글자 뒤의 -지우기
                        teltext.setText(teltext_full.substring(0, teltext_full.length() - 2));
                    } else {
                        teltext.setText(teltext_full.substring(0, teltext_full.length() - 1));
                    }
                }

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String teltext_full = teltext.getText().toString();
                final EditText ET = new EditText(MainActivity.this);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("인원 수 설정");
                ET.setInputType(TYPE_CLASS_NUMBER);
                dialog.setMessage("인원 수");
                dialog.setView(ET);

                if( teltext_full.length() == 13) {
                    // 확인 버튼 이벤트
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        teltext.setText("");
                        String textTxt = textview4.getText().toString().replaceAll("팀", "");
                        textview4.setText(Integer.parseInt(textTxt)+1 + "팀");
                        Toast.makeText(MainActivity.this, teltext_full + "님 번호표가 발급되었습니다.", Toast.LENGTH_SHORT).show();
                        NetworkTask net = new NetworkTask("nomemTicket"){
                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                Toast.makeText(MainActivity.this, "df"+s, Toast.LENGTH_SHORT).show();
                            }
                        };
                        SendDataSet sds1 = new SendDataSet("num",ET.getText().toString());
                        SendDataSet sds2 = new SendDataSet("phone_number", teltext_full);
                        net.execute(sds1);
                    }
                });
                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
                }
            }
        });

    }
}

