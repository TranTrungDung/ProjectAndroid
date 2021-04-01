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
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                arrayList.add(new baihoc(id,price ,name,details,im));

            }
        }
        else if(nam.equals("JACKETS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '1'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                arrayList.add(new baihoc(id,price ,name,details,im));
            }
        }
        else if(nam.equals("SWEATERS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '2'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                arrayList.add(new baihoc(id,price ,name,details,im));
            }
        }
        else if(nam.equals("T-SHIRTS")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '3'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                arrayList.add(new baihoc(id,price ,name,details,im));
            }
        }else{
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = 4");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                arrayList.add(new baihoc(id,price ,name,details,im));
            }
        }

        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
