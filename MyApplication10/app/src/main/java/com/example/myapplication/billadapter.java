package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;



public class billadapter extends RecyclerView.Adapter<billadapter.Holder> {
    private Context context;
    private List<baihoc> taskModelList;

    public billadapter(Context context, List<baihoc> taskModelList) {
        this.context = context;
        this.taskModelList = taskModelList;
        Collections.reverse(taskModelList);
    }

    public void setTaskModelList(List<baihoc> taskModelList) {
        this.taskModelList = taskModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context)
                .inflate(R.layout.listbill, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Log.d("Daily", "taskModelList: " + taskModelList);
        holder.Name.setText(taskModelList.get(position).getName());
        holder.Gia.setText(String.valueOf(taskModelList.get(position).getPrice()));
        holder.image.setImageResource(taskModelList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return taskModelList != null ? taskModelList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView Name,Gia;
        ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.nameproduct);
            Gia = itemView.findViewById(R.id.pricebill);
            image = itemView.findViewById(R.id.imageproduct);
        }
    }

}

