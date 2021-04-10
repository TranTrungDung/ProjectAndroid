package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class bill extends AppCompatActivity {
    ImageButton imgClose;
    Button delete;
    TextView sl,pricesum;

    private List<baihoc> taskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        imgClose = (ImageButton) findViewById(R.id.imgClose);
        sl = (TextView) findViewById(R.id.sl);
        taskList = PrefConfig.readListFromPref(this);
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        int t = taskList.size();

        int sumpro = 0, k = 0, j = 0,sum = 0,g = 0;
        for(int h = 0; h < t ; h++ ){
            k = taskList.get(h).getAmount();
            j = Integer.parseInt(taskList.get(h).getPrice());
            g = k * j;
            sum += k;
            sumpro += g;
        }
        String s=String.valueOf(sum);
        sl.setText(s);
        pricesum = (TextView) findViewById(R.id.pricesum);
        pricesum.setText(String.valueOf(sumpro));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        billadapter adapter = new billadapter(getApplicationContext(), taskList);
        recyclerView.setAdapter(adapter);
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