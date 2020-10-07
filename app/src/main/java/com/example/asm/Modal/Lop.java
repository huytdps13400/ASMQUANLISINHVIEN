package com.example.asm.Modal;


import androidx.annotation.NonNull;

public class Lop {
    private String idlop;
    private String tenlop;

    public Lop(String idlop, String tenlop) {
        this.idlop = idlop;
        this.tenlop = tenlop;
    }

    public String getIdlop() {
        return idlop;
    }

    public void setIdlop(String idlop) {
        this.idlop = idlop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    @NonNull
    @Override
    public String toString() {
        return idlop;
    }
}
