package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.SQLLite.BaiHocHelper;

public class update_user extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    Button btnback, btnupdate;
    BaiHocHelper baiHocHelper;
    EditText tx_user, tx_email, tx_pass, tx_phone;
    int id = 0;
    String username = null, email = null, password = null, phone = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        baiHocHelper = new BaiHocHelper(this, "mikenco.sqlite", null, 1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        btnback = (Button) findViewById(R.id.btn_back);
        tx_user = (EditText) findViewById(R.id.user_pass);
        tx_email = (EditText) findViewById(R.id.pass_pass);
        tx_pass = (EditText) findViewById(R.id.password_update);
        tx_phone = (EditText) findViewById(R.id.phone_update);
        btnupdate = (Button) findViewById(R.id.btn_update);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Data();
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
                Intent intent = new Intent(update_user.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }

    private void Data() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "");
        Cursor data = baiHocHelper.GetData("SELECT * FROM user WHERE username = '" + name + "'");
        while (data.moveToNext()) {
            id = data.getInt(0);
            username = data.getString(2);
            email = data.getString(1);
            password = data.getString(3);
            phone = data.getString(4);
        }
        tx_user.setText(username);
        tx_email.setText(email);
        tx_pass.setText(password);
        tx_phone.setText(phone);
    }

    private void updateData() {
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "");
        String usname = tx_user.getText().toString();
        String mail = tx_email.getText().toString();
        String passw = tx_pass.getText().toString();
        String nphone = tx_phone.getText().toString();
        baiHocHelper.QueryData("UPDATE user SET username ='" + usname + "' , email ='" + mail + "' , password = '" + passw + "' , phone = '" + nphone + "'  where id = '" + id + "'");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
        String usname1 = tx_user.getText().toString();
        String passwd = tx_pass.getText().toString();
        SharedPreferences.Editor editor1 = sharedpreferences.edit();
        editor1.putString(Name, usname1);
        editor1.putString(Pass, passwd);
        editor1.commit();

    }
}
