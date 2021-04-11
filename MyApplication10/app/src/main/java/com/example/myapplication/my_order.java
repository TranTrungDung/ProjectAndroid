package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication.Adapter.MyOrderAdapter;
import com.example.myapplication.list.ListMyOrder;


import java.util.ArrayList;

public class my_order extends AppCompatActivity {
    ListView listView;
    MyOrderAdapter adapter ;
    ImageButton imgX;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
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
        arrayList.add(new ListMyOrder("1/1/2021", 1,20000));
        arrayList.add(new ListMyOrder(null, 2,100000));
        arrayList.add(new ListMyOrder(null,  3,20000));
        arrayList.add(new ListMyOrder(null, 4,300000));
        arrayList.add(new ListMyOrder(null,5,40000));
        //khởi tạo customArrayAdapter
        adapter= new MyOrderAdapter(my_order.this,
                R.layout.list_my_order,arrayList);
        listView.setAdapter(adapter);
    }

}