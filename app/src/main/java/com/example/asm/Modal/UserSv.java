package com.example.asm.Modal;

import androidx.annotation.NonNull;



public class UserSv  {

    private String masv;
    private String tensv;
    private String nganh;
    private String ngaysinh;
    private  String idlop;

    public UserSv() {
    }

    public UserSv(String masv, String tensv, String nganh, String ngaysinh, String idlop) {
        this.masv = masv;
        this.tensv = tensv;
        this.nganh = nganh;
        this.ngaysinh = ngaysinh;
        this.idlop = idlop;
    }

    public UserSv(String tensv, String nganh, String ngaysinh, String idlop) {
        this.tensv = tensv;
        this.nganh = nganh;
        this.ngaysinh = ngaysinh;
        this.idlop = idlop;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getIdlop() {
        return idlop;
    }

    public void setIdlop(String idlop) {
        this.idlop = idlop;
    }
}
