package com.example.diemdanhsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_diemdanh extends BaseAdapter {

    ArrayList<String> hoten ;
    ArrayList<String> maSV;
    LayoutInflater layoutDiemdanh;


    public Adapter_diemdanh (Context context,  ArrayList<String> ht,  ArrayList<String> msv){
        hoten=ht;
        maSV=msv;
        layoutDiemdanh= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return hoten.size();
    }

    @Override
    public Object getItem(int i) {
        return hoten.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = layoutDiemdanh.inflate(R.layout.item_diemdanh,null);
        TextView tvhoten = (TextView)view.findViewById(R.id.tvHoTen);
        TextView tvMsv = (TextView)view.findViewById(R.id.tvMSV);

        tvhoten.setText(hoten.get(i));
        tvMsv.setText(maSV.get(i));

        return view;
    }
}
