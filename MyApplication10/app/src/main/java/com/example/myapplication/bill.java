package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class bill extends AppCompatActivity {
    ImageButton imgClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        imgClose = (ImageButton) findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(bill.this,showproduct.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",all);
                intent.putExtra("data",bundle);
                finish();
                startActivity(intent);
            }
        });
    }
}