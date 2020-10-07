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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm.LOPDAO.QuanTriDao;
import com.example.asm.Modal.User;
import com.example.asm.SQL.SQLite;

public class dangki extends AppCompatActivity {
    EditText edt_passcu,edt_password,edt_kiemtrapass;
    TextView txtdangnhap;
    Button btndangki;
    SQLite helper;
    ImageView img_back;
    QuanTriDao quanTriDao;
    User user;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(ContextCompat.getColor(dangki.this,R.color.colortime));
        helper = new SQLite(this);
        initview();

        txtdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dangki.this,loginActivity.class);
                startActivity(i);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(dangki.this,MainActivity.class);
                startActivity(c);
            }
        });
        btndangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences load = getSharedPreferences("thongtin.dat",MODE_PRIVATE);
//                String a =load.getString("password","");
                QuanTriDao quanTriDao = new QuanTriDao(dangki.this);
                String passcu = edt_passcu.getText().toString().trim();
                String passmoi = edt_password.getText().toString();
                String nhaplaipass = edt_kiemtrapass.getText().toString();
                String un = loginActivity.USER.getEmail();
                if(!quanTriDao.checkpass(passcu)){
                    Toast.makeText(dangki.this,"Mật Khẩu Cũ Sai",Toast.LENGTH_SHORT).show();
                    Toast.makeText(dangki.this,"Mật Khẩu Cũ Sai"+quanTriDao.checkpass(passcu),Toast.LENGTH_SHORT).show();

                }else{
                    if(!passmoi.equals(nhaplaipass)){
                    Toast.makeText(dangki.this,"Mật Khẩu Nhập Vào Không Trùng",Toast.LENGTH_SHORT).show();

                }else{
                    if(quanTriDao.updatepass(new User(un,passmoi))){
                        Toast.makeText(dangki.this,"Cập Nhật Thành Công",Toast.LENGTH_SHORT).show();
                        loginActivity.USER.setPassword(passmoi);
                    }else{
                        Toast.makeText(dangki.this,"Cập Nhật Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                }

                }
            }
        });

    }
    public void initview(){
        img_back = findViewById(R.id.img_back);
        edt_passcu = findViewById(R.id.edt_passcu);
        edt_password = findViewById(R.id.edt_password);
        edt_kiemtrapass = findViewById(R.id.edt_kiemtrapass);
        btndangki = findViewById(R.id.btndangki);
        txtdangnhap = findViewById(R.id.txtdangnhap);


    }
//    private void luutt(String user,String pass){
//        SharedPreferences preferences= getSharedPreferences("thongtin2.dat",MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//
//            editor.putString("username",user);
//            editor.putString("password1",pass);
//
//
//
//
//        }

}
