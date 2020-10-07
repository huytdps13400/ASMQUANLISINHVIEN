package com.example.asm.LOPDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asm.Modal.Lop;
import com.example.asm.SQL.SQLitesv;

import java.util.ArrayList;

public class LopDAO {
    public SQLiteDatabase db;
    public SQLitesv dbh1;

    public LopDAO(Context context){
        dbh1 = new SQLitesv(context);
    }

    public boolean themlop(Lop lop){
        db = dbh1.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOP", lop.getTenlop());
        long row = db.insert("LOP", null,values);
        return row>0;
    }

    public ArrayList<Lop> getLop() {
        ArrayList<Lop> ds = new ArrayList<Lop>();

        db = dbh1.getReadableDatabase();
        Cursor c = db.query("LOP", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String idtl = c.getString(0);
                String namelop = c.getString(1);
                Lop mt = new Lop(idtl,namelop);
                ds.add(mt);

            } while (c.moveToNext());
        }


        return ds;
    }

    public boolean updateLop(String malop,String tenTL){
        db = dbh1.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLOP",tenTL);
        int update = db.update("LOP",values,"MALOP=?",new String[]{malop});
        return update>0;
    }
    public boolean deletlop(String malop){
        db = dbh1.getReadableDatabase();
        int delet =db.delete("LOP","MALOP=?",new String[]{malop});
        return  delet>0;
    }
}
