package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MikensicoFragment extends Fragment {
    // TODO: Rename and change types of parameters
    Button btnsignup;
    Button btnlogin;
    EditText user_lg, password_lg;
    BaiHocHelper baiHocHelper;
    private BaiHocHelper baihocHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mikensico, container, false);
        btnsignup = view.findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(),signup.class);
               startActivity(intent);
            }
        });
        baiHocHelper = new BaiHocHelper(getActivity(),"mikenco.sqlite",null,1);
        btnlogin = view.findViewById(R.id.btnlogin);
        password_lg = view.findViewById(R.id.password_lg);
        user_lg = view.findViewById(R.id.user_lg);

        btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor data = baiHocHelper.GetData("SELECT * FROM user WHERE username = '"+user_lg.getText().toString()+"' AND password = '"+password_lg.getText().toString()+"'");
                    if(data.getCount() == 0 )
                    {
                        Toast.makeText(getActivity(),"Username or password is incorrect",Toast.LENGTH_LONG).show();
                    }
                    else{
                        FragmentManager fm = getFragmentManager();
//                        MikensicoFragment mikensicoFragment  = new MikensicoFragment();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.medu, new userScreen());
                        ft.addToBackStack(null);
                        ft.commit();
                        onDestroy();
                    }

               }
            });

        return view;

    }

}