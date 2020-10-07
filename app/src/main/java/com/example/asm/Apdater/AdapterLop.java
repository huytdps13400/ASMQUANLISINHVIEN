package com.example.asm.Apdater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.asm.LOPDAO.LopDAO;
import com.example.asm.Modal.Lop;
import com.example.asm.R;

import java.util.ArrayList;

import static com.example.asm.QLLop.adapterLop;
import static com.example.asm.QLLop.lv_lop;

public class AdapterLop extends BaseAdapter {
    Context context;
    ArrayList<Lop> datalop;
    LopDAO lopDAO;

    public AdapterLop(Context context, ArrayList<Lop> datalop) {
        this.context = context;
        this.datalop = datalop;
    }

    @Override
    public int getCount() {
        return datalop.size();
    }

    @Override
    public Object getItem(int position) {
        return datalop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final view holder;
        if(convertView == null){
            holder = new view();

            LayoutInflater inflater =((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_lop,null);
            holder.tv_namelop = convertView.findViewById(R.id.tv_item_tenlop);
            holder.tv_sualop = convertView.findViewById(R.id.tv_sua_lop);
            holder.tv_xoalop = convertView.findViewById(R.id.tv_xoa_lop);
            convertView.setTag(holder);
        }else
            holder =(view) convertView.getTag();
            holder.tv_sualop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                //LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_sua_lop, null);
                dialogBuilder.setView(dialogView);

                final EditText edt_tenlop = (EditText) dialogView.findViewById(R.id.edt_sua_ten_lop);
                Button btn_suatl = dialogView.findViewById(R.id.btnsualop);
                Button btn_xoa_themtl = dialogView.findViewById(R.id.btnhuysua_lop);

                edt_tenlop.setText(datalop.get(position).getTenlop());

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                btn_suatl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lopDAO = new LopDAO(context);
                        String id = datalop.get(position).getIdlop();
                        String nameTl = edt_tenlop.getText().toString();
                        lopDAO.updateLop(id, nameTl);

                        datalop = lopDAO.getLop();
                        adapterLop = new AdapterLop(context,datalop);
                        lv_lop.setAdapter(adapterLop);
                        alertDialog.cancel();
                    }
                });

                btn_xoa_themtl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.cancel();
                    }
                });



            }
        });
            holder.tv_xoalop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lopDAO = new LopDAO(context);
                    lopDAO.deletlop(datalop.get(position).getIdlop());

                    datalop = lopDAO.getLop();
                    adapterLop = new AdapterLop(context,datalop);
                    lv_lop.setAdapter(adapterLop);

                }
            });
        holder.tv_namelop.setText(datalop.get(position).getTenlop());

        return convertView;
    }

    class view{
       // ImageView img_select;
        ImageView tv_xoalop, tv_sualop;
        TextView tv_namelop;
    }
}
