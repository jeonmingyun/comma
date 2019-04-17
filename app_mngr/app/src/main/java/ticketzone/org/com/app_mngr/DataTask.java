package ticketzone.org.com.app_mngr;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataTask extends AsyncTask<String, Void, String> {
    String ip ="39.127.7.42"; //학교 IP번호
    String path = "http://"+ip+"/"; // 연결할 jsp주소

    @Override
    protected String doInBackground(String... strings) {
        String param = "owner_id="+ strings[1] +"&owner_password="+ strings[2];
        String data ="";
        path += strings[0];

        try {
            /* 서버연결 */
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값  전달 */
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes("utf-8"));
            os.flush();
            os.close();

            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader br = null;

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is), 8*1024);
            StringBuffer sBuff = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                sBuff.append(line +"\n");
            }
            data = sBuff.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);

    }
}