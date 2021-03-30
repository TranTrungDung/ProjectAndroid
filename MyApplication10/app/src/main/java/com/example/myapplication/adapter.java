package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Objects;

public class adapter extends ArrayAdapter<country> {
    Context context;
    ArrayList<country> arrayList;
    int layoutResource;
    public adapter(Context context, int resource, ArrayList<country> objects) {
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
        TextView txt1 = (TextView)convertView.findViewById(R.id.textView3);
        txt1.setText(arrayList.get(position).getName());
        ImageView img = (ImageView)convertView.findViewById(R.id.imageButton);
        img.setImageResource(arrayList.get(position).getImage());
        return convertView;
    }
}
