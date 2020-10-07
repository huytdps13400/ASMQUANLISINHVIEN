package com.example.asm.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLitesv extends SQLiteOpenHelper {
    public SQLitesv(Context context){
        super(context,"sinhvienlop6.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ;

        sql = "CREATE TABLE LOP(MALOP integer primary key autoincrement ,"+"TENLOP text)";
        db.execSQL(sql);
        sql="INSERT INTO LOP(MALOP,TENLOP)"+"VALUES('1','LT15304')";
        db.execSQL(sql);
        sql="INSERT INTO LOP(MALOP,TENLOP)"+"VALUES('2','UD15304')";
        db.execSQL(sql);
        sql="INSERT INTO LOP(MALOP,TENLOP)"+"VALUES('3','TKTW15304')";
        db.execSQL(sql);
        sql="INSERT INTO LOP(MALOP,TENLOP)"+"VALUES('4','TKĐH15304')";
        db.execSQL(sql);
        sql = "CREATE TABLE SINHVIEN(MASV integer primary key autoincrement,"+"TENSV text," +
               "NGANH text,"+ "DATE text,"+"MALOP text  references  LOP(MALOP))";
        db.execSQL(sql);
        sql="INSERT INTO SINHVIEN(MASV,TENSV,NGANH,DATE,MALOP)"+"VALUES('1','Trần Đình Huy','LT15304','07/11/2001','1')";
        db.execSQL(sql);
        sql="INSERT INTO SINHVIEN(MASV,TENSV,NGANH,DATE,MALOP)"+"VALUES('2','Trần Thị Mỹ Duyên','UD15304','08/12/2001','2')";
        db.execSQL(sql);
        sql="INSERT INTO SINHVIEN(MASV,TENSV,NGANH,DATE,MALOP)"+"VALUES('3','Trần Đình Duy','TKTW15304','15/11/2001','3')";
        db.execSQL(sql);
        sql="INSERT INTO SINHVIEN(MASV,TENSV,NGANH,DATE,MALOP)"+"VALUES('4','Trần Đình Huy','TKĐH15304','20/10/2001','4')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists LOP");
        db.execSQL("Drop table if exists SINHVIEN");

        onCreate(db);
    }
}
