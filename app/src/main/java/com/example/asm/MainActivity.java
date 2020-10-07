package com.example.asm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm.Apdater.Adapter_Spinnerlop;
import com.example.asm.Apdater.Apdatersv;
import com.example.asm.LOPDAO.LopDAO;
import com.example.asm.LOPDAO.Lopdaosinhvien;
import com.example.asm.Modal.Lop;
import com.example.asm.Modal.UserSv;
import com.example.asm.showactivity.quanlisvActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    CardView cardView1,cardView2,cardView3,cardView4;
    private Dialog dialog;
    TextView edtdate;
    TextView exit;
    Lopdaosinhvien lopdaosinhvien;
    LopDAO daolop;
    ArrayList<Lop> lop = new ArrayList<>();
    ArrayList<Apdatersv> date;
    Adapter_Spinnerlop adapterSpinnerlop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniview();
        lopdaosinhvien = new Lopdaosinhvien(MainActivity.this);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, quanlisvActivity.class);
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QLLop.class);
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,dangki.class);
                startActivity(a);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
    }
    public void showDialog(){
//        dialog = new Dialog(MainActivity.this);
//        dialog.setTitle("THÊM SINH VIÊN");
//        dialog.setContentView(R.layout.activity_them);
//        dialog.show();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_them,null);
        dialogBuilder.setView(dialogView);

        final EditText edt_tensv = dialogView.findViewById(R.id.edtten);
        final Spinner edt_tenlop = (Spinner) dialogView.findViewById(R.id.edtlop);
        final EditText edtnganh = dialogView.findViewById(R.id.edtnganh);


          edtdate = dialogView.findViewById(R.id.edtdate);

        Button btn_them = dialogView.findViewById(R.id.btnthemsinhvien);
        Button btn_huy = dialogView.findViewById(R.id.btnhuythem);
        daolop = new LopDAO(MainActivity.this);
        lop =daolop.getLop();
         adapterSpinnerlop = new Adapter_Spinnerlop(MainActivity.this,lop);
        edt_tenlop.setAdapter(adapterSpinnerlop);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        String tensv = edt_tensv.getText().toString().trim();



        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tensv = edt_tensv.getText().toString().trim();
                String ngaysinh = edtdate.getText().toString();
                String tennganh =  edtnganh.getText().toString();
                int index = edt_tenlop.getSelectedItemPosition();
                String idlop = lop.get(index).getIdlop();
                lopdaosinhvien = new Lopdaosinhvien(MainActivity.this);
               lopdaosinhvien.insert(new UserSv(null,tensv,tennganh,ngaysinh,idlop));
                alertDialog.cancel();

                Toast.makeText(MainActivity.this, "THÊM SINH VIÊN THÀNH CÔNG", Toast.LENGTH_SHORT).show();


            }
        });
        edtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepiker();
            }
        });
        //lopdaosinhvien.insert(new UserSv(null, ))

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });


    }
    public void iniview(){
       cardView1=findViewById(R.id.cardview1);
       cardView2 = findViewById(R.id.cardview2);
        cardView3 = findViewById(R.id.cardview3);
        cardView4 = findViewById(R.id.cardview4);
        exit = findViewById(R.id.exit);
    }
    private void datepiker(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
         int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
              calendar.set(i,i1,i2);
                String date = simpleDateFormat.format(calendar.getTime());

                edtdate.setText(date);

            }
        },year,month,day);
        datePicker.show();
    }
}

