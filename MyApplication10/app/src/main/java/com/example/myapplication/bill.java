package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.list.baihoc;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.Adapter.billadapter;
import com.example.myapplication.sharedprefer.PrefConfig;

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
        BtnNext();
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
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(bill.this);
// gán tiêu đề cho dialog
// hiển thị Thông điệp (thông báo) lên dialog
                    alertDialogBuilder .setMessage("Login please ?")
                            .setCancelable(false)
                            .setNegativeButton("Click!!!",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    // Tạo alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // Hiển thị dialog
                    alertDialog.show();
                    FragmentManager fm = getSupportFragmentManager();
                    MikensicoFragment fragment = new MikensicoFragment();
                    fm.beginTransaction().replace(R.id.activity_bill, fragment).commit();
                    next.setVisibility(View.INVISIBLE);
                }else if(taskList.size() == 0){
                    Toast.makeText(bill.this, "hay them san pham", Toast.LENGTH_SHORT).show();
                }
                else{
                 int h = Integer.parseInt(sumsum.getText().toString());
                Intent intent1 = new Intent(bill.this,payments.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("category",all);
                bundle1.putInt("h",h);
                intent1.putExtra("data",bundle);
                finish();
                startActivity(intent1);}
            }
        });
    }
    public static void tongtien(){
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        int t = taskList.size();

        int sumpro = 0, k = 0, j = 0,sum = 0,g = 0,ship1 = 30000;
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
            ship.setText(String.valueOf(ship1));
        }
        int sumsum1 = ship1 + sumpro;
        String sumsun11 = String.valueOf(sumsum1);
        sumsum.setText(sumsun11);
    }
}