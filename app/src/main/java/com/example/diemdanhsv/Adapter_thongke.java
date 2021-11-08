package com.example.diemdanhsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huawei.hms.scankit.ViewfinderView;

import java.util.ArrayList;

public class Adapter_thongke extends BaseAdapter {

    ArrayList<String> tensv;
    ArrayList<String> msv;
    ArrayList<String> sobuoinghi;
    ArrayList<String> camthi;
    LayoutInflater layoutThongke;
    public Adapter_thongke (Context context,ArrayList<String> tensv, ArrayList<String> msv,ArrayList<String> sobuoinghi,ArrayList<String> camthi){
        this.tensv=tensv;
        this.msv=msv;
        this.sobuoinghi=sobuoinghi;
        this.camthi=camthi;
        layoutThongke = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return tensv.size();
    }

    @Override
    public Object getItem(int i) {
        return tensv.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view = layoutThongke.inflate(R.layout.item_thongke,null);

        TextView tvTensv = (TextView)view.findViewById(R.id.hoten_thk);
        TextView tvMsv = (TextView)view.findViewById(R.id.masv_thk);
        TextView tvSobuoinghi = (TextView)view.findViewById(R.id.buoinghi_thk);
        TextView tvCamthi = (TextView)view.findViewById(R.id.camthi_thk);

        tvTensv.setText(tensv.get(i));
        tvMsv.setText(msv.get(i));
        tvCamthi.setText(camthi.get(i));
        tvSobuoinghi.setText("Nghỉ: "+sobuoinghi.get(i));

        return view;
    }
}
