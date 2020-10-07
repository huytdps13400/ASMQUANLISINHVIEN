package com.example.asm.LOPDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm.Modal.User;
import com.example.asm.SQL.SQLite;
import com.example.asm.loginActivity;

public class QuanTriDao {
    SQLite admin;
    public QuanTriDao(Context context){
        admin = new SQLite(context);
    }
    public boolean checkLogin(User user){
        SQLiteDatabase db2 = admin.getReadableDatabase();
     Cursor cs = db2.rawQuery("SELECT * FROM USER WHERE TK=? AND PASS=?",new String[]{user.getEmail(),user.getPassword()});
     if(cs.getCount()<=0){
         return false;
     }
        return  true;
    }
    public boolean checkpass(String oldpass){
      String password = loginActivity.USER.getPassword();
      if(!oldpass.equals(password)){
          return false;
      }
        return  true;
    }
    public boolean updatepass( User user){
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PASS",user.getPassword());
        int row = db.update("USER",values,"TK=?",new String[]{user.getEmail()});
        return row>0;
    }
}
