package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class payments extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) findViewById(R.id.radio_a);
        radioButton2 = (RadioButton) findViewById(R.id.radio_b);
        TextView textView12 = findViewById(R.id.textView12);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        int idcheck = radioGroup.getCheckedRadioButtonId();
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView12.setVisibility(View.VISIBLE);
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView12.setVisibility(View.INVISIBLE);
            }
        });
        imageButton = (ImageButton) findViewById(R.id.imgClose1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payments.this,bill.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("category",all);
                intent.putExtra("data",bundle);
                finish();
                startActivity(intent);
            }
        });

    }
}