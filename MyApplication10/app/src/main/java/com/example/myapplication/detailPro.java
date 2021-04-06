package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

public class detailPro extends AppCompatActivity {
    TextView name1,price,detail;
    ImageView img;
    ImageButton btnimg;
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
        btnimg = (ImageButton) findViewById(R.id.imgClose);
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(detailPro.this,showproduct.class);
                Bundle bundle = new Bundle();
                bundle.putInt("key",2);
                bundle.putString("category",all);
                intent1.putExtra("data",bundle);
                finish();
                startActivity(intent1);

            }
        });
    }
}