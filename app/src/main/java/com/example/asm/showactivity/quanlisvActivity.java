package com.example.asm.showactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.asm.Apdater.Apdatersv;
import com.example.asm.LOPDAO.Lopdaosinhvien;
import com.example.asm.MainActivity;
import com.example.asm.Modal.UserSv;
import com.example.asm.R;

import java.util.ArrayList;

public class quanlisvActivity extends AppCompatActivity {
    public static  ListView lv;
   public static ArrayList<UserSv> data = new ArrayList<>();
    public static Lopdaosinhvien dao;
    public static Apdatersv apdater;
    public static ArrayList<UserSv> filllist;
    ImageView img_back;
    EditText tvsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlisv);
        intview();
        lv = findViewById(R.id.lv);
        dao =new Lopdaosinhvien(quanlisvActivity.this);

        apdater = new Apdatersv(quanlisvActivity.this,data);
        lv.setAdapter(apdater);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(quanlisvActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        tvsearch.setImeOptions(EditorInfo.IME_ACTION_DONE);
        tvsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }
    public void filter(String text){
        filllist = new ArrayList<>();
        dao =new Lopdaosinhvien(quanlisvActivity.this);
        data=dao.readAll();
        for(UserSv item: data){
            if(item.getTensv().toLowerCase().contains(text.toLowerCase())){
                filllist.add(item);
            }
        }
        apdater.search(filllist);
        apdater.notifyDataSetChanged();
    }

    public void onResume(){
        super.onResume();
        data.clear();
        data.addAll(dao.readAll());
        apdater.notifyDataSetChanged();
    }
    public  void intview(){

        img_back = findViewById(R.id.img_back);
        tvsearch = findViewById(R.id.tvsearch);


    }
}
