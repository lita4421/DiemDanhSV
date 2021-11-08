package com.example.diemdanhsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_buoihoc  extends BaseAdapter {
    ArrayList<String> Buoihoc;
    ArrayList<String> SisoSV;
    ArrayList<String> buoihocid;
    LayoutInflater layoutBuoihoc;

    public Adapter_buoihoc (Context context, ArrayList<String> bh, ArrayList<String> ss, ArrayList<String> bhid){
        Buoihoc=bh;
        SisoSV= ss;
        buoihocid=bhid;
        layoutBuoihoc = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Buoihoc.size();
    }

    @Override
    public Object getItem(int i) {
        return Buoihoc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View v = layoutBuoihoc.inflate(R.layout.item_buoihoc,null);

        TextView tvBuoi=v.findViewById(R.id.tvBuoi);
        TextView tvSiso=v.findViewById(R.id.tvSiso);
        TextView tvBuoihocID=v.findViewById(R.id.buoihocID);

        tvBuoi.setText(Buoihoc.get(i));
        tvSiso.setText(SisoSV.get(i));
        tvBuoihocID.setText(buoihocid.get(i));

        return v;
    }
}
