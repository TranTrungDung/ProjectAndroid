package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;
    BaiHocHelper baiHocHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new HomeFragment()).commit();
        bnv = (BottomNavigationView)findViewById(R.id.bottomNavigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()){
                    case R.id.ac : temp =new MikensicoFragment();
                        break;
                    case R.id.category : temp =new CategoryFragment();
                        break;
                        case R.id.menu_home : temp =new HomeFragment();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();
                return true;
            }
        });
        //tao database
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);
        //taobang
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,email VARCHAR(200),username VARCHAR(200),password VARCHAR(200),phone VARCHAR(11))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS category(id_category INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(200))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS product(id_pr INTEGER PRIMARY KEY AUTOINCREMENT,id_category INTEGER,price INTEGER,size VARCHAR(200),name VARCHAR(200),details VARCHAR(200),amount INTEGER,image BLOG,FOREIGN KEY (id_category) REFERENCES category(id_category))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS bill(id_bill INTEGER PRIMARY KEY AUTOINCREMENT,id INTEGER,date DATE,sum INTEGER,pay INTEGER,FOREIGN KEY (id) REFERENCES user(id))");
        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS detail(id_pr INTEGER,id_bill INTEGER,amount INTEGER,price INTEGER,FOREIGN KEY (id_pr) REFERENCES product(id),FOREIGN KEY (id_bill) REFERENCES bill(id_bill))");
        //chendulieu
        //baiHocHelper.QueryData("INSERT INTO user VALUES(null,'phamthanhquan2411@gmail.com','quan','12345','0376135632')");
        //baiHocHelper.QueryData("INSERT INTO user VALUES(null,'trantrungdung@gmail.com','dung','12345','012345678')");
        //baiHocHelper.QueryData("INSERT INTO user VALUES(null,'deodeo@gmail.com','Quynh','12345','0987654321')");
        //baiHocHelper.QueryData("INSERT INTO category VALUES(null,'ao')");
       // baiHocHelper.QueryData("INSERT INTO category VALUES(null,'quan')");
       // baiHocHelper.QueryData("INSERT INTO category VALUES(null,'mu')");
       // baiHocHelper.QueryData("INSERT INTO category VALUES(null,'giay')");



    }
}