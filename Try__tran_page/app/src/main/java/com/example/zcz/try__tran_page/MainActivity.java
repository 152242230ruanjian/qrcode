package com.example.zcz.try__tran_page;

import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private ImageView m1;
    private  ImageView m2,m3,m4;

    private EditText register_yonghu,register_mima;
    private TextView   ok;

    private String str1,str2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       zhuyemian();
    }

    private  void  zhuyemian(){
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "d9539c1dab1929e336661cc28ae8b67a");

        register_mima=(EditText)findViewById(R.id.register_password);
        register_yonghu=(EditText)findViewById(R.id.register_name);
        ok=(TextView)findViewById(R.id.registe_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yonghu p2 = new yonghu();
                str1=register_yonghu.getText().toString();
                str2=register_mima.getText().toString();
                p2.setName(str1);
                p2.setPassword(str2);
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,"chenggong",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"shibai",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        m1=(ImageView) findViewById(R.id.main_1);
       // m1.setColorFilter(Color.WHITE);
        //m1.setBackgroundColor(Color.TRANSPARENT);
      //  m1.setAlpha(100);
        m2=(ImageView) findViewById(R.id.main_2);
        m3=(ImageView) findViewById(R.id.main_3);
        m4=(ImageView) findViewById(R.id.main_4);


        m1.setImageResource(R.drawable.menu_11);
        m2.setImageResource(R.drawable.menu_2);
        m3.setImageResource(R.drawable.menu_3);
        m4.setImageResource(R.drawable.menu_4);


        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciyemian();
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                third_yemian();
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourth_yemian();
            }
        });
    }
    private void ciyemian(){
        setContentView(R.layout.second_main);
        m1=(ImageView) findViewById(R.id.main_1);
        m2=(ImageView) findViewById(R.id.main_2);
        m3=(ImageView) findViewById(R.id.main_3);
        m4=(ImageView) findViewById(R.id.main_4);


        m1.setImageResource(R.drawable.menu_1);
        m2.setImageResource(R.drawable.menu_21);
        m3.setImageResource(R.drawable.menu_3);
        m4.setImageResource(R.drawable.menu_4);


        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuyemian();
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                third_yemian();
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourth_yemian();
            }
        });
    }


    private void third_yemian(){
        setContentView(R.layout.third_main);
        m1=(ImageView) findViewById(R.id.main_1);
        m2=(ImageView) findViewById(R.id.main_2);
        m3=(ImageView) findViewById(R.id.main_3);
        m4=(ImageView) findViewById(R.id.main_4);


        m1.setImageResource(R.drawable.menu_1);
        m2.setImageResource(R.drawable.menu_2);
        m3.setImageResource(R.drawable.menu_31);
        m4.setImageResource(R.drawable.menu_4);

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuyemian();
            }
        });
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciyemian();
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourth_yemian();
            }
        });
    }

    private void fourth_yemian(){
        setContentView(R.layout.fourth_main);
        m1=(ImageView) findViewById(R.id.main_1);
        m2=(ImageView) findViewById(R.id.main_2);
        m3=(ImageView) findViewById(R.id.main_3);
        m4=(ImageView) findViewById(R.id.main_4);


        m1.setImageResource(R.drawable.menu_1);
        m2.setImageResource(R.drawable.menu_2);
        m3.setImageResource(R.drawable.menu_3);
        m4.setImageResource(R.drawable.menu_41);


        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciyemian();
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                third_yemian();
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuyemian();
            }
        });
    }
}
