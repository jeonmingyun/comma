package com.org.ticketzone.app_mem.task;

import android.os.AsyncTask;

import org.json.JSONArray;
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

public class JsonArrayTask extends AsyncTask<SendDataSet, Void, JSONArray> {

    private String ip = "15.164.115.73"; // 서버 IP
//    private String ip = "39.127.7.41";
    String path = "http://"+ip+":8080/"; // 연결할 jsp주소

    protected JsonArrayTask(String url) { this.path +=url; }

    @Override
    protected JSONArray doInBackground(SendDataSet... strings) {
        JSONArray data = new  JSONArray();
        JSONObject jobj = new JSONObject();

        try {
            for ( int i = 0; i < strings.length; i++) {
                jobj.put(strings[i].key, strings[i].value);
            }

            /* 서버연결 */
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json; utf-8");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // xml내용을 전달하기 위해서 출력 스트림을 사용
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값  전달 */
            OutputStreamWriter owr = new OutputStreamWriter(conn.getOutputStream());
            owr.write(jobj.toString());
            owr.flush();
            owr.close();

            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader br = null;

            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuffer sBuff = new StringBuffer();
            String line = null;

            sBuff.append(br.readLine());
            data = new JSONArray(sBuff.toString().trim());
            is.close();
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

}