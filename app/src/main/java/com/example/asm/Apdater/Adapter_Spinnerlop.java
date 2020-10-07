package com.example.asm.Apdater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asm.LOPDAO.LopDAO;
import com.example.asm.Modal.Lop;
import com.example.asm.R;

import java.util.ArrayList;

public class Adapter_Spinnerlop extends BaseAdapter {
    Context context;
    ArrayList<Lop> dslop;
    LopDAO daolop;

    public Adapter_Spinnerlop(Context context, ArrayList<Lop> dslop) {
        this.context = context;
        this.dslop = dslop;
    }

    @Override
    public int getCount() {
        return dslop.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_spinnerlop,null);
        TextView tv_spinnerlop = convertView.findViewById(R.id.tv_spinnerlop);
        Lop lop = dslop.get(position);
        tv_spinnerlop.setText(lop.getTenlop());
        return convertView;
    }
}
