package com.example.diemdanhsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_danhsachSV extends BaseAdapter {

    ArrayList<String> tenSV ;
    ArrayList<String>  maSV;
    LayoutInflater dsSinhvien;

    public Adapter_danhsachSV(Context context, ArrayList<String>  tsv, ArrayList<String>  msv){
        tenSV=tsv;
        maSV=msv;
        dsSinhvien=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return tenSV.size();
    }

    @Override
    public Object getItem(int i) {
        return tenSV.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = dsSinhvien.inflate(R.layout.item_diemdanh,null);

        TextView tvhoten = (TextView)v.findViewById(R.id.tvHoTen);
        TextView tvMsv = (TextView)v.findViewById(R.id.tvMSV);

        tvhoten.setText(tenSV.get(i));
        tvMsv.setText(maSV.get(i));

        return v;
    }
}
