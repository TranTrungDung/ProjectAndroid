package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class CategoryFragment extends Fragment {
    Button button;
    ListView listView;
    adapter adapter;
    public CategoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        listView = (ListView)v.findViewById(R.id.listview);
        //khởi tạo dữ liệu mẫu
        ArrayList<country> arrayList = new ArrayList<>();
        arrayList.add(new country("ALL COLLECTION", R.drawable.all,1));
        arrayList.add(new country("JACKETS", R.drawable.jacket,1));
        arrayList.add(new country("SWEATERS",  R.drawable.sweaters,2));
        arrayList.add(new country("T-SHIRTS", R.drawable.t_shirts,3));
        arrayList.add(new country("PANTS", R.drawable.pants,4));
        //khởi tạo customArrayAdapter
        adapter = new adapter(getActivity(),
                R.layout.category,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(),showproduct.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("category",arrayList.get(position).getName());
                        intent.putExtra("data",bundle);
                        startActivity(intent);
            }
        });

        return v;

    }
}