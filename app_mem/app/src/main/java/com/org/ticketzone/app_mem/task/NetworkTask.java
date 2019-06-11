package com.org.ticketzone.app_mem.task;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkTask extends AsyncTask<SendDataSet, Void, String> {

//        String ip ="39.127.7.42"; //학교 IP번호
    private String ip = "15.164.115.73"; // 서버 IP
    private String path = "http://" + ip + ":8080/"; // 연결할 jsp주소

    protected NetworkTask(String url) {
        this.path += url;
    }

    @Override
    protected String doInBackground(SendDataSet... strings) {
        String data = "";
        JSONObject jobj = new JSONObject();

        try {
            for (int i = 0; i < strings.length; i++) {
                jobj.put(strings[i].key, strings[i].value);
            }

            /* 서버연결 */
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Accept", "text/html; charset=utf-8");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // xml내용을 전달하기 위해서 출력 스트림을 사용
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값  전달 */
            OutputStreamWriter owr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            owr.write(jobj.toString());
            owr.flush();
            owr.close();

            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader br = null;

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sBuff = new StringBuffer();
            String line = null;

            while ((line = br.readLine()) != null) {
                sBuff.append(line + "\n");
            }

            data = sBuff.toString().trim();
            is.close();
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }
}