package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.example.myapplication.SQLLite.BaiHocHelper;
import com.example.myapplication.list.baihoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class detailPro extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    TextView name1,price,detail;
    ImageView img,bill2;
    ImageButton btnimg;
    Button addbag;
    Spinner nbpick;
    Spinner size;
    int id;
    private List<baihoc> taskList;
    public static final String LIST_KEY = "BAG";
    public static final String product= "pro";
    SharedPreferences sharedpreferences;
    BaiHocHelper baiHocHelper;
    TextView bag;
    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<String> arrPackage;
        setContentView(R.layout.activity_detail_pro);
        nbpick = (Spinner) findViewById(R.id.npicker);
        size = (Spinner) findViewById(R.id.sizeproduct);
        taskList = PrefConfig.readListFromPref(this);

        if (taskList == null){
            taskList = new ArrayList<>();}
            name1 = (TextView) findViewById(R.id.name1);
            price = (TextView) findViewById(R.id.pricee);
            detail = (TextView) findViewById(R.id.detail);
            img = (ImageView) findViewById(R.id.img);
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("data");
            id = bundle.getInt("id");
            String all = bundle.getString("category");
            String ten = bundle.getString("name");
            String gia = bundle.getString("price");
            String mota = bundle.getString("detail");
            img.setImageResource(bundle.getInt("img"));
            name1.setText(ten);
            price.setText(gia);
            detail.setText(mota);
            amount();
            size();
            String id1 = String.valueOf(id);
            addbag = (Button) findViewById(R.id.addbag);
//             final Check check = new Check(detailPro.this);
            addbag.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int sol = Integer.parseInt(nbpick.getSelectedItem().toString());
                    Toast.makeText(detailPro.this,"Add Success",Toast.LENGTH_LONG).show();
                    String sizee = size.getSelectedItem().toString();
                    baihoc baihoc1 = new baihoc(id,price.getText().toString(),name1.getText().toString(),detail.getText().toString(),bundle.getInt("img"),sol,sizee);
                    taskList.add(baihoc1);
                    PrefConfig.writeListInPref(getApplicationContext(), taskList);
                    Collections.reverse(taskList);
                    int t = taskList.size();
                    Intent intent1 = new Intent(detailPro.this, showproduct.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("category", all);
                    intent1.putExtra("data", bundle);
                    finish();
                    startActivity(intent1);
                }
            });
            btnimg = (ImageButton) findViewById(R.id.imgClose1);
            btnimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(detailPro.this, showproduct.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("category", all);
                    intent1.putExtra("data", bundle);
                    finish();
                    startActivity(intent1);
                }
            });
            bill2 = (ImageView) findViewById(R.id.bill2);
            bill2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(detailPro.this,bill.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("category", all);
                    intent1.putExtra("data", bundle);
                    finish();
                    startActivity(intent1);
                }
            });
            bag = (TextView) findViewById(R.id.bag) ;
        t = taskList.size();
        int k = 0,sum = 0;
        for(int h = 0; h < t ; h++ ) {
            k = taskList.get(h).getAmount();
            sum += k;
        }
        String s=String.valueOf(sum);
        bag.setText(s);
        }
    private  void amount (){
        Integer[] integers = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,integers);
        nbpick.setAdapter(arrayAdapter);
    }
    private  void size (){
        String[] strings = new String[]{"S","M","L","XL"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,strings);
        size.setAdapter(arrayAdapter);
    }}

