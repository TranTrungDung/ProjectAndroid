package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.list.ListMyOrder;
import com.example.myapplication.list.rankList;

import java.util.ArrayList;

public class rankAdapter extends ArrayAdapter<rankList>{
    Context context;
    ArrayList<rankList> arrayList;
    int layoutResource;
    public rankAdapter(Context context, int resource, ArrayList<rankList> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }
    @NonNull
    @Override
//Hàm khởi tạo cho các dòng để hiển thị trên ListView
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource,null);

//Hàm khởi thêm dữ lieu vào các View từ arrayList thông qua position
        TextView txt1 = (TextView)convertView.findViewById(R.id.number);
        String tx1 = String.valueOf(arrayList.get(position).getId());
        txt1.setText(tx1);
        TextView txt2 = (TextView)convertView.findViewById(R.id.usname);
        txt2.setText(arrayList.get(position).getName());
        TextView txt3 = (TextView)convertView.findViewById(R.id.sumprice);
        String tx3 = String.valueOf(arrayList.get(position).getSumprice());
        txt3.setText(tx3);
        return convertView;
    }
}



