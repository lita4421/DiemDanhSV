package com.example.diemdanhsv;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_monhoc  extends BaseAdapter {

    ArrayList<String> ID;
    ArrayList<String> Monhoc;

    LayoutInflater layoutMonhoc ;

    public Adapter_monhoc (Context context , ArrayList<String> id,ArrayList<String> monhoc  ){
        ID = id;
        Monhoc = monhoc;

        layoutMonhoc= LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return ID.size();
    }

    @Override
    public Object getItem(int i) {
        return ID.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = layoutMonhoc.inflate(R.layout.item_monhoc,null);
        TextView Tenmonhoc = (TextView)view.findViewById(R.id.txtMonhoc);
        ImageView mauAmongus = (ImageView)view.findViewById(R.id.imgAmongus);
        TextView monhoc_id = (TextView)view.findViewById(R.id.monhoc_id);

        monhoc_id.setText(ID.get(i));
        Tenmonhoc.setText(Monhoc.get(i));
        //mauAmongus.getBackground().setColorFilter(Color.parseColor("#123456"),PorterDuff.Mode.SRC_ATOP);

        return view;

    }
}
