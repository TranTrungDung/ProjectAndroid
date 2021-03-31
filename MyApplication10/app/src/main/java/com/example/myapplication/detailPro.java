package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detailPro extends AppCompatActivity {
    TextView name1,price,detail;
    ImageView img;
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
        String ten = bundle.getString("name");
        String gia = bundle.getString("price");
        String mota = bundle.getString("detail");
        img.setImageResource(bundle.getInt("img"));
        name1.setText(ten);
        price.setText(gia);
        detail.setText(mota);
    }
}