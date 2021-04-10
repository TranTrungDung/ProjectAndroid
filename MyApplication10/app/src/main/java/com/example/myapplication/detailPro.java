package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

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
    Button addbag,test;
    NumberPicker nbpick;
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
        NumberPicker();
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
            String id1 = String.valueOf(id);
            addbag = (Button) findViewById(R.id.addbag);
            addbag.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(detailPro.this, "luu xong", Toast.LENGTH_LONG).show();
                    baihoc baihoc1 = new baihoc(id,price.getText().toString(),name1.getText().toString(),detail.getText().toString(),bundle.getInt("img"));
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
            btnimg = (ImageButton) findViewById(R.id.imgClose);
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
            test = (Button) findViewById(R.id.test);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(detailPro.this);
                    Gson gson = new Gson();
                    String jsonString = pref.getString(product, "");
                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("pro");
                    editor.apply();
                    Toast.makeText(detailPro.this,"ok",Toast.LENGTH_LONG).show();
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
        String s=String.valueOf(t);
        bag.setText(s);
        }
        public void NumberPicker(){
        nbpick = (NumberPicker)findViewById(R.id.nbpicker);
        nbpick.setMaxValue(10);
        nbpick.setMinValue(1);
        nbpick.setValue(1);
        nbpick.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        }
    }
