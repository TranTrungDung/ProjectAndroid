package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.SQLLite.BaiHocHelper;

public class signup extends AppCompatActivity {
    private Button btnback;
    private  Button btncreate;
    EditText username ;
    EditText email;
    EditText passwd;
    EditText cfpasswd;
    EditText phone;
    BaiHocHelper baiHocHelper;
    String pass = null , cfpass = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.user_pass);
        email = (EditText) findViewById(R.id.pass_pass);
        passwd = (EditText) findViewById(R.id.password_update);
        cfpasswd = (EditText) findViewById(R.id.cfpassword_update);
        phone = (EditText) findViewById(R.id.phone_update);
        btnback = (Button) findViewById(R.id.btnback);
        btncreate = (Button)findViewById(R.id.btnupdate) ;
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
                pass =passwd.getText().toString();
                cfpass=cfpasswd.getText().toString();
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
                        if(pass.equals(cfpass) )
                        {
                            baiHocHelper.QueryData("INSERT INTO user VALUES( null,'"+email.getText().toString()+"','"+username.getText().toString()+"','"+passwd.getText().toString()+"','"+phone.getText().toString()+"',0  )");
                            Toast.makeText(signup.this,"Sign up access!", Toast.LENGTH_SHORT).show();

                            finish();


                        }
                        else
                        {
                            Toast.makeText(signup.this, "Password not match !!!", Toast.LENGTH_SHORT).show();
                        }
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