package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BaiHocAdapter extends BaseAdapter {
    private Context context;
    private int Layout;
    private List<baihoc> baihocList;

    public BaiHocAdapter(Context context, int layout, List<baihoc> baihocList) {
        this.context = context;
        Layout = layout;
        this.baihocList = baihocList;
    }

    @Override
    public int getCount() {
        return baihocList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView name;
        TextView price;
        ImageView imageView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder =  new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(Layout,null);

            holder.name = (TextView) convertView.findViewById(R.id.name1);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        baihoc baihoc = baihocList.get(position);
        holder.name.setText(baihoc.getName());
        holder.price.setText(baihoc.getPrice());
        holder.imageView.setImageResource(baihoc.getImage());
        return convertView;
    }
}
