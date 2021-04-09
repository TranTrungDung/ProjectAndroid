package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class detailPro extends AppCompatActivity {
    TextView name1,price,detail;
    ImageView img;
    ImageButton btnimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pro);
        name1 = (TextView) findViewById(R.id.name1);
            price = (TextView) findViewById(R.id.pricee);
                    Bundle bundle = new Bundle();
                    bundle.putString("category", all);
                    intent1.putExtra("data", bundle);
                    finish();
                    startActivity(intent1);
                }
            });
            test = (Button) findViewById(R.id.test);
            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(detailPro.this);
                    Gson gson = new Gson();
                    String jsonString = pref.getString(product, "");
                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("pro");
                    editor.apply();
                    Toast.makeText(detailPro.this,"ok",Toast.LENGTH_LONG).show();
                }
            });
        }

    }