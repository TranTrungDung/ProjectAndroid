package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.SQLLite.BaiHocHelper;

public class MikensicoFragment extends Fragment {
    public static final String MyPREFERENCES = "user";
    public static final String Name = "nameKey";
    public static final String Pass = "pass";
    SharedPreferences sharedpreferences;
    // TODO: Rename and change types of parameters
    Button btnsignup,btnlogout;
    Button btnlogin;
    Button btnrank;
    EditText user_lg, password_lg;
    BaiHocHelper baiHocHelper;
    Button btnLogout;
    TextView username;
    Button myorder;
    private BaiHocHelper baihocHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String name = sharedpreferences.getString("nameKey","");
        if (name == ""){
            view = inflater.inflate(R.layout.fragment_mikensico, container, false);

//            Toast.makeText(getActivity(),"abc",Toast.LENGTH_LONG).show();
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

            sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            view = inflater.inflate(R.layout.fragment_user_screen, container, false);
            myorder = view.findViewById(R.id.myOrders);
            myorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),my_order.class);
                    startActivity(intent);
                }
            });

            btnrank = view.findViewById(R.id.rank);
            btnrank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),rankActivity.class);
                    startActivity(intent);
                }

            });

            btnlogout = view.findViewById(R.id.btnLogout);
            username  = view.findViewById(R.id.username);
            Button mikenco = view.findViewById(R.id.mikenisco);
            mikenco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://mikenco.vn");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            username.setText(name);
            btnlogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity()  );
// gán tiêu đề cho dialog
                    alertDialogBuilder.setTitle("Logout");
// hiển thị Thông điệp (thông báo) lên dialog
                    alertDialogBuilder .setMessage("Do you want to log out ?")
                            .setCancelable(false)
                            .setPositiveButton("No",new DialogInterface.OnClickListener()  {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.clear();
                                    editor.apply();
                                    Intent intent = getActivity().getIntent();
                                    getActivity().finish();
                                    startActivity(intent);
                                }
                            });
                    // Tạo alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // Hiển thị dialog
                    alertDialog.show();
                }
            });
        }
        return view;
    }
    private void confirm(){

    }

}