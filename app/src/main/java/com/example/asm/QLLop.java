package com.example.asm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm.Apdater.AdapterLop;
import com.example.asm.LOPDAO.LopDAO;
import com.example.asm.Modal.Lop;

import java.util.ArrayList;

public class QLLop extends AppCompatActivity {

    ImageView img_back,tv_themlop;
    public static ListView lv_lop;
    public static LopDAO lopDAO;
    public static ArrayList<Lop> datalop = new ArrayList<>();
    public static AdapterLop adapterLop;
    MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_lop);

        initView();
        capnhat();
        tv_themlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(QLLop.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_them_lop,null);
                dialogBuilder.setView(dialogView);

                final EditText edt_tenlop = dialogView.findViewById(R.id.edtten_lop);
                Button btn_them = dialogView.findViewById(R.id.btnthemlop);
                Button btn_huy = dialogView.findViewById(R.id.btnhuythem_lop);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenlop = edt_tenlop.getText().toString().trim();
                        lopDAO.themlop(new Lop(null, tenlop));
                        alertDialog.cancel();
                        capnhat();
                        Toast.makeText(QLLop.this, "THÊM LỚP THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.cancel();
                    }
                });

            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comeback = new Intent(QLLop.this,MainActivity.class);
                startActivity(comeback);
            }
        });
    }

    public void initView(){
        tv_themlop = findViewById(R.id.tv_themlop);
        img_back = findViewById(R.id.img_back);
        lv_lop = findViewById(R.id.lv_lop);
    }
    public void capnhat(){
        lopDAO = new LopDAO(QLLop.this);
        datalop = lopDAO.getLop();
        adapterLop = new AdapterLop(QLLop.this, datalop);
        lv_lop.setAdapter(adapterLop);

    }
}
