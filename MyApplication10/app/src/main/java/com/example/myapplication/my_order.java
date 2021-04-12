package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication.Adapter.MyOrderAdapter;
import com.example.myapplication.SQLLite.BaiHocHelper;
import com.example.myapplication.list.ListMyOrder;
import com.example.myapplication.list.baihoc;


import java.util.ArrayList;

public class my_order extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    ListView listView;
    MyOrderAdapter adapter ;
    ImageButton imgX;
    BaiHocHelper baiHocHelper;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey", "");
        imgX = (ImageButton) findViewById(R.id.imgX);
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = (ListView)findViewById(R.id.lv_my_order);
        //khởi tạo dữ liệu mẫu
        ArrayList<ListMyOrder> arrayList = new ArrayList<>();
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        int iduser = 0;
        Cursor data2 = baiHocHelper.GetData("SELECT * FROM user WHERE username ='" + name + "' ");
        while (data2.moveToNext()) {
            iduser = data2.getInt(0);
        }
        Cursor data = baiHocHelper.GetData("SELECT * FROM bill WHERE id = '"+iduser+"' ");
        while(data.moveToNext()){
            String ngay = data.getString(3);
            int id = data.getInt(0);
            int price = data.getInt(4);
            arrayList.add(new ListMyOrder(ngay,id,price));
        }
        //khởi tạo customArrayAdapter
        adapter= new MyOrderAdapter(my_order.this,
                R.layout.list_my_order,arrayList);
        listView.setAdapter(adapter);
    }

}