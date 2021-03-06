package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.SQLLite.BaiHocHelper;
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
                        break;
                    case R.id.ad : temp =new google();

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner,temp).commit();
                return true;
            }
        });
        //tao database
        baiHocHelper = new BaiHocHelper(this,"mikenco.sqlite",null,1);

//   Không động vào bảng category ,user


//        taobang
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT ,email VARCHAR(200),username VARCHAR(200),password VARCHAR(200),phone VARCHAR(11), sumbill INTEGER)");
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS category(id_category INTEGER PRIMARY KEY ,name VARCHAR(200))");
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS product(id_product INTEGER PRIMARY KEY ,id_category INTEGER,price INTEGER,name VARCHAR(200),details VARCHAR(200),image BLOG,FOREIGN KEY (id_category) REFERENCES category(id_category))");
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS storage_product(id_str INTEGER PRIMARY KEY AUTOINCREMENT  ,id_product INTEGER,size CHAR(10),amount INTEGER,FOREIGN KEY (id_product) REFERENCES product(id_product))");
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS bill(id_bill INTEGER PRIMARY KEY AUTOINCREMENT,id INTEGER,madathang varchar(10),date DATE,sum INTEGER,pay INTEGER,FOREIGN KEY (id) REFERENCES user(id))");
//        baiHocHelper.QueryData("CREATE TABLE IF NOT EXISTS details_bill(id_product INTEGER,id_bill INTEGER,amount INTEGER,price INTEGER,FOREIGN KEY (id_product) REFERENCES product(id_product),FOREIGN KEY (id_bill) REFERENCES bill(id_bill))");
//
//
//
////        chendulieu
//          baiHocHelper.QueryData("INSERT INTO product VALUES(1,1,800000,'MONOGRAM HOODIE','98% Cotton','"+R.drawable.hoddiemixjacket+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(2,1,960000,'LEGEND DENIM JACKET','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.legendjacket+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(3,1,800000,'COUTURE ','98% Cotton, DC Printer','"+R.drawable.couturejacket+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(4,1,1215000,'REBEL JACKET ','98% Cotton','"+R.drawable.rebeljacket+"')");
//
//          baiHocHelper.QueryData("INSERT INTO product VALUES(5,2,855000,'HERMES SWEATER','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.sweaterhermes+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(6,2,880000,'MONALISA SWEATER','98% Cotton, 2% Spandex DTG Printer AR TECHNOLOGY','"+R.drawable.monalisasweater+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(7,2,880000,'VANGOGH SWEATER ','98% Cotton, 2% Spandex DTG Printer AR TECHNOLOGY','"+R.drawable.vangoghsweater+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(8,2,990000,'SAVE THE QUEEN','98% Cotton, 2% Spandex DTG Printer DTG Printer','"+R.drawable.savethequeensweater+"')");
//
//          baiHocHelper.QueryData("INSERT INTO product VALUES(9,3,750000,'BLIND FOR LOVE','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.pololove+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(10,3,700000,'SERPENT POLO','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.poloserpent+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(11,3,800000,'HUMAN BAROQUE ','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.polobaroque+"')");
//          baiHocHelper.QueryData("INSERT INTO product VALUES(12,3,700000,'ANGEL DEVIL ','98% Cotton, 2% Spandex DTG Printer','"+R.drawable.tshirtangel+"')");
//
//        baiHocHelper.QueryData("INSERT INTO product VALUES(13,4,800000,'CRYSTAL JEANS','Denim no Fade','"+R.drawable.crystajean+"')");
//        baiHocHelper.QueryData("INSERT INTO product VALUES(14,4,900000,'BIKER JEAN','Denim no Fade','"+R.drawable.bikerjean+"')");
//        baiHocHelper.QueryData("INSERT INTO product VALUES(15,4,800000,'PRINTED JEANS ','Denim no Fade','"+R.drawable.printedjeans+"')");
//        baiHocHelper.QueryData("INSERT INTO product VALUES(16,4,800000,'RIPPED JEANS ','Denim no Fade','"+R.drawable.rippedjeans+"')");
//
//
////
//        baiHocHelper.QueryData("INSERT INTO category VALUES(1,'JACKETS')");
//        baiHocHelper.QueryData("INSERT INTO category VALUES(2,'SWEATERS')");
//        baiHocHelper.QueryData("INSERT INTO category VALUES(3,'T-SHIRTS')");
//        baiHocHelper.QueryData("INSERT INTO category VALUES(4,'PANTS')");
//
////         //Bảng lưu trữ (id =1)
////
//         baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'1','S','50')");
//         baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'1','M','50')");
//         baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'1','L','50')");
//         baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'1','XL','50')");
//
////        //Bảng lưu trữ (id =2)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'2','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'2','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'2','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'2','XL','50')");
//
////        //Bảng lưu trữ (id =3)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'3','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'3','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'3','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'3','XL','50')");
//
////        //Bảng lưu trữ (id =4)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'4','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'4','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'4','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'4','XL','50')");
//
//
//            //Bảng lưu trữ (id =5)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'5','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'5','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'5','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'5','XL','50')");
//
//        //Bảng lưu trữ (id =6)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'6','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'6','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'6','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'6','XL','50')");
//
//               //Bảng lưu trữ (id =7)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'7','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'7','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'7','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'7','XL','50')");
//
//                //Bảng lưu trữ (id =8)
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'8','S','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'8','M','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'8','L','50')");
//        baiHocHelper.QueryData("INSERT INTO storage_product VALUES(null,'8','XL','50')");
//
//        baiHocHelper.QueryData("INSERT INTO user VALUES(null,'phamthanhquan2411@gmail.com','quan','12345','0376135632',0)");
//        baiHocHelper.QueryData("INSERT INTO user VALUES(null,'trantrungdung@gmail.com','dung','12345','012345678',0)");
//        baiHocHelper.QueryData("INSERT INTO user VALUES(null,'deodeo@gmail.com','Quynh','12345','0987654321',0)");



//        //xoadulieu
//        baiHocHelper.QueryData("DELETE FROM product");
//        baiHocHelper.QueryData("DELETE FROM storage_product");

        // Khong xoa
//   baiHocHelper.QueryData("DELETE FROM user");
//      baiHocHelper.QueryData("DELETE FROM category");
    }
}