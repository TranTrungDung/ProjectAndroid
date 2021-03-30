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
        //taobang
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,email VARCHAR(200),username VARCHAR(200),password VARCHAR(200),phone VARCHAR(11))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS category(id_category INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS product(id_pr INTEGER PRIMARY KEY AUTOINCREMENT,id_category INTEGER,price INTEGER,size VARCHAR(200),name VARCHAR(200),details VARCHAR(200),amount INTEGER,image BLOG,FOREIGN KEY (id_category) REFERENCES category(id_category))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS bill(id_bill INTEGER PRIMARY KEY AUTOINCREMENT,id INTEGER,date DATE,sum INTEGER,pay INTEGER,FOREIGN KEY (id) REFERENCES user(id))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS detail(id_pr INTEGER,id_bill INTEGER,amount INTEGER,price INTEGER,FOREIGN KEY (id_pr) REFERENCES product(id),FOREIGN KEY (id_bill) REFERENCES bill(id_bill))");
        //baiHocHelper.QueryData("INSERT INTO product VALUES(null,53,56000,'XL','aodep','aodepquadi',2,'"+R.drawable.caiquan+"')");
        //baiHocHelper.QueryData("INSERT INTO product VALUES(null,54,430000,'M','aodep','aodepquadi',2,'"+R.drawable.thf+"')");
        //baiHocHelper.QueryData("INSERT INTO product VALUES(null,55,122000,'L','aodep','aodepquadi',2,'"+R.drawable.th2+"')");
        //baiHocHelper.QueryData("INSERT INTO product VALUES(null,56,122000,'L','aodep','aodepquadi',2,'"+R.drawable.ao2+"')");
        //baiHocHelper.QueryData("DELETE FROM product");
        //baiHocHelper.QueryData("DELETE FROM user");
        //baiHocHelper.QueryData("DELETE FROM category");

        lv = (GridView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        adapter = new BaiHocAdapter(this,R.layout.noidung,arrayList);
        lv.setAdapter(adapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String nam = bundle.getString("name");
        if(nam.equals("MIKENCO0")){
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
        else if(nam.equals("MIKENCO1")){
            Toast.makeText(showproduct.this,"MIKENCO1",Toast.LENGTH_LONG).show();
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '53'");
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
        else if(nam.equals("MIKENCO2")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '54'");
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
        else if(nam.equals("MIKENCO3")){
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '55'");
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
            Cursor haha = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = 56");
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
                Toast.makeText(getApplicationContext(),arrayList.get(position).toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),arrayList.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
