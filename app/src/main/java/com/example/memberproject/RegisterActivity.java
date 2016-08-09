package com.example.memberproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText regId, regPwd, regName,regAddr, regAge, regTel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regId = (EditText) findViewById(R.id.regId);
        regPwd = (EditText) findViewById(R.id.regPwd);
        regName = (EditText) findViewById(R.id.regName);
        regAddr = (EditText) findViewById(R.id.regAddr);
        regAge = (EditText) findViewById(R.id.regAge);
        regTel = (EditText) findViewById(R.id.regTel);

    }

    public void onRegist(View v){
        RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask(this);

        MemberVO member = new MemberVO(
                regId.getText().toString(), regPwd.getText().toString(), regName.getText().toString(),
                Integer.parseInt(regAge.getText().toString()), regAddr.getText().toString(), regTel.getText().toString());

        registerAsyncTask.execute(member);



    }
}
