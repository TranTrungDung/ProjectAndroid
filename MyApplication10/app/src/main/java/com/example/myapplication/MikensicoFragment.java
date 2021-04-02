package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TimePicker;
import android.widget.Toast;

public class MikensicoFragment extends Fragment {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    // TODO: Rename and change types of parameters
    Button btnsignup;
    Button btnlogin;
    EditText user_lg, password_lg;
    BaiHocHelper baiHocHelper;
    private BaiHocHelper baihocHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragmen
        //View view = inflater.inflate(R.layout.fragment_mikensico, container, false);
        View view = null;
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey","");
        if (name == ""){
            view = inflater.inflate(R.layout.fragment_mikensico, container, false);
            Toast.makeText(getActivity(),"",Toast.LENGTH_LONG).show();
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
                        String user  = user_lg.getText().toString();
                        String passwd  = password_lg.getText().toString();

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.putString(Name, user);
                        editor.putString(Pass, passwd);
                        editor.commit();
                        Intent intent = getActivity().getIntent();
                        getActivity().finish();
                        startActivity(intent);
                    }
                }
            });

        }else{
            //sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            //SharedPreferences.Editor editor = sharedpreferences.edit();
            //editor.clear();
            //editor.apply();
            Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
            view = inflater.inflate(R.layout.fragment_user_screen, container, false);
            //editor.remove("nameKey");
            //editor.remove("pass");

        }
        return view;
    }
}