package com.example.asm.LOPDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm.Modal.UserSv;
import com.example.asm.SQL.SQLitesv;

import java.util.ArrayList;

public class Lopdaosinhvien {
    SQLitesv sv;
    public SQLiteDatabase db;

    public Lopdaosinhvien(Context context){
    sv = new SQLitesv(context);
    }
    public ArrayList<UserSv> readAll(){
        ArrayList<UserSv> data = new ArrayList<>();
        SQLiteDatabase db = sv.getReadableDatabase();

        Cursor cs =db.rawQuery("Select * from SINHVIEN",null);
        cs.moveToFirst();

        while (!cs.isAfterLast()){
        String masv = cs.getString(0);
        String tensv = cs.getString(1);
            String tennganh = cs.getString(2);
            String date = cs.getString(3);
            String idlop = cs.getString(4);

        data.add(new UserSv(masv,tensv,tennganh,date,idlop));
        cs.moveToNext();
        }cs.close();
        return data;
    }
    public boolean insert(UserSv userSv){
        SQLiteDatabase db = sv.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENSV",userSv.getTensv());
       values.put("DATE",userSv.getNgaysinh());
        values.put("NGANH",userSv.getNganh());
       values.put("MALOP",userSv.getIdlop());
       long them = db.insert("SINHVIEN",null,values);
        return them>0;


    }
    public boolean update(String masv,String tensv,String nganh,String date,String idlop){
      db = sv.getReadableDatabase();
      ContentValues values = new ContentValues();
      values.put("TENSV",tensv);
        values.put("DATE",date);
        values.put("NGANH",nganh);
        values.put("MALOP",idlop);
      int update = db.update("SINHVIEN",values,"MASV=?",new String[]{masv});
      return  update>0;

    }
    public boolean delet(String masv){
        db = sv.getReadableDatabase();
        int delet = db.delete("SINHVIEN","MASV=?",new String[]{masv});
        return delet>0;
    }



}
