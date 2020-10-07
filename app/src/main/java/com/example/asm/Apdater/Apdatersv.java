package com.example.asm.Apdater;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.asm.LOPDAO.LopDAO;
import com.example.asm.LOPDAO.Lopdaosinhvien;
import com.example.asm.MainActivity;
import com.example.asm.Modal.Lop;
import com.example.asm.Modal.UserSv;
import com.example.asm.R;
import com.example.asm.showactivity.quanlisvActivity;


import java.util.ArrayList;

import static com.example.asm.showactivity.quanlisvActivity.apdater;
import static com.example.asm.showactivity.quanlisvActivity.lv;


public class Apdatersv extends BaseAdapter {

    Context context;
    ArrayList<UserSv> data;
    Lopdaosinhvien dao, dao2;
    MainActivity activity;
    Adapter_Spinnerlop adapterSpinnerlop;
    LopDAO daolop;
    ArrayList<Lop>  lop;
    public Apdatersv(Context context, ArrayList<UserSv> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
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
            convertView = inflater.inflate(R.layout.sinhvien,null);
            holder.txthoten = convertView.findViewById(R.id.txthoten);
            holder.txttenlop = convertView.findViewById(R.id.txttenlop);
            holder.txtmalop = convertView.findViewById(R.id.txtmalop);
            holder.txtnganh = convertView.findViewById(R.id.txtnganh);
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.txtdate = convertView.findViewById(R.id.txtdate);
            holder.create = convertView.findViewById(R.id.create);
            holder.xoa = convertView.findViewById(R.id.xoa);
            convertView.setTag(holder);
        }else
            holder =(view) convertView.getTag();
            holder.create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                    View dialogview = inflater.inflate(R.layout.dialog_updatesv,null);
                    dialogbuilder.setView(dialogview);
                    final EditText edt_sua_ten_sv = (EditText) dialogview.findViewById(R.id.edt_sua_ten_sv);
                    final EditText edt_sua_nganh = (EditText) dialogview.findViewById(R.id.edt_sua_nganh);
                    final Spinner edt_sua_ten_nganh = (Spinner) dialogview.findViewById(R.id.edt_sua_ten_nganh);
                    final EditText edt_sua_date = (EditText) dialogview.findViewById(R.id.edt_sua_date);
                    Button btnsuasv = dialogview.findViewById(R.id.btnsuasv);
                    Button btnhuysua_sv = dialogview.findViewById(R.id.btnhuysua_sv);
                    edt_sua_ten_sv.setText(data.get(position).getTensv());
                    edt_sua_nganh.setText(data.get(position).getNganh());
                    edt_sua_date.setText(data.get(position).getNgaysinh());
                    LopDAO daolop = new LopDAO(context);
                    lop =daolop.getLop();
                     adapterSpinnerlop = new Adapter_Spinnerlop(context,lop);
                    edt_sua_ten_nganh.setAdapter(adapterSpinnerlop);
                     int vitri = Integer.parseInt(data.get(position).getIdlop()) ;
                    Toast.makeText(context,"vị tri" + vitri, Toast.LENGTH_SHORT).show();
                    edt_sua_ten_nganh.setSelection(vitri-1);
                    final AlertDialog alertDialog = dialogbuilder.create();
                    alertDialog.show();
                    btnsuasv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ma = data.get(position).getMasv();
                            String tensv =edt_sua_ten_sv.getText().toString().trim();
                            String tennganh = edt_sua_nganh.getText().toString().trim();
                        int vitri1 = edt_sua_ten_nganh.getSelectedItemPosition();
                        String id =lop.get(vitri1).getIdlop();
                            String date =edt_sua_date.getText().toString().trim();
                            dao = new Lopdaosinhvien(context);
                            dao.update(ma,tensv,tennganh,date,id);
                            Toast.makeText(context,"Cập Nhật Thành Công",Toast.LENGTH_SHORT).show();

                            dao2 = new Lopdaosinhvien(context);
                            data = dao.readAll();
                            apdater = new Apdatersv(context,data);
                            lv.setAdapter(apdater);
                            alertDialog.cancel();

                        }
                    });
                    btnhuysua_sv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });



                }
            });
            holder.xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Lopdaosinhvien dao = new Lopdaosinhvien(context);
                    dao.delet(data.get(position).getMasv());
                    data = dao.readAll();
                    apdater = new Apdatersv(context,data);
                    lv.setAdapter(apdater);
                }
            });
 holder.txthoten.setText(data.get(position).getMasv());
 holder.txttenlop.setText(data.get(position).getTensv());
        holder.txtnganh.setText(data.get(position).getNganh());
 holder.txtdate.setText(data.get(position).getNgaysinh());
        LopDAO daolop = new LopDAO(context);
        lop =daolop.getLop();
        int id_lop = Integer.parseInt(data.get(position).getIdlop());

        holder.txtmalop.setText(lop.get(id_lop-1).getTenlop());

 return convertView;



    }


    class view{
        ImageView  avatar,xoa,create;
        TextView txtdate,txthoten,txttenlop,txtmalop,txtnganh;
    }
    public void search(ArrayList<UserSv> filllist){
        data = filllist;
        notifyDataSetChanged();

    }


}
