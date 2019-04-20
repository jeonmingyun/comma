package ticketzone.org.com.app_mngr;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkTask extends AsyncTask<SendDataSet, Void, JSONArray> {
    String ip ="39.127.7.42"; //학교 IP번호
    String path = "http://"+ip+":8080/"; // 연결할 jsp주소

    NetworkTask(String url) {
        this.path += url;
    }

    @Override
    protected JSONArray doInBackground(SendDataSet... strings) {
        JSONArray data = new JSONArray(); // response data
//        String data = "";
        JSONObject jobj = new JSONObject(); // request data

        try {
            for ( int i = 0; i < strings.length; i++) {
                jobj.put(strings[i].key, strings[i].value);
            }

            /* 서버연결 */
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true); // xml내용을 전달하기 위해서 출력 스트림을 사용
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값  전달 */
            OutputStreamWriter owr = new OutputStreamWriter(conn.getOutputStream());
            owr.write(jobj.toString());
            owr.flush();
            owr.close();

            String response;
            int responseCoe = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {

            }
            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader br = null;

//            HttpResponse response;
//
//            br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
//
//            String json = br.readLine();
//            JSONTokener json_token = new JSONTokener(json);
//            data = new JSONArray(json_token);
//            Log.e("result ", data.toString());
            is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is), 8*1024);

            StringBuffer sBuff = new StringBuffer();
//            String line = null;
//
//            while ((line = br.readLine()) != null) {
//                sBuff.append(line +"\n");
//            }
            sBuff.append(br.readLine());
            Log.e("tlqk", sBuff.toString().trim()+"" );
            JSONArray jarr = new JSONArray(sBuff.toString().trim());
            Log.e("tlqk", jarr+"" );
            data = jarr;
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