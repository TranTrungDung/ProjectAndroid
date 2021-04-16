package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.SQLLite.BaiHocHelper;
import com.example.myapplication.list.baihoc;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.Adapter.BaiHocAdapter;
import com.example.myapplication.sharedprefer.PrefConfig;

public class showproduct extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    public static final String LIST_KEY = "BAG";
    public static final String product= "pro";

    private List<baihoc> taskList;
    BaiHocHelper baiHocHelper;
    ArrayList<baihoc>arrayList;
    GridView lv;
    BaiHocAdapter adapter;
    Button button;
    TextView bag;
    ImageView Bag;
    ProgressDialog progressDialog;
    String allcategory;
    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showproduct);
        //tao database
        taskList = PrefConfig.readListFromPref(this);

        if (taskList == null) {
            taskList = new ArrayList<>();
            t = 0;
        }
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        lv = (GridView) findViewById(R.id.lv);
        arrayList = new ArrayList<>();
        adapter = new BaiHocAdapter(this,R.layout.noidung,arrayList);
        lv.setAdapter(adapter);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String nam = bundle.getString("category");
        if(nam.equals("ALL COLLECTION")){
            allcategory = "ALL COLLECTION";
            Cursor data = baiHocHelper.GetData("SELECT * FROM product");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                int amount = 0;
                String size = null;
                arrayList.add(new baihoc(id,price ,name,details,im,amount,size));

            }
        }
        else if(nam.equals("JACKETS")){
            allcategory = "JACKETS";
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '1'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                int amount = 0;
                String size = null;
                arrayList.add(new baihoc(id,price ,name,details,im,amount,size));
            }
        }
        else if(nam.equals("SWEATERS")){
            allcategory = "SWEATERS";
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '2'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                int amount = 0;
                String size = null;
                arrayList.add(new baihoc(id,price ,name,details,im,amount,size));
            }
        }
        else if(nam.equals("T-SHIRTS")){
            allcategory = "T-SHIRTS";
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = '3'");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                int amount = 0;
                String size = null;
                arrayList.add(new baihoc(id,price ,name,details,im,amount,size));
            }
        }else{
            allcategory = "PANTS";
            Cursor data = baiHocHelper.GetData("SELECT * FROM product WHERE id_category = 4");
            while(data.moveToNext()){
                String price = data.getString(2);
                String details = data.getString(4);
                int id = data.getInt(0);
                String name = data.getString(3);
                int im = data.getInt(5);
                int amount = 0;
                String size = null;
                arrayList.add(new baihoc(id,price ,name,details,im,amount,size));
            }
        }

        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                progressDialog = new ProgressDialog(showproduct.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                Intent intent = new Intent(showproduct.this,detailPro.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",allcategory);
                bundle.putInt("id",arrayList.get(position).getId_pr());
                bundle.putString("name",arrayList.get(position).getName());
                bundle.putString("price",arrayList.get(position).getPrice());
                bundle.putString("detail",arrayList.get(position).getDetails());
                bundle.putInt("img",arrayList.get(position).getImage());
                intent.putExtra("data",bundle);
                finish();
                startActivity(intent);
            }
        });
        ImageButton imageButton = findViewById(R.id.close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bag = (TextView) findViewById(R.id.bag);
        t = taskList.size();
        int k = 0,sum = 0;
        for(int h = 0; h < t ; h++ ) {
            k = taskList.get(h).getAmount();
            sum += k;
        }
        String s=String.valueOf(sum);
        bag.setText(s);

        Bag =(ImageView)findViewById(R.id.Bag);
        Bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bag = new Intent(showproduct.this,bill.class);
                Bundle bundle = new Bundle();
                bundle.putString("category",allcategory);
                bag.putExtra("data",bundle);
                finish();
                startActivity(bag);
            }
        });
    }
    @Override
    public void onBackPressed()
    {


    }
}

