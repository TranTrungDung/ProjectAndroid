package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class showproduct extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    BaiHocHelper baiHocHelper;
    ArrayList<baihoc>arrayList;
    GridView lv;
    BaiHocAdapter adapter;
    Button button;
    TextView bag;
    SharedPreferences sharedpreferences;
    ProgressDialog progressDialog;
    String allcategory;
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
        String nam = bundle.getString("category");
        if(nam.equals("ALL COLLECTION")){
            allcategory = "ALL COLLECTION";
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
            allcategory = "JACKETS";
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
            allcategory = "SWEATERS";
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
            allcategory = "T-SHIRTS";
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
            allcategory = "PANTS";
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
                progressDialog = new ProgressDialog(showproduct.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                Intent intent = new Intent(showproduct.this,detailPro.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",allcategory);
                bundle.putString("name",arrayList.get(position).getName());
                bundle.putString("price",arrayList.get(position).getPrice());
                bundle.putString("detail",arrayList.get(position).getDetails());
                bundle.putInt("img",arrayList.get(position).getImage());
                intent.putExtra("data",bundle);
                finish();
                startActivity(intent);
            }
        });
        ImageButton imageButton = findViewById(R.id.close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                CategoryFragment fragment = new CategoryFragment();
                fm.beginTransaction().replace(R.id.show,fragment).commit();
                finish();
            }
        });

        sharedpreferences = showproduct.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name1 = sharedpreferences.getString("nameKey","");

        if(name1.isEmpty()){
            Toast.makeText(showproduct.this,"khong",Toast.LENGTH_LONG).show();
        }
        else {
        }
    }
    @Override
    public void onBackPressed()
    {


    }
}

