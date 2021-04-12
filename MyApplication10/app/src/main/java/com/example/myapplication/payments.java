package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.SQLLite.BaiHocHelper;
import com.example.myapplication.list.baihoc;
import com.example.myapplication.sharedprefer.PrefConfig;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class payments extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    private List<baihoc> taskList;
    SharedPreferences sharedpreferences;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    ImageButton imageButton;
    Button order;
    EditText edit_name,edit_phone,edit_address;
    int idcheck, sum;
    BaiHocHelper baiHocHelper;
    TextView idhd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        baiHocHelper = new BaiHocHelper(this, "mikenco.sqlite", null, 1);
        Random generator = new Random();
        int id = generator.nextInt((99999 - 10000)) + 10000;
        idhd = findViewById(R.id.idhd);
        idhd.setText(String.valueOf(id));
        taskList = PrefConfig.readListFromPref(this);
        order = (Button) findViewById(R.id.order);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radio_a);
        radioButton2 = (RadioButton) findViewById(R.id.radio_b);
        edit_name = (EditText) findViewById(R.id.edit_Name);
        edit_phone = (EditText) findViewById(R.id.editPhone);
        edit_address=(EditText) findViewById(R.id.editAddress);
        TextView textView12 = findViewById(R.id.textView12);
        textView12.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        sum = bundle.getInt("h");
        idcheck = radioGroup.getCheckedRadioButtonId();
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView12.setVisibility(View.VISIBLE);
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView12.setVisibility(View.INVISIBLE);
            }
        });
        imageButton = (ImageButton) findViewById(R.id.imgClose1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payments.this, bill.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("category", all);
                intent.putExtra("data", bundle);
                finish();
                startActivity(intent);
            }
        });
        
         Order();

    }

    private void Order() {
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       if (radioButton1.isChecked()) {
                           place(0);
                           Gson gson = new Gson();
                           String jsonString = gson.toJson(taskList);
                           SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(payments.this);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.clear();
                           editor.apply();
                           Toast.makeText(payments.this, "chua thanh toan", Toast.LENGTH_LONG).show();
                           Intent intent = new Intent(payments.this, my_order.class);
                           finish();
                           startActivity(intent);
                       } else if (radioButton2.isChecked()) {
                           place(1);
                           Gson gson = new Gson();
                           String jsonString = gson.toJson(taskList);
                           SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(payments.this);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.clear();
                           editor.apply();
                           Toast.makeText(payments.this, "thanh toan roi", Toast.LENGTH_LONG).show();
                           Intent intent = new Intent(payments.this, my_order.class);
                           finish();
                           startActivity(intent);
                       } else {
                           Toast.makeText(payments.this, "Select a payment method", Toast.LENGTH_LONG).show();
                       }
                   }
        });
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }
    public void place(int pay){
        taskList = PrefConfig.readListFromPref(this);
        int sumpro = 0, k = 0, j = 0, g = 0, ship1 = 30000;
        int t = taskList.size();
        for (int h = 0; h < t; h++) {
            k = taskList.get(h).getAmount();
            j = Integer.parseInt(taskList.get(h).getPrice());
            g = k * j;
            sum += k;
            sumpro += g;
        }
        int sumsum1 = ship1 + sumpro;
        int iduser = 0, idbill = 0;
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "");
        Cursor data2 = baiHocHelper.GetData("SELECT * FROM user WHERE username ='" + name + "' ");
        while (data2.moveToNext()) {
            iduser = data2.getInt(0);
        }
        baiHocHelper.QueryData("INSERT INTO bill VALUES(null,'" + iduser + "','" + idhd.getText().toString() + "','" + getDate() + "','" + sumsum1 + "','"+pay+"')");
        Cursor data3 = baiHocHelper.GetData("SELECT * FROM bill WHERE id ='" + iduser + "' AND madathang = '" + idhd.getText().toString() + "' ");
        while (data3.moveToNext()) {
            idbill = data3.getInt(0);
        }
        for (int h = 0; h < t; h++) {
            baiHocHelper.QueryData("INSERT INTO details_bill VALUES('" + taskList.get(h).getId_pr() + "','" + idbill + "','" + taskList.get(h).getAmount() + "','" + Integer.parseInt(taskList.get(h).getPrice()) + "')");
        }
    }

}