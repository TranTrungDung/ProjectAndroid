package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class detailPro extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    TextView name1,price,detail;
    ImageView img;
    ImageButton btnimg;
    BaiHocHelper baiHocHelper;
    SharedPreferences sharedpreferences;
    TextView bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pro);
        name1 = (TextView) findViewById(R.id.name1);
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