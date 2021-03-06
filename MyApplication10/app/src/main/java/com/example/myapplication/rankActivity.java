package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication.Adapter.MyOrderAdapter;
import com.example.myapplication.Adapter.rankAdapter;
import com.example.myapplication.SQLLite.BaiHocHelper;
import com.example.myapplication.list.ListMyOrder;
import com.example.myapplication.list.rankList;

import java.util.ArrayList;

public class rankActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    ListView listView;
    rankAdapter adapter ;
    ImageButton imgarrowback,reward;
    BaiHocHelper baiHocHelper;
    ConstraintLayout constraintLayout;
    static int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        listView = (ListView)findViewById(R.id.listrank);
        ArrayList<rankList> arrayList = new ArrayList<>();
        imgarrowback = (ImageButton) findViewById(R.id.image_arrow_back);
        imgarrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        constraintLayout = (ConstraintLayout) findViewById(R.id.con_reward);
        constraintLayout.setVisibility(View.INVISIBLE);
        reward = (ImageButton) findViewById(R.id.imageButton_Reward);

        reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count%2 ==0)
                {
                    constraintLayout.setVisibility(View.INVISIBLE);
                }
                else
                {
                    constraintLayout.setVisibility(View.VISIBLE);

                }

            }
        });
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);

        Cursor data = baiHocHelper.GetData(" SELECT *  FROM user ORDER BY sumbill desc limit 3");

        while(data.moveToNext()){
            String username = data.getString(2);
            int sum = data.getInt(5);
            arrayList.add(new rankList(username,sum));
        }

        //kh???i t???o customArrayAdapter
        adapter= new rankAdapter(rankActivity.this,
                R.layout.minirank,arrayList);
        listView.setAdapter(adapter);

    }
}