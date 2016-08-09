package com.example.memberproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText id;
    EditText pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
    }

    public void onClicked(View v){
        WebAsyncTask asyncTask = new WebAsyncTask(this);
        asyncTask.execute(id.getText().toString(), pwd.getText().toString());
    }
}
