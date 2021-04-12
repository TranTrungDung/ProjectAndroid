package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
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
    ImageButton imgX;
    BaiHocHelper baiHocHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        listView = (ListView)findViewById(R.id.listrank);
        ArrayList<rankList> arrayList = new ArrayList<>();
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        int Count = 0 ;
        Cursor data = baiHocHelper.GetData(" SELECT *  FROM user ORDER BY sumbill desc limit 5");

        while(data.moveToNext()){
            Count ++;
            String username = data.getString(2);
            int sum = data.getInt(5);
            arrayList.add(new rankList(username,Count,sum));
        }

        //khởi tạo customArrayAdapter
        adapter= new rankAdapter(rankActivity.this,
                R.layout.minirank,arrayList);
        listView.setAdapter(adapter);

    }
}