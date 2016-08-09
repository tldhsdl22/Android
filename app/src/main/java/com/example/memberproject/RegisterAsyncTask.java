package com.example.memberproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 한국정보기술 on 2016-08-09.
 */
public class RegisterAsyncTask extends AsyncTask<MemberVO, Void, Void> {

    Activity activity;

    public RegisterAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(MemberVO... params) {
        try {
            // URL 생성
            StringBuilder sb = new StringBuilder();

            sb.append("http://192.168.0.228:8101/10._Android/register.and");
            URL url = new URL(sb.toString());

            // url 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // 데이터 전송
            StringBuilder sb2 = new StringBuilder();
            sb2.append("id="+params[0].getId());
            sb2.append("&pwd="+params[0].getPwd());
            sb2.append("&name="+params[0].getName());
            sb2.append("&addr="+params[0].getAddr());
            sb2.append("&age="+params[0].getAge());
            sb2.append("&tel="+params[0].getTel());

            OutputStream os = conn.getOutputStream();
            os.write(sb2.toString().getBytes());

            // 성공여부
            int resCode = conn.getResponseCode();
            Log.e("Register", ""+resCode);
            if(resCode == HttpURLConnection.HTTP_OK){
                Log.e("Register", "회원가입 성공");

                // 데이터 수신
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String result = br.readLine();

                System.out.println(result);
                if(result.equals("success"))
                    publishProgress();
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Intent intent = new Intent(activity, ListActivity.class);
        activity.startActivity(intent);
        super.onProgressUpdate(values);
    }
}
