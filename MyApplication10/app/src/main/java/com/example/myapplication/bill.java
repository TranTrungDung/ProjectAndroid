package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class bill extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    private static List<baihoc> taskList;
    ImageButton imgClose;
    Button delete,next;
    static TextView sl;
    static TextView pricesum;
    static TextView sumsum;
    static TextView ship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        BtnNext();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        imgClose = (ImageButton) findViewById(R.id.imgClose1);
        sl = (TextView) findViewById(R.id.sl);
        pricesum = (TextView) findViewById(R.id.pricesum);
        sumsum = (TextView) findViewById(R.id.sumsum);
        ship = (TextView) findViewById(R.id.ship);
        taskList = PrefConfig.readListFromPref(this);
        tongtien();
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
    private void BtnNext(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String all = bundle.getString("category");
        next = (Button) findViewById(R.id.btnNext);
        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey","");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name == ""){
                    Toast.makeText(bill.this,"ddawng nhap ddi",Toast.LENGTH_LONG).show();
                }else{
                Intent intent1 = new Intent(bill.this,payments.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("category",all);
                intent1.putExtra("data",bundle);
                startActivity(intent1);}
            }
        });
    }
    public static void tongtien(){
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        int t = taskList.size();

        int sumpro = 0, k = 0, j = 0,sum = 0,g = 0,ship1 = 0;
        for(int h = 0; h < t ; h++ ){
            k = taskList.get(h).getAmount();
            j = Integer.parseInt(taskList.get(h).getPrice());
            g = k * j;
            sum += k;
            sumpro += g;
        }
        String s=String.valueOf(sum);
        sl.setText(s);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        pricesum.setText(decimalFormat.format(sumpro));
        if(t == 0){
            ship1 = 0;
            ship.setText(String.valueOf(ship1));
        }else{
        ship1 = Integer.parseInt(ship.getText().toString());
        }
        int sumsum1 = ship1 + sumpro;
        String sumsun11 = String.valueOf(sumsum1);
        sumsum.setText(sumsun11);
    }
}