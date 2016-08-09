package com.example.memberproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 한국정보기술 on 2016-08-08.
 */
public class WebAsyncTask extends AsyncTask<String, String, String> {
    Activity activity;

    public WebAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // URL 생성
            StringBuilder sb = new StringBuilder();
            sb.append("http://192.168.0.228:8111/10.AndroidProject/Register");
            URL url = new URL(sb.toString());

            // url 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 데이터 전송
            String str = "id="+params[0] + "&pwd="+params[1];
            OutputStream os = conn.getOutputStream();
            os.write(str.getBytes());

            // 데이터 수신
            if( conn.getResponseCode() == HttpURLConnection.HTTP_OK ) { // 성공했을 경우
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String result = br.readLine();
                publishProgress(result);
                return result;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Toast.makeText(activity, values[0], Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String params) {
        super.onPostExecute(params);
        if(params.equals("로그인 성공")) {
            Intent intent = new Intent(activity, ListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }
    }
}
