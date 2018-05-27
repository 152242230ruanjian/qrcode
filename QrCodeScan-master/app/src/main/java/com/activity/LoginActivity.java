package com.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qrcodescan.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edittext_yhm,edittext_mm;
    private Button buttonlogin;
    private CheckBox checkBox_Login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        denglujiemian();
    }

    private void denglujiemian(){
        super.setContentView(R.layout.activity_login);

        buttonlogin = (Button)findViewById(R.id.button_login);
        edittext_yhm = (EditText)findViewById(R.id.input_messageyhm);
        edittext_mm = (EditText)findViewById(R.id.input_messagemm);
        checkBox_Login = (CheckBox)findViewById(R.id.checkBoxLogin);
        boolean isRemember = pref.getBoolean("remember_password",false);


        if(isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            edittext_yhm.setText(account);
            edittext_mm.setText(password);
            checkBox_Login.setChecked(true);
        }


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = edittext_yhm.getText().toString();
                String password = edittext_mm.getText().toString();

                if(account.equals("admin")&&password.equals("123456")){

                    if(checkBox_Login.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("isLogin",true);

                    }else {
                        editor.clear();
                    }
                    editor.apply();

                }else {
                    Toast.makeText(LoginActivity.this,"请输入用户名和密码!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
