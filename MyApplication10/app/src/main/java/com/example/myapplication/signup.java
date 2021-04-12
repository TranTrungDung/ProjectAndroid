package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.SQLLite.BaiHocHelper;

import java.util.regex.Pattern;

import static android.view.View.GONE;

public class signup extends AppCompatActivity {
    private Button btnback;
    private  Button btncreate;
    EditText username ;
    EditText email;
    EditText passwd;
    EditText cfpasswd;
    EditText phone;
    BaiHocHelper baiHocHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.user_sg);
        email = (EditText) findViewById(R.id.email_sg);
        passwd = (EditText) findViewById(R.id.password_sg);
        cfpasswd = (EditText) findViewById(R.id.cfpassword_sg);
        phone = (EditText) findViewById(R.id.phone_sg);
        btnback = (Button) findViewById(R.id.btnback);
        btncreate = (Button)findViewById(R.id.btncreate) ;
        baiHocHelper = new BaiHocHelper(signup.this,"mikenco.sqlite",null,1);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
            }

            boolean isEmpty(EditText text) {
                CharSequence str = text.getText().toString();
                return TextUtils.isEmpty(str);
            }
            boolean isEmail(EditText text){
                CharSequence email = text.getText().toString();
                return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
            }
            void checkDataEntered(){
                if(isEmail(email) == false ) {
                    email.setError("Enter valid email!");
                }
                else if (isEmpty(username))  {
                    Toast.makeText(signup.this,"You must enter username to register!", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(passwd)) {
                    Toast.makeText(signup.this,"You must enter password to register!", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(cfpasswd)) {
                    Toast.makeText(signup.this,"You must enter confirm password to register!", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(email)) {
                    Toast.makeText(signup.this,"You must enter email to register!", Toast.LENGTH_SHORT).show();
                }
                else if (isEmpty(phone)) {
                    Toast t = Toast.makeText(signup.this,"You must enter phone to register!", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Cursor data = baiHocHelper.GetData("SELECT * FROM user WHERE username = '"+username.getText().toString() +"'" );
                    Cursor data2 = baiHocHelper.GetData("SELECT * FROM user WHERE email = '"+email.getText().toString() +"'" );
                    if(data.getCount() >= 1 ) {
                            Toast.makeText(signup.this, "Username invalid!", Toast.LENGTH_SHORT).show();
                        }
                    else if (data2.getCount() >=1) {
                        Toast.makeText(signup.this, "Email invalid!", Toast.LENGTH_SHORT).show();
                        }
                    else {
<<<<<<< HEAD
                        baiHocHelper.QueryData("INSERT INTO user VALUES( null,'"+email.getText().toString()+"','"+username.getText().toString()+"','"+passwd.getText().toString()+"','"+phone.getText().toString()+"',0  )");
                        Toast.makeText(signup.this,"Sign up access!", Toast.LENGTH_SHORT).show();

=======
                        baiHocHelper.QueryData("INSERT INTO user VALUES( 8,'"+email.getText().toString()+"','"+username.getText().toString()+"','"+passwd.getText().toString()+"','"+phone.getText().toString()+"'  )");
                        finish();
>>>>>>> 13f5d4ec69cc82e6651dba0d163d90cbc29b3e17
                    }
                }
            }
        });
   }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}