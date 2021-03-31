package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class showproduct extends AppCompatActivity {
    BaiHocHelper baiHocHelper;
    ArrayList<baihoc>arrayList;
    GridView lv;
    BaiHocAdapter adapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showproduct);
        //tao database
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        lv = (GridView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        adapter = new BaiHocAdapter(this,R.layout.noidung,arrayList);
        lv.setAdapter(adapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String nam = bundle.getString("name");
        if(nam.equals("ALL COLLECTION")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product");
            while(data.moveToNext()){
                String price = data.getString(2);
                String size = data.getString(3);
                String details = data.getString(5);
                int amount = data.getInt(6);
                int id = data.getInt(0);
                String name = data.getString(4);
                int im = data.getInt(7);
                arrayList.add(new baihoc(id,price, size,name,details,amount,im));

            }
        }
        else if(nam.equals("JACKETS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '27'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String size = data.getString(3);
                String details = data.getString(5);
                int amount = data.getInt(6);
                int id = data.getInt(0);
                String name = data.getString(4);
                int im = data.getInt(7);
                arrayList.add(new baihoc(id,price, size,name,details,amount,im));
            }
        }
        else if(nam.equals("SWEATERS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '26'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String size = data.getString(3);
                String details = data.getString(5);
                int amount = data.getInt(6);
                int id = data.getInt(0);
                String name = data.getString(4);
                int im = data.getInt(7);
                arrayList.add(new baihoc(id,price, size,name,details,amount,im));
            }
        }
        else if(nam.equals("T-SHIRTS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '25'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String size = data.getString(3);
                String details = data.getString(5);
                int amount = data.getInt(6);
                int id = data.getInt(0);
                String name = data.getString(4);
                int im = data.getInt(7);
                arrayList.add(new baihoc(id,price, size,name,details,amount,im));
            }
        }else{
            Cursor haha = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = 28");
            while(haha.moveToNext()){
                String price = haha.getString(2);
                String size = haha.getString(3);
                String details = haha.getString(5);
                int amount = haha.getInt(6);
                int id = haha.getInt(0);
                String name = haha.getString(4);
                int im = haha.getInt(7);
                arrayList.add(new baihoc(id,price, size,name,details,amount,im));
            }
        }

        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(showproduct.this,arrayList.get(position).getName(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(showproduct.this,detailPro.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",arrayList.get(position).getName());
                bundle.putString("price",arrayList.get(position).getPrice());
                bundle.putString("detail",arrayList.get(position).getDetails());
                bundle.putInt("img",arrayList.get(position).getImage());
                intent.putExtra("data",bundle);
                startActivity(intent);
            }
        });
    }
}
