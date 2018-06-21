package com.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.images.WebImage;
import com.google.zxing.WriterException;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.encoding.EncodingHandler;
import com.qrcodescan.R;
import com.utils.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainscannerActivity extends AppCompatActivity {
    Button tiaozhuan;
    Button to_register;
    @BindView(R.id.openQrCodeScan)
    Button openQrCodeScan;
    @BindView(R.id.text)
    EditText text;
    @BindView(R.id.CreateQrCode)
    Button CreateQrCode;
    @BindView(R.id.QrCode)
    ImageView QrCode;
    TextView mm;

    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String nnn="1";

        mm=(TextView) findViewById(R.id.outText);
        /*if(st.isadmin==1)
        mm.setVisibility(View.VISIBLE);
        else
        mm.setVisibility(View.INVISIBLE);*/


        tiaozhuan=(Button)findViewById(R.id.tiaozhuan);
        to_register=(Button)findViewById(R.id.to_register);
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainscannerActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainscannerActivity.this,Register.class);
                startActivity(intent);
            }
        });
        Bmob.initialize(this, "2b78e28cc371b21beea7352333b297f5");
        Person p2 = new Person();
        p2.setName("lucky111");
        p2.setaddress("北京海淀");
        p2.setpassword("123456");
        p2.setEmail("150150");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                  //  Toast("添加数据成功，返回objectId为："+objectId);
                }else{
                 //   Toast("创建数据失败：" + e.getMessage());
                }
            }
        });
        ButterKnife.bind(this);

       ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();

        BmobQuery<Person> query = new BmobQuery<Person>();
        query.addWhereEqualTo("name", "user3");

        // query.addWhereEqualTo("password", edit_mm.getText());
        // query.addWhereEqualTo("isadmin", "0");
        // Toast.makeText(LoginActivity.this,edit_yhm.getText()+"   "+edit_mm.getText(), Toast.LENGTH_SHORT).show();
        //query.setLimit(50);
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> object, BmobException e) {
                if (e == null) {
                    //   Toast.makeText(LoginActivity.this, "AAAA+bbcccc" + "  " + object.size(), Toast.LENGTH_SHORT).show();
                    //toast("查询年龄6-29岁之间，姓名以'y'或者'e'结尾的人个数："+object.size());
                    //Toast.makeText(tickets.this,String.valueOf(object.size()), Toast.LENGTH_SHORT).show();
                    for (Person i : object) {
                        i.getaddress();
                        //TextView mm=(TextView) findViewById(R.id.outText);
                        mm.setText(""+i.getnum());
                        //   TextView mm=(TextView) findViewById(R.id.outText);
                        //   mm.setText(i.getaddress());
                        //Toast.makeText(tickets.this,i.getName(), Toast.LENGTH_SHORT).show();
                        //if (edit_mm.getText().toString()==i.getpassword())
                        //  iscorrect = true;

                        // Toast.makeText(LoginActivity.this, i.getName() + i.getpassword() + iscorrect, Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(LoginActivity.this, MainscannerActivity.class);
                        // startActivity(intent);
                    }

                }
            }
        });

    }

    @OnClick({R.id.openQrCodeScan, R.id.CreateQrCode})
    public void onClick(View view) {
        TextView mm=(TextView) findViewById(R.id.outText);
        if(st.isadmin=="1")
            mm.setVisibility(View.VISIBLE);
        else
            mm.setVisibility(View.INVISIBLE);
        switch (view.getId()) {
            case R.id.openQrCodeScan:
                //打开二维码扫描界面
                if(CommonUtil.isCameraCanUse()){
                    Intent intent = new Intent(MainscannerActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                }else{
                    Toast.makeText(this,"请打开此应用的摄像头权限！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.CreateQrCode:
                try {
                    //获取输入的文本信息
                    String str = text.getText().toString().trim();
                    if(str != null && !"".equals(str.trim())){
                        //根据输入的文本生成对应的二维码并且显示出来
                        Bitmap mBitmap = EncodingHandler.createQRCode(text.getText().toString(), 500);
                        if(mBitmap != null){
                            Toast.makeText(this,"二维码生成成功！",Toast.LENGTH_SHORT).show();
                            QrCode.setImageBitmap(mBitmap);


                            BmobQuery<Person> query = new BmobQuery<Person>();
                            query.addWhereEqualTo("name", "user3");

                            // query.addWhereEqualTo("password", edit_mm.getText());
                            // query.addWhereEqualTo("isadmin", "0");
                            // Toast.makeText(LoginActivity.this,edit_yhm.getText()+"   "+edit_mm.getText(), Toast.LENGTH_SHORT).show();
                            //query.setLimit(50);
                            query.findObjects(new FindListener<Person>() {
                                @Override
                                public void done(List<Person> object, BmobException e) {
                                    if (e == null) {
                                        //   Toast.makeText(LoginActivity.this, "AAAA+bbcccc" + "  " + object.size(), Toast.LENGTH_SHORT).show();
                                        //toast("查询年龄6-29岁之间，姓名以'y'或者'e'结尾的人个数："+object.size());
                                        //Toast.makeText(tickets.this,String.valueOf(object.size()), Toast.LENGTH_SHORT).show();
                                        for (Person i : object) {
                                            i.getaddress();

                                            TextView mm=(TextView) findViewById(R.id.outText);
                                            mm.setText(""+(i.num+1));

                                            //   TextView mm=(TextView) findViewById(R.id.outText);
                                            //   mm.setText(i.getaddress());
                                            //Toast.makeText(tickets.this,i.getName(), Toast.LENGTH_SHORT).show();
                                            //if (edit_mm.getText().toString()==i.getpassword())
                                            //  iscorrect = true;

                                            // Toast.makeText(LoginActivity.this, i.getName() + i.getpassword() + iscorrect, Toast.LENGTH_SHORT).show();
                                            //Intent intent = new Intent(LoginActivity.this, MainscannerActivity.class);
                                            // startActivity(intent);
                                        }

                                    }
                                }
                            });


                        }
                    }else{
                        Toast.makeText(this,"文本信息不能为空！",Toast.LENGTH_SHORT).show();
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView mm=(TextView) findViewById(R.id.outText);
        if(st.isadmin=="1")
            mm.setVisibility(View.VISIBLE);
        else
            mm.setVisibility(View.INVISIBLE);
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            //qrCodeText.setText(scanResult);
            Intent intent = new Intent(MainscannerActivity.this, WebActivity.class);
            intent.putExtra("web_scanResult",scanResult);
            startActivity(intent);
        }
    }
}
