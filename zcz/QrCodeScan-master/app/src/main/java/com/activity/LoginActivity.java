package com.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.qrcodescan.R;

import java.util.Timer;
import java.util.TimerTask;

public class  LoginActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private CheckBox rememberPass;
    private SharedPreferences.Editor editor;
    EditText edit_yhm;
    EditText edit_mm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final CheckBox autologin=(CheckBox)findViewById(R.id.autologin);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        edit_yhm=(EditText)findViewById(R.id.edit_yhm);
        edit_mm=(EditText)findViewById(R.id.edit_mima);
        Button login=(Button)findViewById(R.id.login);
        rememberPass=(CheckBox) findViewById(R.id.remember_pass);
        //boolean isRemember =pref.getBoolean("remember_password",false);
       // boolean isautologin=pref.getBoolean("auto_login",false);
        /*if(isRemember){
            String account=pref.getString("account","");
            String password =pref.getString("password","");
            edit_yhm.setText(account);
            edit_mm.setText(password);
            rememberPass.setChecked(true);
        }
        if(isautologin){
            autologin.setChecked(true);
            final   Intent intent = new Intent(MainActivity.this,chepiaoyuding.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    //if (edit_yhm.getText().toString().equals("zcz") && edit_mm.getText().toString().equals("zcz123456"))
                    startActivity(intent); //执行
                }
            };timer.schedule(task, 1000 * 3);

        }*/
        /*autologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autologin.isChecked())
                    rememberPass.setChecked(true);
            }
        });
        rememberPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!rememberPass.isChecked()){
                    autologin.setChecked(false);
                }
            }
        });*/
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (edit_yhm.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入用户名!", Toast.LENGTH_SHORT).show();

                } else if (edit_mm.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入密码!", Toast.LENGTH_SHORT).show();
                } else if (edit_yhm.getText().toString().equals("zcz") && edit_mm.getText().toString().equals("zcz123456")) {
                    //editor =pref.edit();
                    /*if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",edit_yhm.getText().toString());
                        editor.putString("password",edit_mm.getText().toString());
                        if(autologin.isChecked())
                            editor.putBoolean("auto_login",true);
                    }
                    else{
                        editor.clear();
                    }*/
                    //editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainscannerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "密码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }
}
