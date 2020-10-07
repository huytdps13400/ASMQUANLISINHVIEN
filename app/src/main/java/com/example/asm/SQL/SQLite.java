package com.example.asm.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asm.Modal.User;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context){
        super(context,"user.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ;

        sql = "CREATE TABLE USER(TK text primary key ,"+"PASS text)";
        db.execSQL(sql);
        sql = "INSERT INTO USER VALUES ('admin', '12345678')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists USER");


        onCreate(db);
    }
    public User insert(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TK",user.email);
        values.put("PASS",user.password);
        db.insert("USER",null,values);
        db.close();
        return user;
    }



    }

