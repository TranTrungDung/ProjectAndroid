package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.PrefConfig;
import com.example.myapplication.R;
import com.example.myapplication.list.baihoc;
import com.example.myapplication.bill;

import java.util.Collections;
import java.util.List;



public class billadapter extends RecyclerView.Adapter<billadapter.Holder> {
    private Context context;
    private List<baihoc> taskModelList;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

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
        if(taskModelList == null){
            return;
        }
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(taskModelList.get(position).getId_pr()));
        holder.Name.setText(taskModelList.get(position).getName());
        holder.Gia.setText(String.valueOf(taskModelList.get(position).getPrice()));
        holder.image.setImageResource(taskModelList.get(position).getImage());
        holder.soluong.setText("x" + String.valueOf(taskModelList.get(position).getAmount()));
        holder.size.setText(taskModelList.get(position).getSize());
        holder.layoutdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskModelList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                PrefConfig.writeListInPref(context.getApplicationContext(), taskModelList);
                Collections.reverse(taskModelList);
                bill.tongtien();
            }
        });
    }
    @Override
    public int getItemCount() {
        return taskModelList != null ? taskModelList.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutdelete;
        TextView Name,Gia,size,soluong,pricesum;
        ImageView image;
        public Holder(@NonNull View itemView) {
            super(itemView);
            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            layoutdelete = itemView.findViewById(R.id.layout_delete);
            Name = itemView.findViewById(R.id.nameproduct);
            Gia = itemView.findViewById(R.id.pricebill);
            image = itemView.findViewById(R.id.imageproduct);
            size = itemView.findViewById(R.id.size);
            soluong = itemView.findViewById(R.id.soluong);
            pricesum = itemView.findViewById(R.id.pricesum);

        }
    }

}

