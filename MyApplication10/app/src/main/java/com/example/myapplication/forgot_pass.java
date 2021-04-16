package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.SQLLite.BaiHocHelper;

public class forgot_pass extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    TextView forget ;
    EditText user,new_pass,new_cfpass;
    Button btn_confirm,back;
    BaiHocHelper baiHocHelper;
    String username = null;
    String password1 =null,password2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        baiHocHelper = new BaiHocHelper(forgot_pass.this,"mikenco.sqlite",null,1);
        user = (EditText) findViewById(R.id.user_pass);
        new_pass=(EditText)findViewById(R.id.pass_pass);
        new_cfpass=(EditText)findViewById(R.id.cfpassword);
        back =(Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_confirm=(Button) findViewById(R.id.btnConfirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datauser();
            }
        });
    }
    private void Datauser () {
        username = user.getText().toString();
        password1 = new_pass.getText().toString();
        password2 = new_cfpass.getText().toString();
        Cursor Datauser = baiHocHelper.GetData("SELECT * FROM user WHERE username = '"+username +"'" );
        if(Datauser.getCount()>0)
        {
            if(password1.equals(password2) )
            {
                baiHocHelper.QueryData("UPDATE user SET password ='"+password1+"' where username ='"+username+"'");
                finish();
            }
            else
            {
                Toast.makeText(this, "Password not match !!!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "User not exist", Toast.LENGTH_SHORT).show();
        }
    }
}