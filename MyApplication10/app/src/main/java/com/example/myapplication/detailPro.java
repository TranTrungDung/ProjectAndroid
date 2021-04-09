package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
<<<<<<< HEAD
=======
import android.database.Cursor;
>>>>>>> 9eaca4bb0544807d5212f6f7fb67b76f151148b2
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
=======
import android.widget.Toolbar;
>>>>>>> 9eaca4bb0544807d5212f6f7fb67b76f151148b2

public class detailPro extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    TextView name1,price,detail;
    ImageView img;
    ImageButton btnimg;
<<<<<<< HEAD
    Button addbag,test;
    int id;
    public static final String LIST_KEY = "BAG";
    public static final String product= "pro";
    SharedPreferences sharedpreferences;
    private List<baihoc> taskList;
=======
    BaiHocHelper baiHocHelper;
    SharedPreferences sharedpreferences;
    TextView bag;
>>>>>>> 9eaca4bb0544807d5212f6f7fb67b76f151148b2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<String> arrPackage;
        setContentView(R.layout.activity_detail_pro);
        taskList = PrefConfig.readListFromPref(this);

        if (taskList == null){
            taskList = new ArrayList<>();}


        name1 = (TextView) findViewById(R.id.name1);
<<<<<<< HEAD
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
            addbag.setOnClickListener(new View.OnClickListener() {
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
        }

    }
=======
        price = (TextView) findViewById(R.id.pricee) ;
        detail = (TextView) findViewById(R.id.detail);
        img = (ImageView) findViewById(R.id.img);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        String ten = bundle.getString("name");
        String gia = bundle.getString("price");
        String mota = bundle.getString("detail");
        img.setImageResource(bundle.getInt("img"));
        name1.setText(ten);
        price.setText(gia);
        detail.setText(mota);
        bag = (TextView) findViewById(R.id.bag);
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        sharedpreferences = detailPro.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name2 = sharedpreferences.getString("nameKey","");
        if(name2.isEmpty()){
            Toast.makeText(detailPro.this,"khong",Toast.LENGTH_LONG).show();
        }
        else {
            int id = 0;
            Cursor data = baiHocHelper.GetData("SELECT amount FROM details_bill,bill,user WHERE details_bill.id_bill = bill.id_bill AND bill.id = user.id AND username = '"+name2+"' ");
            while(data.moveToNext()) {
                id = id + data.getInt(0);

            }

            String s=String.valueOf(id);
            bag.setText(s);
        }
        btnimg = (ImageButton) findViewById(R.id.imgClose);
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(detailPro.this,showproduct.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",all);
                intent1.putExtra("data",bundle);
                finish();
                startActivity(intent1);

            }
        });
    }
}
>>>>>>> 9eaca4bb0544807d5212f6f7fb67b76f151148b2
