package com.example.asm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm.LOPDAO.QuanTriDao;
import com.example.asm.Modal.User;
import com.example.asm.SQL.SQLite;

public class loginActivity extends AppCompatActivity {
    EditText edt_email,edt_password;
    TextView txtdangki;
    Button btndangnhap;
    SQLite helper;

    CheckBox checkrember;
    public static  User USER = null;
    QuanTriDao quanTriDao;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(loginActivity.this, R.color.colortime));
        quanTriDao = new QuanTriDao(loginActivity.this);
        initView();
        loaddate();
        helper = new SQLite(this);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    String Email = edt_email.getText().toString().trim();
                    String Password = edt_password.getText().toString().trim();
                    boolean check =checkrember.isChecked();
                    User user = new User(Email,Password);
                    if(quanTriDao.checkLogin(user)){
                        luutt(Email,Password,check);
                        USER = user;
                        Toast.makeText(loginActivity.this,"ĐĂNG NHẬP THÀNH CÔNG",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(loginActivity.this,MainActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(loginActivity.this,"ĐĂNG NHẬP THẤT BẠI",Toast.LENGTH_SHORT).show();
                    }







            }
        });


    }
    private void luutt(String user,String pass,boolean check){
        SharedPreferences preferences= getSharedPreferences("thongtin1.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(check){
            editor.putString("username",user);
            editor.putString("password",pass);
            editor.putBoolean("check",check);

        }else {
            editor.clear();
        }editor.commit();

    }
    private void loaddate(){
        SharedPreferences load = getSharedPreferences("thongtin1.dat",MODE_PRIVATE);
        boolean check = load.getBoolean("check",false);
        if(check){
            edt_email.setText(load.getString("username",""));
            edt_password.setText(load.getString("password",""));
            checkrember.setChecked(check);
        }
    }

    public void initView(){
        checkrember= findViewById(R.id.checkrember);
        btndangnhap = findViewById(R.id.btndangnhap);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
    }

}
