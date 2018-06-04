package com.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qrcodescan.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
    EditText name,email,password;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this, "2b78e28cc371b21beea7352333b297f5");
        password=(EditText)findViewById(R.id.register_password);
        name=(EditText)findViewById(R.id.register_name);
        email=(EditText)findViewById(R.id.register_email);
        ok=(Button) findViewById(R.id.register_pass_register);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p2 = new Person();
                p2.setName(name.getText().toString());
                p2.setpassword(password.getText().toString());
                p2.setEmail(email.getText().toString());
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if (e == null) {
                            Toast.makeText(Register.this,"添加成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this,"添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent =new Intent(Register.this,MainscannerActivity.class);
                startActivity(intent);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }
}

