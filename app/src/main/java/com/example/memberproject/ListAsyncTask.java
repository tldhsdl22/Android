package com.example.memberproject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Member;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 한국정보기술 on 2016-08-09.
 */
public class ListAsyncTask extends AsyncTask<Void, Void, List<MemberVO>> {

    Activity activity;

    public ListAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected List<MemberVO> doInBackground(Void... voids) {
        try {
            // URL 생성
            StringBuilder sb = new StringBuilder();

            sb.append("http://192.168.0.228:8101/10._Android/list.and");
            URL url = new URL(sb.toString());

            // url 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept","application/json"); //받을때 데이터타입
            conn.setRequestMethod("POST");

            // 성공하면 여부확인 및 동작
            int resCode = conn.getResponseCode();   // 성공여부
            if(resCode == HttpURLConnection.HTTP_OK){ // 성공하면
                Log.e("ListAsync", "리스트 출력");

                // 데이터 수신
                BufferedReader br =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str;
                String json="";
                // 한줄씩 읽어서 json에 저장
                while((str = br.readLine()) != null) {
                    Log.e("ListAsync", str);
                    json += str;
                }

                JSONArray array = new JSONArray(json);
                Log.e("ListAsync", array.length()+"");

                List<MemberVO> list = new ArrayList<>();

                for(int i=0; i<array.length(); i++){
                    JSONObject jsonObject = (JSONObject) array.get(i);

                    MemberVO member = new MemberVO();
                    member.setId((String) jsonObject.get("id"));
                    member.setName((String) jsonObject.get("name"));
                    member.setAge((int)jsonObject.get("age"));

                    list.add(member);
                }
                return list;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
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

    @Override
    protected void onPostExecute(List<MemberVO> memberVOs) {
        super.onPostExecute(memberVOs);

        // 어댑터뷰(리스트 뷰)
        ListView listView = (ListView)activity.findViewById(R.id.memberListView);

        // 어댑터
        ArrayAdapter adapter = new ListAdapter(activity, R.layout.member_item, memberVOs); // 안드로이드에서 기본적으로 제공되는 레이아웃
        listView.setAdapter(adapter);

    }
}
